package com.djdu.orders.entity;

import com.djdu.common.base.BaseEmtity2;
import com.djdu.logistics.entity.Logistics;
import com.djdu.orderDetails.entity.OrderDetails;
import com.djdu.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Orders
 * @Description TODO 订单表
 * @Author DJDU
 * @Date 2019/2/20 13:15
 * @Version 1.0
 **/

@Data
@Entity
@Table(name = "orders")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Orders extends BaseEmtity2 {
    @Id
    @Column(name = "orders_id")
    private String orders_id;//订单id

    private int itemCount;//商品项数量，不是商品个数，比如手机*2，鼠标*1，这里应该是2

    private double priceCount;//商品总额

    private int state;//是否已完成 1未付款 2等待出货 4待收货 3 确认收货

    private String user_id;//用户id

    private String address_xq;

    private String tel;

    private String name;

}
