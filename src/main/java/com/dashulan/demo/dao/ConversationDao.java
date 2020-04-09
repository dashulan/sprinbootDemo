package com.dashulan.demo.dao;

import com.dashulan.demo.entity.dao.Conversation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ConversationDao extends CrudRepository<Conversation,Long> {
}
