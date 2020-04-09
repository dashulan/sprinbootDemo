package com.dashulan.demo.entity.dao;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(indexes = {@Index(name="user_phone_active_index",columnList = "phone",unique = true)})
public class UserNeedActive {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String phone;
    private String code;
    private LocalDateTime create_at;
    private boolean isActive;
}
