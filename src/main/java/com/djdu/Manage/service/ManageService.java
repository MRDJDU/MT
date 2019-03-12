package com.djdu.Manage.service;

import com.djdu.Manage.entity.Manage;
import com.djdu.Manage.repository.ManageRepository;
import com.djdu.common.Enums.Usable;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @ClassName ManageService
 * @Description TODO 业务逻辑层，调用仓库层接口对数据进行处理
 * @Author DJDU
 * @Date 2019/3/11 10:07
 * @Version 1.0
 **/
@Service
@Transactional//开启事务处理
public class ManageService {
    @Autowired
    ManageRepository manageRepository;
    private String success = "";
    private String fail = "";

    /**
     * @Author DJDU
     * @Description TODO 新增管理员
     * @Date 2019/3/11 10:20 
     * @Param [manage]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage addManage(Manage manage){
        fail="新增管理员失败";
        success="新增管理员成功";
        ResponseMessage responseMessage = new ResponseMessage<Manage>();
        try{
            if(manage.getPassword()==null || manage.getPassword()==""){
                responseMessage.makeFail(fail,"密码不能为空");
                return responseMessage;
            }
            if(manage.getName()==null || manage.getName()==""){
                responseMessage.makeFail(fail,"用户名不能为空");
                return responseMessage;
            }
            else if (manageRepository.UnDeletedfindExistsName(manage.getName())>0){
                responseMessage.makeFail(fail,"该用户名已存在");
                return responseMessage;
            }
            else {
                manage.setManage_id(UUID.randomUUID().toString());
                manage.setCreatTime(new Date());
                manage.setUsable(Usable.UnDeleted);
                manage.setState(true);
                manage.setPassword(Md5.EncoderByMd5(manage.getPassword()));
                manageRepository.save(manage);
                responseMessage.makeSuccess(success,manage.getName(),manage);
                return responseMessage;
            }
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }

    /**
     * @Author DJDU
     * @Description TODO 管理员登陆
     * @Date 2019/3/11 10:54
     * @Param [manage]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage login(Manage manage){
        fail="登陆失败";
        success="登陆成功";
        ResponseMessage responseMessage = new ResponseMessage<Manage>();
        try{
            if(manage.getPassword()==null || manage.getPassword()==""){
                responseMessage.makeFail(fail,"密码不能为空");
                return responseMessage;
            }
            else if(manage.getName()==null || manage.getName()==""){
                responseMessage.makeFail(fail,"用户名不能为空");
                return responseMessage;
            }
            else if(manageRepository.UnDeletedfindByNameToLoginExists(manage.getName())==0){
                responseMessage.makeFail(fail,"用户不存在");
                return responseMessage;
            }
            else if(manageRepository.UnDeletedfindAndStatefindByNameToLoginExists(manage.getName())==0){
                responseMessage.makeFail(fail,"账号已被冻结");
                return responseMessage;
            }
            else {
                String password=manageRepository.UnDeletedfindAndStatefindByNameToLogin(manage.getName());
                if(Md5.checkpassword(manage.getPassword(),password)){
                    responseMessage.makeSuccess(success,manage.getName(),manage);
                }
                else {
                    responseMessage.makeFail(fail,"密码错误");
                }
                return responseMessage;
            }
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }

}
