package com.dashulan.demo.chat.service;

import com.dashulan.demo.chat.entity.Message;
import com.dashulan.demo.chat.entity.vo.ClientMessage;

import java.util.List;


public interface MessageService {

    Message queryById(Long id);

    List<Message> queryAllByLimit(int offset, int limit);

    Message insert(Message message);

    Message update(Message message);

    boolean deleteById(Long id);

    void messagePersistence(ClientMessage message);

}