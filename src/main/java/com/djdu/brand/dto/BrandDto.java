package com.djdu.brand.dto;

import com.djdu.brand.entity.Brand;
import com.djdu.common.Enums.Usable;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BrandDto
 * @Description TODO 品牌类Dto，用于接收数据，拼装查询条件
 * @Author DJDU
 * @Date 2019/2/21 10:37
 * @Version 1.0
 **/
@Data
public class BrandDto {
    private String brand_id;//品牌id

    private String name;//品牌名

    private Usable usable;//是否被删除

    /**
     * @Author DJDU
     * @Description TODO 拼装高级自定义查询条件，动态条件组装
     * @Date 2019/2/11 17:03
     * @Param [categoryDto]
     * @return org.springframework.data.jpa.domain.Specification<com.djdu.entity.Category>
     **/
    @SuppressWarnings({"serial"})
    public static Specification<Brand> getWhereClause(final BrandDto brandDto){
        return new Specification<Brand>(){
            @Override
            public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();

                //查询品牌名不为空的话，拼装查询条件
                if(StringUtils.isNotBlank(brandDto.getName())){
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class),
                            "%"+brandDto.getName()+"%"
                            ));
                }

                //默认已删除的不能查询
                predicate.add(criteriaBuilder.equal(root.get("usable").as(Usable.class),
                        Usable.UnDeleted
                ));

                Predicate[] pre =new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }


    public String toString(){
        return "brand_id:"+brand_id+"——"+"name:"+name+"——"+"usable"+usable;
    }
}
