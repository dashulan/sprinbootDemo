package com.dashulan.demo.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ClientMessage {
    private Long UserId;
    private String text;
    private String sent;
    private Long conversationId;
}
