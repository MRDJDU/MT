package com.djdu.common.Message;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @ClassName ResponseMessage
 * @Description TODO 返回请求处理操作结果的信息,状态码，错误信息，操作数据
 * @Author DJDU
 * @Date 2019/2/12 10:03
 * @Version 1.0
 **/
@Data
@Component
public class ResponseMessage<T> {
    private boolean StatuCode;//状态码，用于判断操作是否成功 true：成功 false：失败
    private String Message;//返回给前端的操作结果信息
    private String ErrorMessage;//返回前端错误信息
    private T Data;//返回前端数据
}
