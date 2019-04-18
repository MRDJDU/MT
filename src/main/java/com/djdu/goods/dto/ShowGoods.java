package com.djdu.goods.dto;

import com.djdu.common.base.BaseEmtity;
import com.djdu.norms.entity.Norms;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ShowGoods
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/18 15:30
 * @Version 1.0
 **/
@Data
public class ShowGoods extends BaseEmtity {
    private String goods_id;//商品id

    private String goodsName;//商品名

    private String category_id;//父，三级分类b

    private String brand_id;//父，品牌

    private String category;//分类名

    private String brand;//品牌名

    private List<Norms> norms; //基础属性

    private String masterImage;//商品主图

    private List<detailsImageDto> detailsImage;//商品详情图
}
