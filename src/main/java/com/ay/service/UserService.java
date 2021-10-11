package com.ay.service;

import com.ay.dto.UserDTO;

/**
 * 用户服务接口
 */
public interface UserService {
    //通过id 查询用户
    UserDTO find(String id);
}
