package com.dashulan.demo.chat.service.impl;

import com.dashulan.demo.chat.entity.User;
import com.dashulan.demo.chat.dao.UserDao;
import com.dashulan.demo.chat.entity.UserNeedActive;
import com.dashulan.demo.chat.service.UserNeedActiveService;
import com.dashulan.demo.chat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-04-10 17:19:11
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    private UserNeedActiveService codeService;

    public UserServiceImpl(UserNeedActiveService codeService) {
        this.codeService = codeService;
    }

    @Override
    public User queryById(Long id) {
        return this.userDao.queryById(id);
    }


    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }


    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }


    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }


    @Override
    public boolean deleteById(Long id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public User findUser(String information) {
        return this.deriveInformationType(information);
    }

    @Override
    public Boolean askFriend(String from, String to) {
        User f = deriveInformationType(from);
        User t = deriveInformationType(to);
        if (f == null || t == null) {
            log.error("用户不存在");
            return false;
        }
        userDao.addAskFromTo(f.getId(), t.getId());
//        userDao.addAskFromTo(t.getId(), f.getId());
        return true;
    }

    @Override
    public List<User> findAllFriends(String user) {
        User u = deriveInformationType(user);
        if (u == null) {
            return new ArrayList<>();
        }
        return userDao.getAllFriendsAsk(u.getId());
    }

    @Override
    public List<User> findAllFriendsAskTo(String user) {
        User u = deriveInformationType(user);
        List<Long> users = userDao.getAllFriendsAskTo(u.getId());
        List<User> userList = new ArrayList<>();
        users.forEach(id ->
            userList.add(userDao.queryById(id))
        );
        return userList;
    }

    @Override
    public List<User> findAllFriendsAskFrom(String user) {
        User u = deriveInformationType(user);
        List<Long> users = userDao.getAllFriendsAskFrom(u.getId());
        List<User> userList = new ArrayList<>();
        users.forEach(id->
            userList.add(userDao.queryById(id))
        );
        return userList;
    }

    private User deriveInformationType(String user) {
        Pattern phonePattern = Pattern.compile("[\\d]{11}");
        Pattern namePattern = Pattern.compile("^(?!_)(?!.*?_$)[a-zA-Z_\\u4e00-\\u9fa5]+$");
        if (phonePattern.matcher(user).matches()) {
            return userDao.queryByPhone(user);
        } else if (namePattern.matcher(user).matches()) {
            return userDao.queryByName(user);
        }else {
            return userDao.queryById(Long.valueOf(user));
        }
    }

    @Override
    public boolean addUserWaitActive(String phone) {
        UserNeedActive u =  codeService.generateCode(phone);
        if (u == null) {
            return false;
        }
        String code = u.getCode();
        informUser(code);
        return true;
    }

    private void informUser(String code) {
        System.out.println(code);
    }

    @Override
    public boolean activeUser(String authInfo, String code) {
        UserNeedActive u =  codeService.getCodeByAuthInfo(authInfo);
        if (u.getCode().equals(code)) {
            u.setIsActive(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userDao.insert(user)==1;
    }
}