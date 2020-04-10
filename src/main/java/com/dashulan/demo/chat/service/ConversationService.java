package com.dashulan.demo.chat.service;

import com.dashulan.demo.chat.entity.Conversation;
import com.dashulan.demo.chat.entity.Message;
import com.dashulan.demo.chat.entity.User;

import java.util.List;

/**
 * (Conversation)表服务接口
 *
 * @author makejava
 * @since 2020-04-10 17:17:44
 */
public interface ConversationService {

    Conversation queryById(Long id);

    List<Conversation> queryAllByLimit(int offset, int limit);

    Conversation insert(Conversation conversation);

    Conversation update(Conversation conversation);

    boolean deleteById(Long id);

    /***
    * @Description: 集合操作 取交集
    * @Param: [users, type]
    * @return: java.lang.Long
    * @Author: dashulan
    * @Date: 2020/4/10
    */
    List<Long> establishConversation(List<Long> users,int type);

    List<Conversation> getUserAllConversations(Long id);

    List<Message> getAllMessageInConversation(Long cid);
}