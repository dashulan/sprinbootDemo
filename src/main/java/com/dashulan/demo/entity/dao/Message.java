package com.dashulan.demo.entity.dao;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private LocalDateTime sentTime;

    @ManyToOne
    private User user;
    @ManyToOne
    private Conversation conversation;
}
