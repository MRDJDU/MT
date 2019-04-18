package com.djdu.sku.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName SKUValue
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 15:06
 * @Version 1.0
 **/
@Data
@Entity
@Table(name = "SKUValue")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class SKUValue {
    @Id
    @Column(name = "SKUValue_id")
    private String SKUValue_id;//库存进出计量单位id

    private String value;//库存进出计量单位id

    private String SKU_id;//父，库存基础属性

    private String group_id;//属于那一组
}
