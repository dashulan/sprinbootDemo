package com.dashulan.demo.chat.dao;

import com.dashulan.demo.chat.entity.Conversation;
import com.dashulan.demo.chat.entity.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Conversation)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-10 17:17:43
 */
@Repository
public interface ConversationDao {

    Conversation queryById(Long id);

    List<Conversation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    List<Conversation> queryAll(Conversation conversation);

    int insert(Conversation conversation);

    int update(Conversation conversation);

    int deleteById(Long id);

    List<Long> findConversation(@Param("user") Long id, @Param("type") int type);

    boolean addUserIntoConversation(@Param("uid") Long uid, @Param("cid") Long cid);

    List<Conversation> getAllUserConversations(Long uid);

}