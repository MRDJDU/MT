package com.djdu.norms.entity;

import com.djdu.common.base.BaseEmtity;
import com.djdu.goods.entity.Goods;
import com.djdu.normsValue.entity.NormsValue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName
 * @Description TODO 商品规格表,一种商品有多种规格
 * @Author DJDU
 * @Date 2019/2/20 1:01
 * @Version 1.0
 **/

@Data
@Entity
@Table(name = "norms")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Norms extends BaseEmtity {
    @Id
    @Column(name = "norms_id")
    private String norms_id;//规格id

    private String name;//规格名称

    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;//父，商品
}
