package com.sqz.springmultidatasource.mapper;

import com.sqz.springmultidatasource.entry.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper {
    @Select("SELECT id,user_name as userName FROM  user ")
    public List<UserEntity> findUser();

    @Insert("insert into user(user_name) values (#{userName}); ")
    public void insertUser(@Param("userName") String userName);

}
