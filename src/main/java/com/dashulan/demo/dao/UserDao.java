package com.dashulan.demo.dao;

import com.dashulan.demo.entity.dao.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User,Long> {
    Optional<User> findByName(String name);

    @Query("select m1.to from MakeFriends m1 join MakeFriends m2 on m1.from = m2.to and m1.to=m2.from where m1.from=:user")
    List<User> findFriends(User user);

}
