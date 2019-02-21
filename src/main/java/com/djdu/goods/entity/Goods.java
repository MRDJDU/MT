package com.djdu.goods.entity;

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

    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "categoryThird_id")
    private CategoryThird categoryThird;//父，三级分类b

    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;//父，品牌

    @OneToMany(cascade = CascadeType.ALL,mappedBy="goods",fetch=FetchType.LAZY)
    private List<ImageResource> imageResource = new ArrayList<ImageResource>();//子，图片资源

    @OneToMany(cascade = CascadeType.ALL,mappedBy="goods",fetch=FetchType.LAZY)
    private List<Norms> norms = new ArrayList<Norms>();//子，规格

    @OneToMany(cascade = CascadeType.ALL,mappedBy="goods",fetch=FetchType.LAZY)
    private List<SKU> SKUs = new ArrayList<SKU>();//子，库存进出计量单位


}