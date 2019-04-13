package com.djdu.category.entity;

import com.djdu.common.base.BaseEmtity;
import com.djdu.goods.entity.Goods;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CategoryThird
 * @Description TODO 三级分类表实体，存放分类信息，如笔记本、cpu、电源
 * @Author DJDU
 * @Date 2019/2/17 21:07
 * @Version 1.0
 **/
@Data
@Entity
@Table(name = "categoryThird")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class CategoryThird extends BaseEmtity {
    @Id
    @Column(name = "categoryThird_id")
    private String categoryThird_id;//分类的id

    private String name;//分类名

    private Integer morder;//同级分类顺序

    private String img;//分类照片

    private String categorySecond_id;//父，二级分类
}
