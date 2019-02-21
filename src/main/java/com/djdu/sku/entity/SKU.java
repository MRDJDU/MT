package com.djdu.sku.entity;

import com.djdu.common.base.BaseEmtity;
import com.djdu.goods.entity.Goods;
import com.djdu.normsValue.entity.NormsValue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    private Long stock;//库存

    private BigDecimal price;//价格

    @OneToMany(cascade = CascadeType.ALL,fetch= FetchType.LAZY)
    private List<NormsValue> normsValue = new ArrayList<NormsValue>();//子，商品规格值

    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;//父，商品

}
