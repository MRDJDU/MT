package com.djdu.goods.dto;

import lombok.Data;

/**
 * @ClassName SKUDto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 16:33
 * @Version 1.0
 **/
@Data
public class SKUDto {
    private String value;//库存进出计量单位

    private int price;//价格

    private int stock;//库存
}
