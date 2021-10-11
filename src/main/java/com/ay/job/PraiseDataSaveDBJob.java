package com.ay.job;

import com.ay.model.Mood;
import com.ay.model.UserMoodPraiseRel;
import com.ay.service.MoodService;
import com.ay.service.UserMoodPraiseRelService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Set;


/**
 * Quartz 定时器
 */
@Component
@Configurable
@EnableScheduling
public class PraiseDataSaveDBJob {

    @Resource
    private UserMoodPraiseRelService userMoodPraiseRelService;

    @Resource
    private MoodService moodService;

    @Resource
    private RedisTemplate redisTemplate;

    private static final String PRAISE_HASE_KEY = "springmvc.mybatis.boot.mood.id.list.key";

    /**
     * 每10 秒钟更新 “说说点赞”相关表的 数据信息
     * cron: 秒 分 时 日 月 周 年
     */
    @Scheduled(cron = "* 1 * * * *")
    public void savePraiseDataToDB() {
        //step1:拿到 redis 中所有的 说说 id
        Set<String> moods = redisTemplate.opsForSet().members(PRAISE_HASE_KEY);
        if (CollectionUtils.isEmpty(moods))
            return;

        for (String moodId : moods) {
            if (redisTemplate.opsForSet().members(moodId) == null

            ) {
                continue;
            } else {
                //step2 :拿到Reids 中key 为 当前循环中的moodId 的所有 项
                Set<String> userIds = redisTemplate.opsForSet().members(moodId);

                if (CollectionUtils.isEmpty(userIds)) {
                    continue;
                } else {
                    //step3：更新 说说点赞关系表
                    for (String userId : userIds) {
                        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
                        userMoodPraiseRel.setUserId(userId);
                        userMoodPraiseRel.setMoodId(moodId);

                        userMoodPraiseRelService.save(userMoodPraiseRel);
                    }

                    // step4：跟新说说点赞数量
                    Mood mood = moodService.findById(moodId);
                    mood.setPraiseNum(mood.getPraiseNum() + userIds.size());
                    moodService.update(mood);

                    //step5：清除redis 缓存中的数据
                    redisTemplate.delete(moodId);
                }
            }
        }
        //step6：清除 redis 缓存中的数据
        redisTemplate.delete(PRAISE_HASE_KEY);

    }


}
