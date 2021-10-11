package com.ay.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：角色实体
 *
 * @author ZQS
 * @create 2020/05/14
 */
public class AyRole implements Serializable {
    private Integer id;
    private String name;
    // 角色与用户是多对关系，一个角色对应多个用户
    private List<AyUser> ayUserList;

    private MultipartFile d;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<AyUser> getAyUserList() {
        return ayUserList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setAyUserList(List<AyUser> ayUserList) {
        this.ayUserList = ayUserList;
    }
}
