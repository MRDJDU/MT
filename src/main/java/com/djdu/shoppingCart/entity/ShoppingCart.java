package com.djdu.shoppingCart.entity;

import com.djdu.common.base.BaseEmtity2;
import com.djdu.goods.entity.Goods;
import com.djdu.sku.entity.SKU;
import com.djdu.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @ClassName ShoppingCart
 * @Description TODO 购物车表
 * @Author DJDU
 * @Date 2019/2/20 17:25
 * @Version 1.0
 **/

@Data
@Entity
@Table(name = "shoppingCart")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class ShoppingCart extends BaseEmtity2 {
    @Id
    @Column(name = "id")
    private String id;

    private String shoppingCart_id;//购物车内商品id

    private String goods_id;

    private  String sku_id;

    private  String user_id;

    private String goodsName;//商品名称

    private int goodsCount;//商品数量

    private double price;//价格

    private String sku; // SKU

    private int state;//状态，是否已付款 1未付款 2已付款

    private String img;

    private double zprice;

}
