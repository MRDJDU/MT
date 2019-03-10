package com.djdu.common.Tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName MTLogs
 * @Description TODO 日志工具类
 * @Author DJDU
 * @Date 2019/2/27 10:25
 * @Version 1.0
 **/
public class MTLogs {
    //获取日志配置文件的信息，赋值
    private static Logger MT_INFO = LoggerFactory.getLogger("MT_INFO");
    private static Logger MT_ERROR = LoggerFactory.getLogger("MT_ERROR");

    /**
     * @Author DJDU
     * @Description TODO 写入info级别日志，针对用户
     * @Date 2019/2/27 10:35
     * @Param [info]
     * @return void
     **/
    public static void INFO(String info){
        MT_INFO.info(info);
    }


    /**
     * @Author DJDU
     * @Description TODO 写入error级别日志，针对开发者
     * @Date 2019/2/27 10:37
     * @Param [error]
     * @return void
     **/
    public static void ERROR(String error){
        MT_ERROR.error(error);
    }
}
