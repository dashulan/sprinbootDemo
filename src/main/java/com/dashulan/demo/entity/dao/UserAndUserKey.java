package com.dashulan.demo.entity.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class UserAndUserKey implements Serializable {

    @Column(name = "user_a_id")
    Long userAId;

    @Column(name = "user_b_id")
    Long UserBId;


}

