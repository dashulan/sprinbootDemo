package com.dashulan.demo.entity.vo;

import com.dashulan.demo.entity.dao.User;
import lombok.Data;

@Data
public class UserVo {
    public enum RegisterStatus{
        SUCCESS,FAIL
    }
    private RegisterStatus status;
    private Long id;

    private String name;
    private String avatar_url;
    private int role;

    public UserVo() {
    }

    public UserVo(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.avatar_url =user.getAvatar_url();
        this.role = user.getRole();
    }

    public UserVo fillINfo(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.avatar_url =user.getAvatar_url();
        this.role = user.getRole();
        return this;
    }
}
