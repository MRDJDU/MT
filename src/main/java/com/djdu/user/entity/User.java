package com.djdu.user.entity;

import com.djdu.address.entity.Address;
import com.djdu.common.base.BaseEmtity2;
import com.djdu.evaluation.entity.Evaluation;
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
    private String id;//用户id

    private String nickName;//用户昵称

    private String headUrl;//用户头像url

    private String gender;


}
