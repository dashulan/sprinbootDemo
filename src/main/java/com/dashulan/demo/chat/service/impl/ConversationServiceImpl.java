package com.dashulan.demo.chat.service.impl;

import com.dashulan.demo.chat.dao.MessageDao;
import com.dashulan.demo.chat.dao.UserDao;
import com.dashulan.demo.chat.entity.Conversation;
import com.dashulan.demo.chat.dao.ConversationDao;
import com.dashulan.demo.chat.entity.Message;
import com.dashulan.demo.chat.entity.User;
import com.dashulan.demo.chat.entity.vo.ClientMessage;
import com.dashulan.demo.chat.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * (Conversation)表服务实现类
 *
 * @author makejava
 * @since 2020-04-10 17:17:55
 */
@Service("conversationService")
public class ConversationServiceImpl implements ConversationService {

    @Resource
    private ConversationDao conversationDao;

    @Resource
    private MessageDao messageDao;

    @Resource
    private UserDao userDao;

    @Override
    public Conversation queryById(Long id) {
        return this.conversationDao.queryById(id);
    }


    @Override
    public List<Conversation> queryAllByLimit(int offset, int limit) {
        return this.conversationDao.queryAllByLimit(offset, limit);
    }

    @Override
    public Conversation insert(Conversation conversation) {
        this.conversationDao.insert(conversation);
        return conversation;
    }

    @Override
    public Conversation update(Conversation conversation) {
        this.conversationDao.update(conversation);
        return this.queryById(conversation.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        return this.conversationDao.deleteById(id) > 0;
    }

    @Override
    public List<Long> establishConversation(List<Long> users,int type) {
        Map<Long,List<Long>> map = new HashMap<>();
        users.forEach(user->{

            map.put(user, this.getConversationsId(conversationDao.findConversation(user,type)));
        });
        List<Long> list=  new ArrayList<>();
        list.addAll(map.get(users.get(0)));

        map.entrySet().stream().forEach(entry -> {
            list.retainAll(entry.getValue());
        });
        if (list.size() == 0) {
            Conversation conversation = new Conversation();
            conversation.setCreatedAt(LocalDateTime.now());
            conversation.setLastChatAt(LocalDateTime.now());
            conversationDao.insert(conversation);
            Long cid = conversation.getId();
            users.forEach(u->{
                conversationDao.addUserIntoConversation(u, cid,type);
            });
            list.add(cid);
        }
        return list;
    }

    private List<Long> getConversationsId(List<Conversation> conversations) {
        List<Long> longs = new ArrayList<>();
        conversations.forEach(
                c -> longs.add(c.getId())
        );
        return longs;
    }

    @Override
    public String getConversationTitle(Conversation conversation,String exclude) {
        List<Long> usersInConversation = conversationDao.getUsersInConversation(conversation.getId());
        List<String> names = new ArrayList<>();
        usersInConversation.forEach(u->{
            User user = userDao.queryById(u);
            names.add(user.getName());
        });

        names.remove(exclude);
        if (names.isEmpty()) {
            return "";
        }
        return names.get(0);
    }

    @Override
    public String getConversationCaption(Conversation conversation) {
        return null;
    }

    @Override
    public List<Conversation> getUserAllConversations(Long uid) {
       return conversationDao.getAllUserConversations(uid);
    }

    @Override
    public List<Message> getAllMessageInConversation(Long cid) {
        return messageDao.getAllMessagesInConversation(cid);
    }
}