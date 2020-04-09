package com.dashulan.demo.service;

import com.dashulan.demo.entity.dao.Message;

public interface ChatMessagingService {
    void sentMessage(Message message);
}
