package com.djdu.logistics.entity;

import com.djdu.common.base.BaseEmtity2;
import com.djdu.orders.entity.Orders;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Logistics
 * @Description TODO 物流表
 * @Author DJDU
 * @Date 2019/2/20 20:01
 * @Version 1.0
 **/

@Data
@Entity
@Table(name = "logistics")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Logistics extends BaseEmtity2 {
    @Id
    @Column(name = "logistics_id")
    private String logistics_id;//物流表id

    private String name;//物流公司名

    private String number;//快递单号

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date outTime;//发货时间

    @JsonFormat(pattern="yyyy/MM/dd HH:mm:ss",timezone="GMT+8")
    private Date getTime;//收货时间

    @OneToMany(cascade = CascadeType.ALL,mappedBy="logistics",fetch= FetchType.LAZY)
    private List<GetLogistics> getLogisticsDto = new ArrayList<GetLogistics>();;//子，第三方接口返回来的结果信息

    @OneToOne(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;//父，订单，一对一
}
