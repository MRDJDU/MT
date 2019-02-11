package com.djdu.entity;

import com.djdu.common.ShowOut;
import com.djdu.common.Usable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * @ClassName Category
 * @Description 分类表实体，存放分类信息，如维修服务，电脑整机，手机配件等
 * @Author DJDU
 * @Date 2019/1/27 19:58
 * @Version 1.0
 **/
@Entity
@Table(name = "category")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Category{
    @Id
    @Column(name = "category_id")
    String category_id;//分类的id

    String name;//分类名

    Usable usable;//分类是否被删除

    ShowOut showOut;//是否展示


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


    /**
     * @Author DJDU
     * @Description 重写toString
     * @Date 2019/1/26 21:26
     * @Param []
     * @return java.lang.String
     **/
    public String toString(){
        return "category_id:"+category_id+" "+"name:"+name+" "+"usable"+usable+" "+"showOut:"+showOut;
    }

}
