package com.dashulan.demo.chat.entity.vo;

import lombok.Data;

@Data
public class SuggestName {
    public enum NameState{
        OK,REPEAT,INVALID
    }
    private NameState status;
    private String msg;

}
