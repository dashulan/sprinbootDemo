package com.dashulan.demo.chat.service;

import com.dashulan.demo.chat.entity.UserNeedActive;
import java.util.List;

/**
 * (UserNeedActive)表服务接口
 *
 * @author makejava
 * @since 2020-04-10 17:31:00
 */
public interface UserNeedActiveService {

    UserNeedActive queryById(Long id);

    List<UserNeedActive> queryAllByLimit(int offset, int limit);

    UserNeedActive insert(UserNeedActive userNeedActive);

    UserNeedActive update(UserNeedActive userNeedActive);

    boolean deleteById(Long id);

    UserNeedActive generateCode(String phone);

    UserNeedActive getCodeByAuthInfo(String authInfo);
}