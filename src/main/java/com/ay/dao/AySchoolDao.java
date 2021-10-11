package com.ay.dao;

import com.ay.model.AySchool;

/**
 * 描述：学校 DAO 接口
 *
 * @author ZQS
 * @create 2020/5/12
 */
public interface AySchoolDao {
    AySchool findById(Integer id);
}
