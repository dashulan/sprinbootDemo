package com.dashulan.demo.web;

import com.dashulan.demo.dao.ConversationDao;
import com.dashulan.demo.dao.MessageDao;
import com.dashulan.demo.dao.UserDao;
import com.dashulan.demo.entity.Greeting;
import com.dashulan.demo.entity.HelloMessage;
import com.dashulan.demo.entity.dao.Conversation;
import com.dashulan.demo.entity.dao.Message;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.loader.entity.CacheEntityLoaderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@CrossOrigin
@Slf4j
@Controller
public class GrettingController {

    UserDao userDao ;
    private SimpMessagingTemplate template;
    MessageDao messageDao;
    ConversationDao conversationDao;

    @Autowired
    public GrettingController(UserDao userDao, SimpMessagingTemplate template, MessageDao messageDao,ConversationDao dao) {
        this.userDao = userDao;
        this.template = template;
        this.messageDao = messageDao;
        this.conversationDao = dao;
    }





    @MessageMapping("/chat/{fromUser}/{toUser}")
    public void chat2People(HelloMessage message, @DestinationVariable String fromUser,@DestinationVariable String toUser) {
        System.out.println(message);
        System.out.println("from:"+fromUser+"-->"+"to:"+toUser);
        template.convertAndSend("/queue/chat/"+fromUser,message);
        template.convertAndSend("/queue/chat/"+toUser,message);
    }




    @Transactional
    @MessageMapping("/conversation/{conversation}")
    public void send2Conversation(HelloMessage helloMessage, @DestinationVariable String conversation) {
        Message message = new Message();
        message.setText(helloMessage.getText());
        message.setSentTime(LocalDateTime.now());
        message.setUser(userDao.findById(helloMessage.getUserId()).get());
        message.setConversation(conversationDao.findById(helloMessage.getConversationId()).get());
        messageDao.save(message);
        template.convertAndSend("/conversation/" + conversation,helloMessage);
    }
}
