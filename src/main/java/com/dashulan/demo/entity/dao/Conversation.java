package com.dashulan.demo.entity.dao;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long conversationId;
    private LocalDateTime establish_Time;
    private LocalDateTime lastChat_Time;

    @OneToMany(mappedBy = "conversation")
    private final List<Message> messages = new ArrayList<>();

    @ManyToMany
    private final List<User> users = new ArrayList<>();
}


