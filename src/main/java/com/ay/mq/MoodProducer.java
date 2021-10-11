package com.ay.mq;

import com.ay.dto.MoodDTO;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.logging.Logger;

@Component
public class MoodProducer {

    @Resource
    private JmsTemplate jmsTemplate;

    private Logger log = Logger.getLogger(this.getClass().getName());

    public void sendMessage(Destination destination, final MoodDTO mood) {

        // 记录日志
        log.info("生产者--->>>> 用户ID:" + mood.getUserId()
                + " 给说说ID:" + mood.getId() + " 点赞");

        // mood 实体需要实现 Serializable 序列化接口
        jmsTemplate.convertAndSend(destination, mood);
    }
}
