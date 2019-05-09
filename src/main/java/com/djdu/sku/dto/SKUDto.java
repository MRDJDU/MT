package com.djdu.sku.dto;

import com.djdu.norms.dto.NormsDto;
import com.djdu.norms.entity.Norms;
import com.djdu.sku.entity.SKU;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SKUDto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 23:25
 * @Version 1.0
 **/
@Data
public class SKUDto {
    private String SKU_id;//库存进出计量单位id

    private String SKU;//库存进出计量单位

    private int price;//价格

    private int stock;//库存

    private String goods_id;//父，商品

    @SuppressWarnings({"serial"})
    public static Specification<SKU> getWhereClause(final SKUDto skuDto){
        return new Specification<SKU>(){
            @Override
            public Predicate toPredicate(Root<SKU> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();

                if(StringUtils.isNotBlank(skuDto.getGoods_id())){
                    predicate.add(criteriaBuilder.like(root.get("goods_id").as(String.class),
                            "%"+skuDto.getGoods_id()+"%"
                    ));
                }

                Predicate[] pre =new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
