package com.ay.dao;

import com.ay.model.AyUserAddress;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public interface AyUserAddressDao {
    AyUserAddress findById(Integer id);
}
