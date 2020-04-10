package com.dashulan.demo.service;

import com.dashulan.demo.dao.ConversationDao;
import com.dashulan.demo.dao.UserDao;
import com.dashulan.demo.entity.dao.Conversation;
import com.dashulan.demo.entity.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

public class ConversationService {

//    ConversationDao conversationDao;
//    UserDao userDao;
//
//    @Autowired
//    public ConversationService(ConversationDao conversationDao) {
//        this.conversationDao = conversationDao;
//    }

//
//    public Conversation getConversationWithUsers(List<User> users) {
//        int count =users.size();
//        Map<Long,Integer> map = new HashMap<>();
//        Iterator<User> iterator = users.iterator();
//        while (iterator.hasNext()) {
//            List<Conversation> conversationList = iterator.next().getConversations();
//            conversationList.forEach(c->{
//                Long cid = c.getConversationId();
//                if(map.get(cid)==null)
//                    map.put(cid,Integer.valueOf(1));
//                else
//                    map.put(cid, map.get(cid).intValue() + 1);
//            });
//        }
//        List<Map.Entry<Long,Integer>> list = map.entrySet().stream().filter(
//                e -> e.getValue().equals(count)
//        ).collect(Collectors.toList());
//        if (list.size() == 0) {
//            return null;
//        }
////        return conversationDao.findById(list.get(0).getKey()).get();
//    }
//
//    public List<Conversation> getUserAllConversations(Long uid) {
//        return null;
//    }
//
//    public Long saveConversation(Conversation conversation) {
//        return conversationDao.save(conversation).getConversationId();
//    }
}
