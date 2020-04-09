package com.dashulan.demo.service;

import com.dashulan.demo.entity.dao.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Session;

@Service
public class JmsChatMessagingService implements ChatMessagingService {

    private JmsTemplate jms;

    @Autowired
    public JmsChatMessagingService(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public void sentMessage(Message message) {
        //繁琐写法
//        jms.send(new MessageCreator() {
//            @Override
//            public javax.jms.Message createMessage(Session session) throws JMSException {
//                return session.createObjectMessage(message);
//            }
//        });
        jms.send(session -> session.createObjectMessage(message));
    }
}
