package com.dashulan.demo.web;


import com.dashulan.demo.dao.ConversationDao;
import com.dashulan.demo.dao.UserDao;
import com.dashulan.demo.entity.dao.Conversation;
import com.dashulan.demo.entity.dao.Message;
import com.dashulan.demo.entity.dao.User;
import com.dashulan.demo.entity.vo.ResponseData;
import com.dashulan.demo.service.ConversationService;
import org.apache.catalina.mbeans.UserMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
public class ChatController {
    ConversationService service;
    UserDao userDao;
    ConversationDao conversationDao;

    @Autowired
    public ChatController(ConversationService conversationService,UserDao userDao,ConversationDao dao) {
        this.userDao = userDao;
        this.service = conversationService;
        this.conversationDao= dao;
    }
    @GetMapping("/user/{id}")
    public Iterable<Conversation> userAllConversation(@PathVariable("id") Long id){
       return service.getUserAllConversations(id);
    }

    @GetMapping(value = "/conversation/{id}")
    public ResponseEntity<ResponseData> getConversationMessage(@RequestParam(value = "count" ,defaultValue = "20")String count,@PathVariable String id){
        Optional<Conversation> c = this.conversationDao.findById(Long.valueOf(id));
        if (c.isPresent()) {
            Conversation conversation = c.get();
            List<Message> messages = conversation.getMessages();
            return new ResponseEntity<>(ResponseData.ok(messages), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseData.error(null,"会话不存在"), HttpStatus.OK);
    }


    @GetMapping("/conversation/establish/{from}/{to}")
    public ResponseEntity<ResponseData> establishConversation(@PathVariable String from, @PathVariable String to) {
        User userA = userDao.findById(Long.valueOf(from)).get();
        User userB = userDao.findById(Long.valueOf(to)).get();
        Conversation conversation = service.getConversationWithUsers(Arrays.asList(userA,userB));
        conversation = Optional.ofNullable(conversation).orElseGet(()->{
            Conversation c = new Conversation();
            c.getUsers().add(userA);
            c.getUsers().add(userB);
            c.setEstablish_Time(LocalDateTime.now());
            c.setLastChat_Time(LocalDateTime.now());
            service.saveConversation(c);
            userA.getConversations().add(c);
            userB.getConversations().add(c);
            userDao.saveAll(Arrays.asList(userA, userB));
            return c;
        });
        return new ResponseEntity<>(ResponseData.ok(conversation), HttpStatus.OK);
    }

}
