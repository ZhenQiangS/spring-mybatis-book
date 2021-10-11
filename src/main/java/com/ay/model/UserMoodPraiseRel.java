package com.ay.model;

import java.io.Serializable;

/**
 * 说说点赞 关联表
 */
public class UserMoodPraiseRel implements Serializable {
    private String id;

    //用户id
    private String userId;

    // 说说id
    private String moodId;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getMoodId() {
        return moodId;
    }

    private String account;

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMoodId(String moodId) {
        this.moodId = moodId;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
