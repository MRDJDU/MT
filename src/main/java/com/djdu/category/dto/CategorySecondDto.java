package com.djdu.category.dto;

import com.djdu.category.entity.CategorySecond;
import com.djdu.common.Enums.Usable;
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
 * @ClassName CategorySecondDto
 * @Description TODO 二级分类Dto
 * @Author DJDU
 * @Date 2019/4/9 11:22
 * @Version 1.0
 **/
@Data
public class CategorySecondDto {
    private String categorySecond_id;//分类的id

    private String name;//分类名

    private Integer morder;//同级分类顺序

    private String img;//分类照片

    private String categoryFirst_id;//一级分类的id

    @SuppressWarnings({"serial"})
    public static Specification<CategorySecond> getWhereClause(final CategorySecondDto categorySecondDto){
        return new Specification<CategorySecond>(){
            @Override
            public Predicate toPredicate(Root<CategorySecond> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();

                //通过名字模糊查询
                if(StringUtils.isNotBlank(categorySecondDto.getName())){
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class),
                            "%"+categorySecondDto.getName()+"%"
                    ));
                }


                predicate.add(criteriaBuilder.like(root.get("categoryFirst_id").as(String.class),
                        "%"+categorySecondDto.getCategoryFirst_id()+"%"
                ));

                predicate.add(criteriaBuilder.equal(root.get("categoryFirst_id").as(String.class),
                        categorySecondDto.getCategoryFirst_id()
                ));

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
