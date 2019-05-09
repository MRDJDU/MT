package com.djdu.goods.entity;

import com.djdu.evaluation.entity.Evaluation;
import com.djdu.imageResource.entity.ImageResource;
import com.djdu.brand.entity.Brand;
import com.djdu.category.entity.CategoryThird;
import com.djdu.common.base.BaseEmtity;
import com.djdu.norms.entity.Norms;
import com.djdu.sku.entity.SKU;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Goods
 * @Description TODO 商品实体表,存放商品，如iphone6 32g 、华硕F555L
 * @Author DJDU
 * @Date 2019/2/18 23:28
 * @Version 1.0
 **/
@Data
@Entity
@Table(name = "goods")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Goods extends BaseEmtity {
    @Id
    @Column(name = "goods_id")
    private String goods_id;//商品id

    private String name;//商品名

    private String category_id;//父，三级分类b

    private int type;

    private String brand_id;//父，品牌
}