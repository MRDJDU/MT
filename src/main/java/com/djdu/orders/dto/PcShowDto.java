package com.djdu.orders.dto;

import com.djdu.orderDetails.entity.OrderDetails;
import com.djdu.orders.entity.Orders;
import lombok.Data;

import java.util.List;

/**
 * @ClassName PcShowDto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/10 23:36
 * @Version 1.0
 **/
@Data
public class PcShowDto {
    private Orders orders;
    private List<OrderDetails> orderDetailsList;
}
