package com.ay.dao;

import com.ay.model.User;
import org.springframework.stereotype.Repository;

/**
 * 用户 DAO
 */
@Repository
public interface UserDao {

    //查询用户
    User find(String id);
}
