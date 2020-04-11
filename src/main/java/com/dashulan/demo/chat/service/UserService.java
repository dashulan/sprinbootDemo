package com.dashulan.demo.chat.service;

import com.dashulan.demo.chat.entity.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-04-10 17:19:11
 */
public interface UserService {


    User queryById(Long id);


    List<User> queryAllByLimit(int offset, int limit);

    User insert(User user);

    User update(User user);

    boolean deleteById(Long id);

    User findUser(String information);

    Boolean askFriend(String from, String to);

    List<User> findAllFriends(String user);

    List<User> findAllFriendsAskTo(String user);

    List<User> findAllFriendsAskFrom(String user);

    boolean addUserWaitActive(String authInfo);

    boolean activeUser(String authInfo,String code);

    boolean addUser(User user);
}