package com.ay.service;

import com.ay.dto.UserDTO;
import com.ay.model.AyUser;

import java.util.List;

public interface AyUserService {
    AyUser findById(String id);

    int insert(AyUser ayUser);

    List<AyUser> findAllUser();

    int deleteUser(String id);

    int updateUser(AyUser ayUser);
}
