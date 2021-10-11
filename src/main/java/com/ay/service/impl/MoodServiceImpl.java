package com.ay.service.impl;

import com.ay.dao.MoodDao;
import com.ay.dao.UserDao;
import com.ay.dao.UserMoodPraiseRelDao;
import com.ay.dto.MoodDTO;
import com.ay.model.Mood;
import com.ay.model.User;
import com.ay.model.UserMoodPraiseRel;
import com.ay.mq.MoodProducer;
import com.ay.service.MoodService;
import com.ay.service.UserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.*;

@Service
public class MoodServiceImpl implements MoodService {

    @Resource
    private MoodDao moodDao;

    @Resource
    private UserDao userDao;

    @Resource
    private UserMoodPraiseRelDao userMoodPraiseRelDao;

    @Resource
    private RedisTemplate redisTemplate;


    //key 命名规范：项目名称+模块名称+具体内容
    private static final String PRAISE_HASE_KEY = "springmvc.mybatis.boot.mood.id.list.key";

    public List<MoodDTO> findAll() {
        List<Mood> moodList = moodDao.findAll();

        return converModel2DTO(moodList);

    }

    public boolean update(Mood mood) {
        return moodDao.update(mood);
    }

    public Mood findById(String id) {
        return moodDao.findById(id);
    }

    public boolean praiseMood(String userId, String moodId) {
        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
        userMoodPraiseRel.setUserId(userId);
        userMoodPraiseRel.setMoodId(moodId);

        userMoodPraiseRelDao.save(userMoodPraiseRel);
        Mood mood = this.findById(moodId);
        mood.setPraiseNum(mood.getPraiseNum() + 1);
        this.update(mood);


        return Boolean.TRUE;
    }

    @Resource
    private MoodProducer moodProducer;

    private static Destination destination =
            new ActiveMQQueue("ay.queue.high.concurrency.praise");

    public boolean praiseMood4RedisAsync(String userId, String moodId) {

        MoodDTO moodDTO = new MoodDTO();
        moodDTO.setUserId(userId);
        moodDTO.setId(moodId);

        moodProducer.sendMessage(destination, moodDTO);
        return false;
    }

    public boolean praiseMood4Redis(String userId, String moodId) {

        //存入 redis 中
        redisTemplate.opsForSet().add(PRAISE_HASE_KEY, moodId);
        redisTemplate.opsForSet().add(moodId, userId);

        return false;
    }


    @Resource
    private UserService userService;

    public List<MoodDTO> findAll4Redis() {
        List<Mood> moodList = moodDao.findAll();
        if (CollectionUtils.isEmpty(moodList))
            return Collections.EMPTY_LIST;
        List<MoodDTO> moodDTOS = new ArrayList<MoodDTO>();
        for (Mood mood : moodList) {
            MoodDTO moodDTO = new MoodDTO();
            moodDTO.setId(mood.getId());
            moodDTO.setUserId(mood.getUserId());

            // 总点赞数：数据库点赞数  +  redis 点赞数
            moodDTO.setPraiseNum(mood.getPraiseNum()
                    + redisTemplate.opsForSet().size(mood.getId()).intValue());

            moodDTO.setPublishTime(mood.getPublishTime());
            moodDTO.setContent(mood.getContent());

            // 通过userID 查询用户
            User user = userService.find(mood.getUserId());
            moodDTO.setUserName(user.getName());

            //账号
            moodDTO.setUserAccount(user.getAccount());
            moodDTOS.add(moodDTO);
        }
        return moodDTOS;
    }

    private List<MoodDTO> converModel2DTO(List<Mood> moodList) {
        if (CollectionUtils.isEmpty(moodList))
            return Collections.EMPTY_LIST;

        List<MoodDTO> moodDTOList = new ArrayList<MoodDTO>();

        for (Mood mood : moodList) {
            MoodDTO moodDTO = new MoodDTO();
            moodDTO.setId(mood.getId());
            moodDTO.setContent(mood.getContent());
            moodDTO.setPraiseNum(mood.getPraiseNum());
            moodDTO.setPublishTime(mood.getPublishTime());
            moodDTO.setUserId(mood.getUserId());

            moodDTOList.add(moodDTO);

            //设置用户信息
            User user = userDao.find(mood.getId());
            moodDTO.setUserName(user.getName());
            moodDTO.setUserAccount(user.getAccount());
        }
        return moodDTOList;
    }
}
