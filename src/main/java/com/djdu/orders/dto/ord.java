package com.djdu.orders.dto;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName ord
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/10 20:16
 * @Version 1.0
 **/
@Data
public class ord {
    private String address_xq;
    private Date addtime;
    private String id;
    private String order_sn;
    private String order_status;
    private double price;
    private String receiver;
    private String tel;
}
