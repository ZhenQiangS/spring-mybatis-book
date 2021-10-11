package com.ay.service;

import com.ay.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AyUserDemoService {
    List<AyUserDemo> findAll();

    List<AyUserDemo> findAll3();

    List<AyUserDemo> resultMap();

    List<AyUserDemo> findByNameAndPassword(String name, String password);

    List<AyUserDemo> findByNameAndPassword2(String name, String password);

    void insert_1(AyUserDemo AyUserDemo);

    void insert_2(AyUserDemo AyUserDemo);

}
