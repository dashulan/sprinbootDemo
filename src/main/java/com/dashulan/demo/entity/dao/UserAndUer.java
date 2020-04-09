package com.dashulan.demo.entity.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UserAndUer {

    @EmbeddedId
    UserAndUserKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("user_a_id")
    @JoinColumn(name="user_a_id")
    User userA;

    @JsonIgnore
    @ManyToOne
    @MapsId("user_b_id")
    @JoinColumn(name="user_b_id")
    User userB;


}
