package com.djdu.sku.entity;

import com.djdu.common.base.BaseEmtity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName SKU
 * @Description TODO 库存进出计量单位
 * @Author DJDU
 * @Date 2019/2/20 13:35
 * @Version 1.0
 **/
@Data
@Entity
@Table(name = "SKU")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class SKU extends BaseEmtity {
    @Id
    @Column(name = "SKU_id")
    private String SKU_id;//库存进出计量单位id

    private String name;//库存进出计量单位id

    private String goods_id;//父，商品
}
