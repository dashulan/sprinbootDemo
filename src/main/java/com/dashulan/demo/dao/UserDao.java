package com.dashulan.demo.dao;

import com.dashulan.demo.entity.dao.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends CrudRepository<User,Long> {
    Optional<User> findByName(String name);
}
