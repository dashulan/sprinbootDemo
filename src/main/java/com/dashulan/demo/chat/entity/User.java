package com.dashulan.demo.chat.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-04-10 17:19:11
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 853592947533875370L;
    
    private Long id;
    
    private String name;
    
    private String password;
    
    private String phone;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
    private String avatarUrl;

}