package com.djdu.shoppingCart.dto;

import com.djdu.brand.dto.BrandDto;
import com.djdu.brand.entity.Brand;
import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Enums.Usable;
import com.djdu.shoppingCart.entity.ShoppingCart;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ShoppingCartDto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/10 5:01
 * @Version 1.0
 **/
@Data
public class ShoppingCartDto {
    private String shoppingCart_id;//购物车内商品id

    private String goods_id;

    private  String sku_id;

    private  String user_id;

    private String goodsName;//商品名称

    private int goodsCount;//商品数量

    private BigDecimal price;//价格

    private String sku; // SKU

    private int state;//状态，是否已付款 1未付款 2已付款

    private String img;

    @SuppressWarnings({"serial"})
    public static Specification<ShoppingCart> getWhereClause(final ShoppingCartDto shoppingCartDto){
        return new Specification<ShoppingCart>(){
            @Override
            public Predicate toPredicate(Root<ShoppingCart> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();

                //查询品牌名不为空的话，拼装查询条件
                if(StringUtils.isNotBlank(shoppingCartDto.getUser_id())){
                    predicate.add(criteriaBuilder.like(root.get("user_id").as(String.class),
                            "%"+shoppingCartDto.getUser_id()+"%"
                    ));
                }

                //查询品牌名不为空的话，拼装查询条件
                if(StringUtils.isNotBlank(shoppingCartDto.getShoppingCart_id())){
                    predicate.add(criteriaBuilder.like(root.get("shoppingCart_id").as(String.class),
                            "%"+shoppingCartDto.getShoppingCart_id()+"%"
                    ));
                }
                Predicate[] pre =new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }


}
