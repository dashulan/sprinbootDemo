package com.dashulan.demo.entity.dao;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Data
@Table(name = "User",indexes = {@Index(name="user_index_name",columnList = "name",unique = true)})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String avatar_url;

    private String phone;
    private String email;

    private String password;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;



    //--------------
    @ManyToMany(mappedBy = "users")
    private final List<Conversation> conversations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "from",cascade = CascadeType.ALL)
    Set<MakeFriends> friendAskFromMe;

    @OneToMany(mappedBy = "to",cascade = CascadeType.ALL)
    Set<MakeFriends> friendAskToMe;

}
