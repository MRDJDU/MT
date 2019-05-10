package com.djdu.orders.dto;

import com.djdu.orderDetails.entity.OrderDetails;
import com.djdu.orders.entity.addr;
import lombok.Data;

import java.util.List;

/**
 * @ClassName addOderDto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/10 14:18
 * @Version 1.0
 **/
@Data
public class addOderDto {
    public List<OrderDetails> orderDetails;
    public addr add;
    public double total;
    public String user_id;
}
