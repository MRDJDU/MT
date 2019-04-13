package com.djdu.sku.dto;

import com.djdu.common.Enums.Usable;
import com.djdu.norms.dto.BaseNormsDto;
import com.djdu.norms.entity.BaseNorms;
import com.djdu.sku.entity.BaseSKU;
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
 * @ClassName BaseSKUDto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/12 21:41
 * @Version 1.0
 **/
@Data
public class BaseSKUDto {

    private String baseSKU_id;//库存进出计量单位id

    private String name;//库存进出计量单位名称

    private String categoryThird_id;//父，三级分类

    @SuppressWarnings({"serial"})
    public static Specification<BaseSKU> getWhereClause(final BaseSKUDto baseSKUDto){
        return new Specification<BaseSKU>(){
            @Override
            public Predicate toPredicate(Root<BaseSKU> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();

                //查询品牌名不为空的话，拼装查询条件
                if(StringUtils.isNotBlank(baseSKUDto.getCategoryThird_id())){
                    predicate.add(criteriaBuilder.like(root.get("categoryThird_id").as(String.class),
                            "%"+baseSKUDto.getCategoryThird_id()+"%"
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
}
