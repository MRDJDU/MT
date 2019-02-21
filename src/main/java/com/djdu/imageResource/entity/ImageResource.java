package com.djdu.imageResource.entity;

import com.djdu.common.Enums.ImageCategory;
import com.djdu.common.base.BaseEmtity;
import com.djdu.goods.entity.Goods;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName ImageResource
 * @Description TODO 商品图片资源，分为主图和详情图
 * @Author DJDU
 * @Date 2019/2/19 8:50
 * @Version 1.0
 **/
@Data
@Entity
@Table(name = "imageResource")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class ImageResource extends BaseEmtity {
    @Id
    @Column(name = "imageResource_id")
    private String imageResource_id;//图片id

    private String name;//图片名

    private String ImageURL;//图片路径

    private ImageCategory imageCategory;//图片分类，主图和详情图

    private Integer morder;//同图片分类展示时顺序

    @ManyToOne(cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;//父，商品

}
