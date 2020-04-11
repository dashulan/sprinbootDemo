package com.dashulan.demo.chat.dao;

import com.dashulan.demo.chat.entity.UserNeedActive;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (UserNeedActive)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-10 17:31:00
 */
@Mapper
public interface UserNeedActiveDao {


    UserNeedActive queryById(Long id);
    UserNeedActive queryByPhone(String phone);

    List<UserNeedActive> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    List<UserNeedActive> queryAll(UserNeedActive userNeedActive);


    int insert(UserNeedActive userNeedActive);


    int update(UserNeedActive userNeedActive);

    int deleteById(Long id);

    UserNeedActive findCodeByPhone(String phone);
}