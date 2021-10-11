package com.ay.service;

import com.ay.dto.MoodDTO;
import com.ay.model.Mood;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说说接口
 */
public interface MoodService {

    //查询所有的说说
    List<MoodDTO> findAll();

    boolean update(@Param("mood") Mood mood);

    Mood findById(String id);

    boolean praiseMood(String userId, String moodId);

    boolean praiseMood4Redis(String userId, String moodId);

    boolean praiseMood4RedisAsync(String userId, String moodId);

    List<MoodDTO> findAll4Redis();

}
