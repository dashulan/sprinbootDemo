package com.dashulan.demo.chat.entity.vo;

import lombok.Data;

@Data
public class ClientMessage {
    private Long uid;
    private String text;
    private String sent;
    private Long conversationId;
}
