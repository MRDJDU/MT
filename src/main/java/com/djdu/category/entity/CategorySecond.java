package com.djdu.category.entity;

import com.djdu.common.base.BaseEmtity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CategorySecond
 * @Description TODO 二级分类表实体，存放分类信息，如电脑整机，电脑配件
 * @Author DJDU
 * @Date 2019/2/17 21:10
 * @Version 1.0
 **/
@Data
@Entity
@Table(name = "categorySecond")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class CategorySecond extends BaseEmtity {
    @Id
    @Column(name = "categorySecond_id")
    private String categorySecond_id;//分类的id

    private String name;//分类名

    private Integer morder;//同级分类顺序

    @OneToMany(cascade = CascadeType.ALL,mappedBy="categorySecond",fetch=FetchType.LAZY)
    private List<CategoryThird> categoryThird = new ArrayList<CategoryThird>();//子 三级分类

    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "categoryFirst_id")
    private CategoryFirst categoryFirst;//父 一级分类
}
