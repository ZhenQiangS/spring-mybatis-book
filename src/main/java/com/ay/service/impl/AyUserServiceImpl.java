package com.ay.service.impl;

import com.ay.dao.AyUserDao;
import com.ay.dto.UserDTO;
import com.ay.model.AyUser;
import com.ay.model.User;
import com.ay.service.AyUserService;
import org.omg.CORBA.Object;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@EnableTransactionManagement
@Service
public class AyUserServiceImpl implements AyUserService {

    @Resource
    private AyUserDao ayUserDao;

    public AyUser findById(String id) {
        return ayUserDao.findById(id);
    }

    public int insert(AyUser ayUser) {
        return ayUserDao.insert(ayUser);
    }

    public List<AyUser> findAllUser() {
        return ayUserDao.findAll();
    }

    public int deleteUser(String id) {
        return ayUserDao.deleteUser(id);
    }

    public int updateUser(AyUser ayUser) {
        return ayUserDao.update(ayUser);
    }


    @Transactional
    public AyUser update(AyUser ayUser) {
        return null;
    }
}
