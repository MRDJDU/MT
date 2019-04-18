package com.djdu.norms.dto;

import com.djdu.common.Enums.Usable;
import com.djdu.goods.dto.GoodsDto;
import com.djdu.goods.entity.Goods;
import com.djdu.norms.entity.Norms;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName NormsDto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 22:55
 * @Version 1.0
 **/
@Data
public class NormsDto {

    private String norms_id;//商品基础表id

    private String name;//商品基础表名称

    private String value;//商品基础表值

    private String goods_id;//父，商品

    @SuppressWarnings({"serial"})
    public static Specification<Norms> getWhereClause(final NormsDto normsDto){
        return new Specification<Norms>(){
            @Override
            public Predicate toPredicate(Root<Norms> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();

                if(StringUtils.isNotBlank(normsDto.getGoods_id())){
                    predicate.add(criteriaBuilder.like(root.get("goods_id").as(String.class),
                            "%"+normsDto.getGoods_id()+"%"
                    ));
                }

                Predicate[] pre =new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
