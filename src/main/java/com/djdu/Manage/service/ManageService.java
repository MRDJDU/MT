package com.djdu.Manage.service;

import com.djdu.Manage.entity.Manage;
import com.djdu.Manage.repository.ManageRepository;
import com.djdu.brand.entity.Brand;
import com.djdu.common.Enums.Usable;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
                manage.setState(1);
                manage.setGrade(1);
                manage.setPassword(Md5.EncoderByMd5(manage.getPassword()));
                if(manage.getImg()==null || manage.getImg()==""){
                    manage.setImg("../../assets/ManageImg/DJDU.png");
                }
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
     * @Description TODO 新增超级管理员，只能通过脚本添加
     * @Date 2019/3/16 16:41 
     * @Param [manage]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage addManageAdmin(Manage manage){
        fail="新增超级管理员失败";
        success="新增超级管理员成功";
        ResponseMessage responseMessage = new ResponseMessage<Manage>();
        try{
            if(!manage.getKey().equals("DJDU")){
                responseMessage.makeFail(fail,"新增口令错误");
                return responseMessage;
            }
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
                manage.setState(1);
                manage.setGrade(0);
                manage.setPassword(Md5.EncoderByMd5(manage.getPassword()));
                if(manage.getImg()==null || manage.getImg()==""){
                    manage.setImg("../../assets/ManageImg/DJDU.png");
                }
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
                    Manage manage1 = manageRepository.findByName(manage.getName()).get();
                    responseMessage.makeSuccess(success,manage.getName(),manage1);
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

    /**
     * @Author DJDU
     * @Description TODO 分页查找管理员列表
     * @Date 2019/3/13 21:57
     * @Param [specification, pageable]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage getBrandPage(Specification specification, Pageable pageable){
        success = "分页查找管理员列表成功！";
        fail = "分页查找管理员列表失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<Manage>>();
        try{
            responseMessage.makeSuccess(success,manageRepository.findAll(specification,pageable));
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }

    }

    /**
     * @Author DJDU
     * @Description TODO 管理员状态变更
     * @Date 2019/3/18 2:40
     * @Param [manage]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage changeState(Manage manage){
        ResponseMessage responseMessage = new ResponseMessage<Manage>();
        try{
             Manage manage1=manageRepository.findById(manage.getManage_id()).get();
             if(manage1.getState()==1){
                 success = "管理员冻结成功！";
                 fail = "管理员冻结失败！";
                 manage1.setState(0);
             }
             else {
                 success = "管理员启用成功！";
                 fail = "管理员启用失败！";
                 manage1.setState(1);
             }
             manageRepository.save(manage1);
             responseMessage.makeSuccess(success);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }

    /**
     * @Author DJDU
     * @Description TODO 删除管理员
     * @Date 2019/3/18 2:44
     * @Param [manage]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage delete(Manage manage){
        ResponseMessage responseMessage = new ResponseMessage<Manage>();
        success = "管理员删除成功！";
        fail = "管理员删除失败！";
        try{
            Manage manage1=manageRepository.findById(manage.getManage_id()).get();
            manage1.setUsable(Usable.Deleted);
            manageRepository.save(manage1);
            responseMessage.makeSuccess(success);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }

}
