package com.djdu.sku.entity;

import com.djdu.common.base.BaseEmtity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName BaseSKU
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/12 21:19
 * @Version 1.0
 **/
@Data
@Entity
@Table(name = "basesku")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class BaseSKU extends BaseEmtity {
    @Id
    @Column(name = "baseSKU_id")
    private String baseSKU_id;//库存进出计量单位id

    private String name;//库存进出计量单位名称

    private String categoryThird_id;//父，三级分类
}
