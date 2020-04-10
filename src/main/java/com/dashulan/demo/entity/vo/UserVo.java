package com.dashulan.demo.entity.vo;

import com.dashulan.demo.chat.entity.User;
import lombok.Data;

@Data
public class UserVo {
    private Long id;
    private String name;
    private String avatar_url;

    public UserVo() {
    }

    public UserVo(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.avatar_url =user.getAvatarUrl();
    }

}
