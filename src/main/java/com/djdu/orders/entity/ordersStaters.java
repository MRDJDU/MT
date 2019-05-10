package com.djdu.orders.entity;

import lombok.Data;

/**
 * @ClassName ordersStaters
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/10 17:22
 * @Version 1.0
 **/
@Data
public class ordersStaters {
    private int finish_num;//已完成
    private int pay_num;//待付款
    private int rec_num;//待收货
}
