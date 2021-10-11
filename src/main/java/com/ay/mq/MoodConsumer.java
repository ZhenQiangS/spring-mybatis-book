package com.ay.mq;

import com.ay.dto.MoodDTO;
import com.ay.model.Mood;
import jdk.nashorn.internal.runtime.ECMAException;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * 消费者
 */
@Component
public class MoodConsumer implements MessageListener {

    //key 命名规范：项目名称+模块名称+具体内容
    private static final String PRAISE_HASE_KEY = "springmvc.mybatis.boot.mood.id.list.key";


    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Resource
    private RedisTemplate redisTemplate;

    public void onMessage(Message message, byte[] bytes) {

        try {

            //从 message 对象中获取说说实体
            MoodDTO moodDTO = (MoodDTO) ((ActiveMQObjectMessage) message).getObject();

            //存放到 redis 的 set 中
            redisTemplate.opsForSet().add(PRAISE_HASE_KEY, moodDTO.getId());

            redisTemplate.opsForSet().add(moodDTO.getId(), moodDTO.getUserId());
            logger.info("消费者--->>>用户id:" + moodDTO.getUserId() + " 给说说id:"
                    + moodDTO.getId() + " 点赞");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void handleMessage(MoodDTO moodDTO) {

        try {

            //存放到 redis 的 set 中
            redisTemplate.opsForSet().add(PRAISE_HASE_KEY, moodDTO.getId());

            redisTemplate.opsForSet().add(moodDTO.getId(), moodDTO.getUserId());
            logger.info("消费者--->>>用户id:" + moodDTO.getUserId() + " 给说说id:"
                    + moodDTO.getId() + " 点赞");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}













