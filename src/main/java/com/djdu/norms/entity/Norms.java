package com.djdu.norms.entity;

import com.djdu.common.base.BaseEmtity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName
 * @Description TODO 商品基础表
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
    private String norms_id;//商品基础表id

    private String name;//商品基础表名称

    private String value;//商品基础表值

    private String goods_id;//父，商品
}
