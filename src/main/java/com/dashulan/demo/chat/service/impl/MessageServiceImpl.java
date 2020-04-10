package com.dashulan.demo.chat.service.impl;

import com.dashulan.demo.chat.entity.Message;
import com.dashulan.demo.chat.dao.MessageDao;
import com.dashulan.demo.chat.service.MessageService;
import com.dashulan.demo.entity.ClientMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * (Message)表服务实现类
 *
 * @author makejava
 * @since 2020-04-10 17:19:03
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;


    @Override
    public Message queryById(Long id) {
        return this.messageDao.queryById(id);
    }


    @Override
    public List<Message> queryAllByLimit(int offset, int limit) {
        return this.messageDao.queryAllByLimit(offset, limit);
    }

    @Override
    public Message insert(Message message) {
        this.messageDao.insert(message);
        return message;
    }


    @Override
    public Message update(Message message) {
        this.messageDao.update(message);
        return this.queryById(message.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        return this.messageDao.deleteById(id) > 0;
    }

    @Override
    public void messagePersistence(ClientMessage clientMessage) {
        Message message = convertMessageFromClient(clientMessage);
        messageDao.insert(message);
    }

    private Message convertMessageFromClient(ClientMessage clientMessage) {
        Message message = new Message();
        message.setText(clientMessage.getText());
        message.setSentTime(LocalDateTime.now());
        message.setUId(clientMessage.getUserId());
        message.setCId(clientMessage.getConversationId());
        return message;
    }
}