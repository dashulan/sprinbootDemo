package com.dashulan.demo.entity.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class MakeFriends {

    @EmbeddedId
    UserAndUserKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("user_ask_from")
    @JoinColumn(name="user_ask_from")
    User from;

    @JsonIgnore
    @ManyToOne
    @MapsId("user_ask_to")
    @JoinColumn(name="user_ask_to")
    User to;


    String remarks;



}
