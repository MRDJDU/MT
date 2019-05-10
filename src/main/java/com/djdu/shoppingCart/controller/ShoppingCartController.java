package com.djdu.shoppingCart.controller;

import com.djdu.common.Message.ResponseMessage;
import com.djdu.shoppingCart.dto.ShoppingCartDto;
import com.djdu.shoppingCart.entity.ShoppingCart;
import com.djdu.shoppingCart.service.ShoppingCartService;
import com.djdu.user.entity.User;
import org.eclipse.jdt.internal.compiler.ast.NullLiteral;
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

    /**
     * @Author DJDU
     * @Description TODO 查找购物车内商品
     * @Date 2019/5/10 4:54
     * @Param [shoppingCart]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/findcar",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage findcar(@RequestBody ShoppingCartDto shoppingCartDto){
        return shoppingCartService.findcar(ShoppingCartDto.getWhereClause(shoppingCartDto));
    }
    @PostMapping(value="/changcar",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage changcar(@RequestBody ShoppingCartDto shoppingCartDto){
        return shoppingCartService.changcar(shoppingCartDto,ShoppingCartDto.getWhereClause(shoppingCartDto));
    }
    @PostMapping(value="/delete",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage delete(@RequestBody ShoppingCartDto shoppingCartDto){
        return shoppingCartService.delete(ShoppingCartDto.getWhereClause(shoppingCartDto));
    }
    @PostMapping(value="/buy_cart",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage buy_cart(@RequestBody ShoppingCartDto shoppingCartDto){
        String[] shoppingCart_ids = shoppingCartDto.getShoppingCart_id().split(",");
        return shoppingCartService.buy_cart(shoppingCart_ids,shoppingCartDto.getUser_id());
    }



}
