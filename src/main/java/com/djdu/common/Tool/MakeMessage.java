package com.djdu.common.Tool;

import org.springframework.stereotype.Component;

/**
 * @ClassName MakeMessage
 * @Description TODO 规范化输出日志的格式，组装日志信息
 * @Author DJDU
 * @Date 2019/2/28 18:51
 * @Version 1.0
 **/

public class MakeMessage {

    /**
     * @Author DJDU
     * @Description TODO 组装info日志
     * @Date 2019/2/28 19:18 
     * @Param [message, info]
     * @return java.lang.String
     **/
    public static String makeInfo(String message,String info){
        return String.format("%s >>>>> [%s]",message,info);
    }

    /**
     * @Author DJDU
     * @Description TODO 组装info日志,不需要具体成功信息
     * @Date 2019/3/1 22:19
     * @Param [message]
     * @return java.lang.String
     **/
    public static String makeInfo(String message){
        return String.format("%s",message);
    }
    /**
     * @Author DJDU
     * @Description TODO 组装error日志
     * @Date 2019/2/28 19:18 
     * @Param [message, error]
     * @return java.lang.String
     **/
    public static String makeError(String message,String error){
        return String.format("%s >>>>> [%s]",message,error);
    }

    /**
     * @Author DJDU
     * @Description TODO 组装返回给前端的信息
     * @Date 2019/3/1 1:31
     * @Param [message, tips]
     * @return java.lang.String
     **/
    public static String makeMessage(String message,String tips){
        return String.format("%s -> [%s]",message,tips);
    }


    /**
     * @Author DJDU
     * @Description TODO 组装返回给前端的信息,不需要具体成功信息
     * @Date 2019/3/1 22:20
     * @Param [message]
     * @return java.lang.String
     **/
    public static String makeMessage(String message){
        return String.format("%s",message);
    }

    /**
     * @Author DJDU
     * @Description TODO 组装返回给前端的错误信息
     * @Date 2019/2/28 19:35 
     * @Param [message, tips]
     * @return java.lang.String
     **/
    public static String makeErrerMessage(String message,String tips){
        return String.format("%s -> [%s]",message,tips);
    }


}
