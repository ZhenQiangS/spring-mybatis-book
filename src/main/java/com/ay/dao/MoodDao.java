package com.ay.dao;

import com.ay.model.Mood;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 说说 DAO
 */
@Repository
public interface MoodDao {
    List<Mood> findAll();

    Mood findById(String id);

    boolean update(@Param("mood") Mood mood);


}
