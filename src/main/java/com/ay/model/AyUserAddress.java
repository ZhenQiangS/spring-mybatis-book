package com.ay.model;


import java.io.Serializable;

public class AyUserAddress implements Serializable {
    private Integer id;
    private String name;


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
