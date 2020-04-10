package com.dashulan.demo.chat.dao;

import com.dashulan.demo.chat.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-10 17:19:11
 */
@Repository
public interface UserDao {


    User queryById(Long id);

    User queryByName(String name);

    User queryByPhone(String phone);

    List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    List<User> queryAll(User user);

    int insert(User user);

    int update(User user);

    int deleteById(Long id);

    int addAskFromTo(Long from, Long to);

    List<User> getAllFriendsAsk(Long id);

    List<Long> getAllFriendsAskFrom(Long id);

    List<Long> getAllFriendsAskTo(Long id);

}