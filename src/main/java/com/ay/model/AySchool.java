package com.ay.model;

import jdk.nashorn.internal.runtime.OptimisticReturnFilters;

import java.io.Serializable;
import java.util.List;

public class AySchool implements Serializable {
    private Integer id;
    private String name;
    private List<AyStudent> students;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<AyStudent> getStudents() {
        return students;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<AyStudent> students) {
        this.students = students;
    }
}
