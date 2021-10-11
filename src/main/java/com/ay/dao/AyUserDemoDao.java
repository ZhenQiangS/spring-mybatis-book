package com.ay.dao;

import com.ay.model.AyUserDemo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AyUserDemoDao {
    List<AyUserDemo> findAll();

    List<AyUserDemo> findAll2();

    /* AyUserDemo 的值自定应映射 */
    List<AyUserDemo> resultMap();

    AyUserDemo findById(String id);

    List<AyUserDemo> findByName(String name);

    int countByName(String name);

    int insert(AyUserDemo AyUserDemo);

    int update(AyUserDemo AyUserDemo);

    /* 如果不填写 @Param 注解，映射值将会按照 参数顺序 映射 */
    List<AyUserDemo> findByNameAndPassword(@Param("name") String name, @Param("password") String password);

    /* 如果不填写 @Param 注解，映射值将会按照 参数顺序 映射 */
    List<AyUserDemo> findByNameAndPassword2(@Param("name") String name, @Param("password") String password);

    // ## 不推荐：如果非要用这种方式，parameterType要设为“Java.Unit.Map” 在xml文件看  ##
    List<AyUserDemo> findByNameAndPassword_1(Map<String, String> map);

    @Select("select * from ay_user")
    List<AyUserDemo> findAll3();

    @Select("select*from ay_user where id=#{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "password", property = "password"),
    })
    List<AyUserDemo> findAll4(String id);

    @Select("select * from ay_user where id = #{id}")
    AyUserDemo findById2(String id);

    /* useGeneraterKeys为true时： 参数 ayUserDemo 的id 会被赋值
     * 插入
     * */
    @Insert("insert into ay_user(name,password) values(#{name},#{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert_1(AyUserDemo ayUserDemo);

    @Insert("insert into ay_user(name,password) values(#{name},#{password})")
    int insert_2(AyUserDemo AyUserDemo);

    /*更新*/
    @Update("update ay_user set name = #{name},password = #{password} where id = #{id}")
    int update_1(AyUserDemo AyUserDemo);

    /*删除：根据 id 删除用户*/
    @Delete("delete ay_user where id = #{id}")
    int delete_1(int id);


    /*删除：根据 name 删除用户*/
    @Delete("delete ay_user where name = #{name}")
    int delete_2(String name);


}
