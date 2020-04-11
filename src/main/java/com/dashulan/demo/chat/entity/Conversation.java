package com.dashulan.demo.chat.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * (Conversation)实体类
 *
 * @author makejava
 * @since 2020-04-10 17:17:42
 */

@Data
public class Conversation implements Serializable {
    private static final long serialVersionUID = -64314557299216108L;
    
    private Long id;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime lastChatAt;


}