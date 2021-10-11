package com.ay.dao;

import com.ay.model.AyUser;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AyUserDao {

    AyUser findById(String id);

    List<AyUser> findAll(RowBounds rowBounds);

    List<AyUser> findAll();

    int insert(AyUser ayUser);

    int insert2(AyUser ayUser);

    int deleteUser(String id);

    public int update(AyUser ayUser);
}
