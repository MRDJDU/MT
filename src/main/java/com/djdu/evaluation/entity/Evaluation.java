package com.djdu.evaluation.entity;

import com.djdu.common.base.BaseEmtity2;
import com.djdu.goods.entity.Goods;
import com.djdu.orderDetails.entity.OrderDetails;
import com.djdu.sku.entity.SKU;
import com.djdu.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName Evaluation
 * @Description TODO 评论表
 * @Author DJDU
 * @Date 2019/2/20 20:17
 * @Version 1.0
 **/

@Data
@Entity
@Table(name = "evaluation")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Evaluation extends BaseEmtity2 {

    @Id
    @Column(name = "evaluation_id")
    private String evaluation_id;//评论表id

    private String comment;//评论内容

    private int grade; //评论分数

    private String name;//评论人昵称

    @ManyToOne(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;//父，用户



    @OneToOne(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    private SKU sku;//单向，商品进存基础单位

    @OneToOne(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    @JoinColumn(name = "orderDetails_id")
    private OrderDetails orderDetails;//父，订单商品详情
}
