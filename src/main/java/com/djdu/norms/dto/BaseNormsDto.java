package com.djdu.norms.dto;

import com.djdu.brand.dto.BrandDto;
import com.djdu.brand.entity.Brand;
import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Enums.Usable;
import com.djdu.norms.entity.BaseNorms;
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
 * @ClassName BaseNormsDto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/12 4:31
 * @Version 1.0
 **/
@Data
public class BaseNormsDto {

    private String baseNorms_id;

    private String name;//规格名称

    private String categoryThird_id;//分类的id

    @SuppressWarnings({"serial"})
    public static Specification<BaseNorms> getWhereClause(final BaseNormsDto baseNormsDto){
        return new Specification<BaseNorms>(){
            @Override
            public Predicate toPredicate(Root<BaseNorms> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();

                //查询品牌名不为空的话，拼装查询条件
                if(StringUtils.isNotBlank(baseNormsDto.getCategoryThird_id())){
                    predicate.add(criteriaBuilder.like(root.get("categoryThird_id").as(String.class),
                            "%"+baseNormsDto.getCategoryThird_id()+"%"
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
