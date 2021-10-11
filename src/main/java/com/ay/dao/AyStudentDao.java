package com.ay.dao;

import com.ay.model.AyStudent;

import java.util.List;

/**
 * 描述；学生 DAO 接口
 *
 * @author ZQS
 * @create 2020/5/12
 */
public interface AyStudentDao {
    List<AyStudent> findBySchoolId(Integer id);
}
