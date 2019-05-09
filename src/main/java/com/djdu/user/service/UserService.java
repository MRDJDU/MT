package com.djdu.user.service;

import com.djdu.Manage.entity.Manage;
import com.djdu.common.Enums.Usable;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.Md5;
import com.djdu.goods.repository.GoodsRepository;
import com.djdu.user.entity.User;
import com.djdu.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/8 16:01
 * @Version 1.0
 **/
@Service
@Transactional//开启事务处理
public class UserService {
    @Autowired
    UserRepository userRepository;//用户持久层

    private String success = "";
    private String fail = "";

    /**
     * @Author DJDU
     * @Description TODO 新增用户
     * @Date 2019/5/8 16:57
     * @Param [manage]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    public ResponseMessage adduser(User user){
        fail="新增用户失败";
        success="新增用户成功";
        ResponseMessage responseMessage = new ResponseMessage<User>();
        try{
            userRepository.save(user);
            responseMessage.makeSuccess(success);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }
}
