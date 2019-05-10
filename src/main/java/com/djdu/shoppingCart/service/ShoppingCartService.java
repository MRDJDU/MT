package com.djdu.shoppingCart.service;

import com.djdu.brand.entity.Brand;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.norms.entity.Norms;
import com.djdu.shoppingCart.dto.ShoppingCartDto;
import com.djdu.shoppingCart.entity.ShoppingCart;
import com.djdu.shoppingCart.repository.ShoppingCartRepository;
import com.djdu.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setUser_id(shoppingCart.getUser_id());
        shoppingCartDto.setShoppingCart_id(shoppingCart.getShoppingCart_id());
        try{
            if(shoppingCartRepository.findAll(ShoppingCartDto.getWhereClause(shoppingCartDto)).size()==0){
                shoppingCart.setId(UUID.randomUUID().toString());
                shoppingCart.setShoppingCart_id(shoppingCart.getGoods_id()+'/'+shoppingCart.getSku_id());
                shoppingCartRepository.save(shoppingCart);
                responseMessage.makeSuccess(success);
            }
            else {
                ShoppingCart shoppingCart1 = (ShoppingCart)shoppingCartRepository.findAll(ShoppingCartDto.getWhereClause(shoppingCartDto)).get(0);
                shoppingCart1.setGoodsCount(shoppingCart1.getGoodsCount()+1);
                shoppingCartRepository.save(shoppingCart1);
            }

            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }

    public ResponseMessage findcar(Specification specification){
        success = "获取购物车成功！";
        fail = "获取购物车失败！";
        ResponseMessage responseMessage = new ResponseMessage<List<ShoppingCart>>();
        try{
            responseMessage.makeSuccess(success,shoppingCartRepository.findAll(specification));
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }

    public ResponseMessage changcar(ShoppingCartDto shoppingCartDto, Specification specification){
        success = "成功！";
        fail = "失败！";
        ResponseMessage responseMessage = new ResponseMessage<List<ShoppingCart>>();
        try{
            ShoppingCart shoppingCart = (ShoppingCart)shoppingCartRepository.findAll(specification).get(0);
            shoppingCart.setGoodsCount(shoppingCartDto.getGoodsCount());
            shoppingCartRepository.save(shoppingCart);
            responseMessage.makeSuccess(success);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }


    public ResponseMessage delete(Specification specification){
        success = "删除成功！";
        fail = "删除失败！";
        ResponseMessage responseMessage = new ResponseMessage<List<ShoppingCart>>();
        try{
            ShoppingCart shoppingCart = (ShoppingCart)shoppingCartRepository.findAll(specification).get(0);
            shoppingCartRepository.deleteById(shoppingCart.getId());
            responseMessage.makeSuccess(success);
            return responseMessage;
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }


    public ResponseMessage buy_cart(String[] shoppingCart_ids,String user_id){
        success = "成功！";
        fail = "失败！";
        ResponseMessage responseMessage = new ResponseMessage<List<ShoppingCart>>();
        List<ShoppingCart> shoppingCartList =  Arrays.asList(new ShoppingCart[shoppingCart_ids.length]);
        for(int i = 0;i < shoppingCart_ids.length;i++){
         ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
         shoppingCartDto.setShoppingCart_id(shoppingCart_ids[i]);
         shoppingCartDto.setUser_id(user_id);
         ShoppingCart shoppingCart = (ShoppingCart)shoppingCartRepository.findAll(ShoppingCartDto.getWhereClause(shoppingCartDto)).get(0);
         shoppingCart.setZprice(shoppingCart.getGoodsCount() * shoppingCart.getPrice());
         shoppingCartList.set(i,shoppingCart);
        }
        responseMessage.makeSuccess(success,shoppingCartList);
        return responseMessage;
    }


}
