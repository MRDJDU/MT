package com.djdu.common.Message;

import org.springframework.stereotype.Component;

/**
 * @ClassName ResponseMessage
 * @Description 返回请求处理操作结果的信息
 * @Author DJDU
 * @Date 2019/2/12 10:03
 * @Version 1.0
 **/
@Component
public class ResponseMessage<T> {
    private int StatuCode;
    private String Message;
    private T Data;

    public int getStatuCode() {
        return StatuCode;
    }

    public void setStatuCode(int statuCode) {
        StatuCode = statuCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
