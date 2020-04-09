package com.dashulan.demo;

import com.dashulan.demo.dao.ConversationDao;
import com.dashulan.demo.dao.MessageDao;
import com.dashulan.demo.dao.UserDao;
import com.dashulan.demo.entity.dao.Message;
import com.dashulan.demo.entity.dao.User;
import com.dashulan.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DaoTest {

    @Autowired
    ConversationDao conversationDao;

    @Autowired
    UserDao userDao;

    @Autowired
    MessageDao messageDao;


    @Test
    public void testUserDao() {
        User user = new User();
        user.setName("大树懒");
        Message message = new Message();
        message.setText("哈哈哈哈哈");
        message.setUser(user);
        messageDao.save(message);
        user.getMessages().add(message);
        userDao.save(user);
        log.info(user.toString());
        Iterable<Message> messageList = messageDao.findAll();
        Iterator<Message> messageIterator = messageList.iterator();
        while (messageIterator.hasNext()) {
            log.info(messageIterator.next().toString());
        }

    }



}
