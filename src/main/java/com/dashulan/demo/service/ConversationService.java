package com.dashulan.demo.service;

import com.dashulan.demo.dao.ConversationDao;
import com.dashulan.demo.entity.dao.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ConversationService {

    ConversationDao conversationDao;

    @Autowired
    public ConversationService(ConversationDao conversationDao) {
        this.conversationDao = conversationDao;
    }

    public List<Conversation> getUserAllConversations(Long uid) {
        return null;
    }
}
