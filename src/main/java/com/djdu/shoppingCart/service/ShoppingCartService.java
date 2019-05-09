package com.djdu.shoppingCart.service;

import com.djdu.common.Message.ResponseMessage;
import com.djdu.shoppingCart.entity.ShoppingCart;
import com.djdu.shoppingCart.repository.ShoppingCartRepository;
import com.djdu.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @ClassName ShoppingCartService
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/9 11:15
 * @Version 1.0
 **/
@Service
@Transactional//开启事务处理
public class ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    private String success = "";
    private String fail = "";

    public ResponseMessage addcar(ShoppingCart shoppingCart){
        fail="添加购物车失败";
        success="添加购物车成功";
        ResponseMessage responseMessage = new ResponseMessage<ShoppingCart>();
        try{
            //shoppingCart.setShoppingCart_id(UUID.randomUUID().toString());
            shoppingCartRepository.save(shoppingCart);
            responseMessage.makeSuccess(success);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }
}
