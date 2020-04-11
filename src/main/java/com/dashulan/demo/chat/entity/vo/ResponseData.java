package com.dashulan.demo.chat.entity.vo;

import lombok.Data;

@Data
public class ResponseData <T> {
    public enum Status{
        OK,ERROR
    }
    T data;
    String message;
    Status status;
    public static<T> ResponseData ok(T data){
        ResponseData response  = new ResponseData();
        response.setData(data);
        response.setMessage("ok");
        response.setStatus(Status.OK);
        return response;
    }

    public static<T> ResponseData error(T data,String errorMessage){
        ResponseData response  = new ResponseData();
        response.setData(data);
        response.setMessage(errorMessage);
        response.setStatus(Status.ERROR);
        return response;
    }
}
