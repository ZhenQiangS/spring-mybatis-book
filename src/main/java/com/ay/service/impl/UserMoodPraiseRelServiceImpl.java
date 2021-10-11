package com.ay.service.impl;

import com.ay.dao.UserMoodPraiseRelDao;
import com.ay.model.UserMoodPraiseRel;
import com.ay.service.UserMoodPraiseRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 说说点赞关联服务类
 */

@Service
public class UserMoodPraiseRelServiceImpl implements UserMoodPraiseRelService {

    @Resource
    private UserMoodPraiseRelDao userMoodPraiseRelDao;

    //点赞功能
    public boolean save(UserMoodPraiseRel userMoodPraiseRel) {
        return userMoodPraiseRelDao.save(userMoodPraiseRel);
    }
}
