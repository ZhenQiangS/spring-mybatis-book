package com.ay.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class AyUser3 implements Serializable {
    private Integer id;

    @NotBlank(message = "name 不能为空")
    private String name;

    @Length(min = 3, max = 16, message = "密码长度必须在3-16位之间")
    private String password;

    @Range(min = 0, max = 150, message = "年龄必须在0-150之间")
    private Integer age;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }
}
