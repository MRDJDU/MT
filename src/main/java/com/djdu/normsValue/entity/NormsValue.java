package com.djdu.normsValue.entity;

import com.djdu.common.base.BaseEmtity;
import com.djdu.norms.entity.Norms;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName NormsValue
 * @Description TODO 商品规格值，同种商品的规格有多种不同的值
 * @Author DJDU
 * @Date 2019/2/20 11:50
 * @Version 1.0
 **/

@Data
@Entity
@Table(name = "normsValue")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class NormsValue extends BaseEmtity {

    @Id
    @Column(name = "normsValue_id")
    private String normsValue_id;//商品规格值id

    private String value;//商品规格值

    private String norms_id;//规格id

}
