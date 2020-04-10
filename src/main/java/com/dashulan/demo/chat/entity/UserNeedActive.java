package com.dashulan.demo.chat.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * (UserNeedActive)实体类
 *
 * @author makejava
 * @since 2020-04-10 17:31:00
 */
@Data
public class UserNeedActive implements Serializable {
    private static final long serialVersionUID = -19751019163708716L;
    
    private Long id;
    
    private String phone;
    
    private String code;
    
    private Boolean isActive;
    
    private LocalDateTime createdAt;

}