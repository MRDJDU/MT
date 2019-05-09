package com.djdu.user.controller;

import com.djdu.common.Message.ResponseMessage;
import com.djdu.goods.dto.AddgoodsDto;
import com.djdu.user.entity.User;
import com.djdu.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/8 11:14
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mt/user")
public class UserController {
    @Autowired
    UserService userService;
    /**
     * @Author DJDU
     * @Description TODO 添加用户
     * @Date 2019/4/18 14:42
     * @Param [addgoodsDto]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/adduser",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage addGoods(@RequestBody User user){
        return userService.adduser(user);
    }

}
