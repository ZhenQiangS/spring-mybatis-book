package com.ay.model;

import java.util.Date;

public class Mood {
    private String id;

    // 说说内容
    private String content;

    //点赞总数
    private Integer praiseNum;

    //用户id
    private String userId;

    //发表时间
    private Date publishTime;

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public String getUserId() {
        return userId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

}
