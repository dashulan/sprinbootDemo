package com.dashulan.demo.chat.web;


import com.dashulan.demo.chat.entity.Conversation;
import com.dashulan.demo.chat.entity.Message;
import com.dashulan.demo.chat.entity.vo.ClientMessage;
import com.dashulan.demo.chat.service.ConversationService;
import com.dashulan.demo.chat.entity.vo.ResponseData;
import com.dashulan.demo.chat.utils.MessageFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@CrossOrigin
@RestController
public class ChatController {

    private ConversationService service;

    @Autowired
    public ChatController(ConversationService conversationService) {
        this.service = conversationService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseData> getUserAllConversations(@PathVariable("id") Long id){
        List<Conversation> conversations = service.getUserAllConversations(id);
        return new ResponseEntity<>(ResponseData.ok(conversations), HttpStatus.OK);
    }

    @GetMapping(value = "/conversation/{cid}")
    public ResponseEntity<ResponseData> getConversationMessage(@RequestParam(value = "count" ,defaultValue = "20")String count,@PathVariable String cid){
        List<Message> messages = service.getAllMessageInConversation(Long.valueOf(cid));
        List<ClientMessage> clientMessages = new ArrayList<>();
        messages.forEach(m-> clientMessages.add(MessageFormatUtil.Message2clientMessage(m)));
        return new ResponseEntity<>(ResponseData.ok(clientMessages), HttpStatus.OK);
    }

    @GetMapping("/conversation/establish/{from}/{to}")
    public ResponseEntity<ResponseData> establishConversation(@PathVariable String from, @PathVariable String to) {
       List<Long> cids =  service.establishConversation(Arrays.asList(Long.valueOf(from), Long.valueOf(to)), 0);
        if (!cids.isEmpty()){
            return new ResponseEntity<>(ResponseData.ok(cids), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseData.error(null, "建立会话失败"), HttpStatus.OK);

    }

}
