package com.djdu.shoppingCart.controller;

import com.djdu.common.Message.ResponseMessage;
import com.djdu.shoppingCart.entity.ShoppingCart;
import com.djdu.shoppingCart.service.ShoppingCartService;
import com.djdu.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ShoppingCartController
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/9 10:46
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mt/car")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    /**
     * @Author DJDU
     * @Description TODO 添加购物车
     * @Date 2019/5/9 10:52
     * @Param [shoppingCart]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/addcar",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage addGoods(@RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.addcar(shoppingCart);
    }

}
