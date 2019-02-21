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
    @Column(name = "shoppingCart_id")
    private String shoppingCart_id;//购物车内商品id

    private String goodsName;//商品名称

    private int goodsCount;//商品数量

    private BigDecimal price;//价格

    private boolean state;//状态，是否已付款

    @OneToOne(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    private Goods goods;//商品

    @OneToOne(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    private SKU sku;//商品库存进出计量单位

    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;//父，用户
}
