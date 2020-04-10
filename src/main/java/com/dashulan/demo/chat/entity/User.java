package com.dashulan.demo.chat.entity;

import com.dashulan.demo.entity.dao.Conversation;
import com.dashulan.demo.entity.dao.MakeFriends;
import com.dashulan.demo.entity.dao.Message;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

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