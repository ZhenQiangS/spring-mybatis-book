package com.ay.model;

import java.io.Serializable;

public class AyStudent implements Serializable {
    private Integer id;
    private String name;
    private Integer age;

    private AySchool aySchool;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setAge(Integer age) {
        this.age = age;
    }

    public AySchool getAySchool() {
        return aySchool;
    }

    public void setAySchool(AySchool aySchool) {
        this.aySchool = aySchool;
    }
}
