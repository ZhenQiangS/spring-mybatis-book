package com.ay.model;

import java.io.Serializable;
import java.util.List;

/**
 * 用户实体
 *
 * @author ZQS
 * @date 2020/02/25
 */
public class AyUser implements Serializable, Cloneable {
    private Integer id;

    private String name;
    private String password;
    private Integer age;


    // 用户与角色是多对多关系，一个用户有多个角色
    private List<AyRole> ayRoleList;


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AyRole> getAyRoleList() {
        return ayRoleList;
    }

    public void setAyRoleList(List<AyRole> ayRoleList) {
        this.ayRoleList = ayRoleList;
    }

    public AyUser clone() {
        AyUser ayUser = null;
        try {
            ayUser = (AyUser) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return ayUser;
    }
}


