package com.ay.service.impl;

import com.ay.dao.AyUserDemoDao;
import com.ay.model.*;
import com.ay.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AyUserDemoServiceImpl implements AyUserDemoService {

    @Resource
    private AyUserDemoDao AyUserDemoDao;

    public List<AyUserDemo> findAll() {
        return AyUserDemoDao.findAll();
    }

    public List<AyUserDemo> findAll3() {
        return AyUserDemoDao.findAll3();
    }

    public List<AyUserDemo> resultMap() {
        return AyUserDemoDao.resultMap();
    }

    public List<AyUserDemo> findByNameAndPassword(String name, String password) {
        return AyUserDemoDao.findByNameAndPassword(name, password);
    }

    public List<AyUserDemo> findByNameAndPassword2(String name, String password) {
        if (name != null) {

        }
        return AyUserDemoDao.findByNameAndPassword2(name, password);
    }

    public void insert_1(AyUserDemo AyUserDemo) {
        AyUserDemoDao.insert_1(AyUserDemo);
    }

    public void insert_2(AyUserDemo AyUserDemo) {
        AyUserDemoDao.insert_2(AyUserDemo);
    }
}
