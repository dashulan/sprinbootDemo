package com.dashulan.demo.entity.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

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



    @JsonProperty("userId")
    public Long userId(){
        return user.getId();
    }

    //怎么才能
    @JsonIgnore
    @ManyToOne
    private User user;


    @JsonIgnore
    @ManyToOne
    private Conversation conversation;
}
