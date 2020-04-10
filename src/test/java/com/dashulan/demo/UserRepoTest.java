package com.dashulan.demo;

import com.dashulan.demo.dao.UserDao;
import com.dashulan.demo.entity.dao.MakeFriends;
import com.dashulan.demo.entity.dao.User;

import com.dashulan.demo.entity.dao.UserAndUserKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserDao userDao;

    @Test
    public void findAllFriend(){
        User u1 = userDao.findByName("大树懒").get();
        User u2 = userDao.findByName("哈哈").get();

        MakeFriends m = new MakeFriends();
        m.setFrom(u1);
        m.setTo(u2);
        u1.getFriendAskFromMe().add(m);

        User user = userDao.findByName("大树懒").get();
        assertThat(user).isNotNull();

        Optional<User> byId = userDao.findById(1l);
    }

}
