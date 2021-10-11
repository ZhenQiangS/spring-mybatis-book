package com.ay.dto;

import com.ay.model.Mood;

import java.io.Serializable;

public class MoodDTO extends Mood implements Serializable {
    private String userName;
    private String userAccount;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAccount() {
        return userAccount;
    }
}
