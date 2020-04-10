package com.dashulan.demo.web;

import com.dashulan.demo.chat.service.MessageService;
import com.dashulan.demo.dao.ConversationDao;
import com.dashulan.demo.dao.MessageDao;
import com.dashulan.demo.dao.UserDao;
import com.dashulan.demo.entity.ClientMessage;
import com.dashulan.demo.entity.dao.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Slf4j
@CrossOrigin
@Controller
public class RealTimeChatController {


    private SimpMessagingTemplate template;
    private MessageService messageService;


    public RealTimeChatController(SimpMessagingTemplate template, MessageService messageService) {
        this.template = template;
        this.messageService = messageService;
    }

    @MessageMapping("/chat/{fromUser}/{toUser}")
    public void chat2People(ClientMessage message, @DestinationVariable String fromUser, @DestinationVariable String toUser) {
        System.out.println("from:"+fromUser+"-->"+"to:"+toUser);
        template.convertAndSend("/queue/chat/"+fromUser,message);
        template.convertAndSend("/queue/chat/"+toUser,message);
    }




    @Transactional
    @MessageMapping("/conversation/{conversation}")
    public void send2Conversation(ClientMessage clientMessage, @DestinationVariable String conversation) {
        messageService.messagePersistence(clientMessage);
        template.convertAndSend("/conversation/" + conversation,clientMessage);
    }
}
