package com.dashulan.demo.chat.dao;

import com.dashulan.demo.chat.entity.Conversation;
import com.dashulan.demo.chat.entity.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Message)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-10 17:19:03
 */
@Repository
public interface MessageDao {


    Message queryById(Long id);

    List<Message> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    List<Message> queryAll(Message message);

    int insert(Message message);

    int update(Message message);

    int deleteById(Long id);

    List<Message> getAllMessagesInConversation(Long cid);
}