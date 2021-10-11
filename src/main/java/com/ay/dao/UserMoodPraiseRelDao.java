package com.ay.dao;

import com.ay.model.UserMoodPraiseRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 说说点赞 DAO
 */
@Repository
public interface UserMoodPraiseRelDao {

    // 点赞
    boolean save(@Param("userMoodPraiseRel") UserMoodPraiseRel userMoodPraiseRel);

}
