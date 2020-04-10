package com.dashulan.demo.chat.service.impl;

import com.dashulan.demo.chat.entity.User;
import com.dashulan.demo.chat.entity.UserNeedActive;
import com.dashulan.demo.chat.dao.UserNeedActiveDao;
import com.dashulan.demo.chat.service.UserNeedActiveService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * (UserNeedActive)表服务实现类
 *
 * @author makejava
 * @since 2020-04-10 17:31:00
 */
@Service("userNeedActiveService")
public class UserNeedActiveServiceImpl implements UserNeedActiveService {

    private UserNeedActiveDao userNeedActiveDao;

    @Autowired
    public UserNeedActiveServiceImpl(UserNeedActiveDao userNeedActiveDao) {
        this.userNeedActiveDao = userNeedActiveDao;
    }

    @Override
    public UserNeedActive queryById(Long id) {
        return this.userNeedActiveDao.queryById(id);
    }

    @Override
    public List<UserNeedActive> queryAllByLimit(int offset, int limit) {
        return this.userNeedActiveDao.queryAllByLimit(offset, limit);
    }

    @Override
    public UserNeedActive insert(UserNeedActive userNeedActive) {
        this.userNeedActiveDao.insert(userNeedActive);
        return userNeedActive;
    }

    @Override
    public UserNeedActive update(UserNeedActive userNeedActive) {
        this.userNeedActiveDao.update(userNeedActive);
        return this.queryById(userNeedActive.getId());
    }

    @Override
    public boolean deleteById(Long id) {

        return this.userNeedActiveDao.deleteById(id) > 0;
    }

    @Override
    public UserNeedActive generateCode(String phone) {
        UserNeedActive id =  userNeedActiveDao.findCodeByPhone(phone);
        if (id == null) {
            return null;
        }
        UUID uuid = UUID.randomUUID();
        UserNeedActive userNeedActive = new UserNeedActive();
        userNeedActive.setCode(uuid.toString().substring(0,6));
        userNeedActive.setCreatedAt(LocalDateTime.now());
        userNeedActive.setIsActive(false);
        userNeedActive.setPhone(phone);
        userNeedActiveDao.insert(userNeedActive);
        return userNeedActive;
    }

    @Override
    public UserNeedActive getCodeByAuthInfo(String authInfo) {
       UserNeedActive u =  userNeedActiveDao.findCodeByPhone(authInfo);
       return u;
    }
}