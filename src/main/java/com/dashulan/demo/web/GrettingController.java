package com.dashulan.demo.web;

import com.dashulan.demo.entity.Greeting;
import com.dashulan.demo.entity.HelloMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

@CrossOrigin
@Slf4j
@Controller
public class GrettingController {

    private SimpMessagingTemplate template;

    @Autowired
    public GrettingController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/hello")
    public void greeting(@Payload String message) {
        log.info(message);
        String newMsg = message + "哈哈哈" + System.currentTimeMillis();
        template.convertAndSend("/topic/greetings", newMsg);
    }

    @MessageMapping("/test")
    public void testChannel(@Payload String message) {
        log.info("hahah");
        String newMsg = message + " test msg";
        template.convertAndSend("/topic/greetings", newMsg);
    }

    @MessageMapping("/test/{userName}")
    public void testUser(@Payload String message, @DestinationVariable String userName) {
        System.out.println(message);
        System.out.println(userName);
        template.convertAndSend("/topic/test/" + userName, message);
    }

    @MessageMapping("/chat/{fromUser}/{toUser}")
    public void chat2People(HelloMessage message, @DestinationVariable String fromUser,@DestinationVariable String toUser) {
        System.out.println(message);
        System.out.println("from:"+fromUser+"-->"+"to:"+toUser);
        template.convertAndSend("/queue/chat/"+fromUser,message);
        template.convertAndSend("/queue/chat/"+toUser,message);
    }
}
