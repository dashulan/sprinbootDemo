package com.dashulan.demo.web;


import com.dashulan.demo.entity.dao.Conversation;
import com.dashulan.demo.entity.dao.Message;
import com.dashulan.demo.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    ConversationService service;

    @Autowired
    public ChatController(ConversationService conversationService) {

        this.service = conversationService;
    }
    @GetMapping("/user/{id}")
    public Iterable<Conversation> userAllConversation(@PathVariable("id") Long id){
       return service.getUserAllConversations(id);
    }

    @CrossOrigin
    @GetMapping("/conversation/id")
    public ResponseEntity<String> testConversations(){
        return new ResponseEntity<>("哈哈哈", HttpStatus.OK);
    }

}
