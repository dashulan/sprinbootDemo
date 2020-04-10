package com.dashulan.demo.entity.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class UserAndUserKey implements Serializable {

    @Column(name = "user_ask_from")
    Long from;

    @Column(name = "user_ask_to")
    Long to;


}

