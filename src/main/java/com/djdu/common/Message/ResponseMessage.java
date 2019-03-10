package com.djdu.common.Message;

import com.djdu.common.Tool.MTLogs;
import com.djdu.common.Tool.MakeMessage;
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

    /**
     * @Author DJDU
     * @Description TODO 操作成功信息填入
     * @Date 2019/3/1 1:54
     * @Param [success, successMessage, data]
     * @return void
     **/
    public void makeSuccess(String success,String successMessage,T data){
        this.StatuCode = true;
        this.Message = MakeMessage.makeMessage(success,successMessage);
        this.Data = data;
        MTLogs.INFO(MakeMessage.makeInfo(success,successMessage));
    }

    /**
     * @Author DJDU
     * @Description TODO 操作成功信息填入,不需要返回详细信息
     * @Date 2019/3/1 1:54
     * @Param [success, successMessage, data]
     * @return void
     **/
    public void makeSuccess(String success,T data){
        this.StatuCode = true;
        this.Message = MakeMessage.makeMessage(success);
        this.Data = data;
        MTLogs.INFO(MakeMessage.makeInfo(success));
    }

    /**
     * @Author DJDU
     * @Description TODO 操作成功信息填入,不需要返回详细信息和实体
     * @Date 2019/3/1 1:54
     * @Param [success, successMessage, data]
     * @return void
     **/
    public void makeSuccess(String success){
        this.StatuCode = true;
        this.Message = MakeMessage.makeMessage(success);
        MTLogs.INFO(MakeMessage.makeInfo(success));
    }



    /**
     * @Author DJDU
     * @Description TODO 操作失败信息填入，用户操作问题
     * @Date 2019/3/1 1:54
     * @Param [fail, failMessage]
     * @return void
     **/
    public void makeFail(String fail,String failMessage){
        this.StatuCode = false;
        this.Message = MakeMessage.makeMessage(fail,failMessage);
        MTLogs.INFO(MakeMessage.makeInfo(fail,failMessage));
        MTLogs.ERROR(MakeMessage.makeError(fail,failMessage));
    }

    /**
     * @Author DJDU
     * @Description TODO 失败信息填入，系统错误
     * @Date 2019/3/1 1:55
     * @Param [error, errorMessage]
     * @return void
     **/
    public void makeError(String error,String errorMessage){
        this.StatuCode = false;
        this.Message = MakeMessage.makeMessage(error,"系统错误，请联系网站管理员");
        this.ErrorMessage = errorMessage;
        MTLogs.ERROR(MakeMessage.makeError(error,errorMessage));
    }
}
