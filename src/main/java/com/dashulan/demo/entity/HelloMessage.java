package com.dashulan.demo.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HelloMessage {
    private Long UserId;
    private List<String> text;
    private String sent;
    private Long conversationId;

}
