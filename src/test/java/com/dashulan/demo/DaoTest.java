package com.dashulan.demo;

import com.dashulan.demo.dao.ConversationDao;
import com.dashulan.demo.dao.MessageDao;
import com.dashulan.demo.dao.UserDao;
import com.dashulan.demo.entity.dao.Conversation;
import com.dashulan.demo.entity.dao.Message;
import com.dashulan.demo.entity.dao.User;
import com.dashulan.demo.service.ConversationService;
import com.dashulan.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
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
    ConversationService  conversationService;



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

    @Transactional
    @Test
    public void testConversations(){
        User userA = new User();
        userA.setName("大树懒");
        userDao.save(userA);
        User userB = new User();
        userB.setName("哈哈");
        userDao.save(userB);
        Conversation conversation = new Conversation();
        conversation.getUsers().add(userA);
        conversation.getUsers().add(userB);
        conversationDao.save(conversation);

        userA.getConversations().add(conversation);
        userB.getConversations().add(conversation);
        userDao.save(userA);
        userDao.save(userB);

        Conversation c = conversationService.getConversationWithUsers(Arrays.asList(userA, userB));
        System.out.println(c.getUsers().size());
    }



}
