package com.djdu.user.entity;

import com.djdu.address.entity.Address;
import com.djdu.common.base.BaseEmtity2;
import com.djdu.orders.entity.Orders;
import com.djdu.shoppingCart.entity.ShoppingCart;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName User
 * @Description TODO 用户，用于存储微信授权登陆的用户信息实体
 * @Author DJDU
 * @Date 2019/2/19 9:07
 * @Version 1.0
 **/

@Data
@Entity
@Table(name = "user")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class User extends BaseEmtity2 {
    @Id
    @Column(name = "user_id")
    private String user_id;//用户id

    private String nickname;//用户昵称

    private String headimgurl;//用户头像url

    @OneToMany(cascade = CascadeType.ALL,mappedBy="user",fetch=FetchType.LAZY)
    private List<Address> address = new ArrayList<Address>();//子，用户填写的收货地址

    @OneToMany(cascade = CascadeType.ALL,mappedBy="user",fetch=FetchType.LAZY)
    private List<ShoppingCart> shoppingCart = new ArrayList<ShoppingCart>();//子，购物车内的商品

    @OneToMany(cascade = CascadeType.ALL,mappedBy="user",fetch=FetchType.LAZY)
    private List<Orders> orders = new ArrayList<Orders>();//子，订单

}
