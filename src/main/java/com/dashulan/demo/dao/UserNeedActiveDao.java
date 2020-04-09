package com.dashulan.demo.dao;

import com.dashulan.demo.entity.dao.UserNeedActive;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserNeedActiveDao extends CrudRepository<UserNeedActive,Long> {
    UserNeedActive findByPhone(String phone);
}
