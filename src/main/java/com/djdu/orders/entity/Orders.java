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

    private BigDecimal priceCount;//商品总额

    private boolean state;//是否已完成

    @ManyToOne(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;//用户，父

    @OneToMany(cascade = CascadeType.ALL,mappedBy="orders",fetch= FetchType.LAZY)
    private List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();//子，订单详情

    @OneToOne(cascade= CascadeType.ALL,fetch= FetchType.LAZY,mappedBy="orders")
    private Logistics logistics;//子，物流表
}
