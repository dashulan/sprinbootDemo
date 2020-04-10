package com.dashulan.demo.chat.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * (Message)实体类
 *
 * @author makejava
 * @since 2020-04-10 17:19:03
 */
@Data
public class Message implements Serializable {
    private static final long serialVersionUID = 268295407915792752L;
    
    private Long id;
    
    private String text;
    
    private LocalDateTime sentTime;
    
    private Long uId;
    
    private Long cId;

}