package com.djdu.category.entity;

import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Enums.Usable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Category
 * @Description TODO 分类表实体，存放分类信息，如维修服务，电脑整机，手机配件等,分类自关联，有多级的分类
 * @Author DJDU
 * @Date 2019/1/27 19:58
 * @Version 1.0
 **/
@Entity
@Table(name = "category")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Category {
    @Id
    @Column(name = "category_id")
    private String category_id;//分类的id

    private String name;//分类名

    private Usable usable;//分类是否被删除

    private ShowOut showOut;//是否展示

    private Integer grade;//分类等级

    private Integer morder;//同级分类顺序

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Category parentCategory;//父分类

    @OneToMany(targetEntity = Category.class, cascade = CascadeType.ALL, mappedBy = "parentCategory")
    @Fetch(FetchMode.SUBSELECT)
    private List<Category> childCategory = new ArrayList<Category>();//子分类


    public String getCategory_id() {
        return category_id;
    }

    public String getName() {
        return name;
    }

    public Usable getUsable() {
        return usable;
    }

    public ShowOut getShowOut() {
        return showOut;
    }

    public Integer getGrade() {
        return grade;
    }

    public Integer getMorder() {
        return morder;
    }

    @JsonIgnore
    public Category getParentCategory() {
        return parentCategory;
    }


    public List<Category> getChildCategory() {
        return childCategory;
    }




    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsable(Usable usable) {
        this.usable = usable;
    }

    public void setShowOut(ShowOut showOut) {
        this.showOut = showOut;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void setMorder(Integer morder) {
        this.morder = morder;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public void setChildCategory(List<Category> childCategory) {
        this.childCategory = childCategory;
    }


    /**
     * @Author DJDU
     * @Description TODO 父分类添加子分类
     * @Date 2019/2/14 11:49
     * @Param [category]
     * @return void
     **/
    public void addChildCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Can't add a null Category as child.");
        }
        // 删除旧的父类别Category
        if (category.getParentCategory() != null) {
            category.getParentCategory().getChildCategory().remove(category);
        }
        // 设置新的父类别Category
        category.setParentCategory(this);
        // 向当前Category对象中加入子类别
        this.getChildCategory().add(category);
    }


    /**
     * @Author DJDU
     * @Description TODO 重写toString
     * @Date 2019/1/26 21:26
     * @Param []
     * @return java.lang.String
     **/
    public String toString(){
        return "category_id:"+category_id+" "+"name:"+name+" "+"usable"+usable+" "+"showOut:"+showOut;
    }

}
