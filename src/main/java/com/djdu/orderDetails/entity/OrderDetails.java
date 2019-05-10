package com.djdu.orderDetails.entity;

import com.djdu.common.base.BaseEmtity2;
import com.djdu.evaluation.entity.Evaluation;
import com.djdu.goods.entity.Goods;
import com.djdu.orders.entity.Orders;
import com.djdu.sku.entity.SKU;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @ClassName OrderDetails
 * @Description TODO 订单详情表
 * @Author DJDU
 * @Date 2019/2/20 19:51
 * @Version 1.0
 **/
@Data
@Entity
@Table(name = "orderDetails")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class OrderDetails extends BaseEmtity2 {
    @Id
    @Column(name = "orderDetails_id")
    private String orderDetails_id;//订单详情id

    private String name;//商品名称

    private int num;//数量

    private double price;//价格，单价

    private double zprice;//单件商品总价

    private String photo_x;

    private String orders_id;

    private String id;
}
