package com.djdu.category.dto;

import com.djdu.category.entity.CategorySecond;
import com.djdu.category.entity.CategoryThird;
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
 * @ClassName CategoryThirdDto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/9 19:05
 * @Version 1.0
 **/
@Data
public class CategoryThirdDto {
    private String categoryThird_id;//分类的id

    private String name;//分类名

    private Integer morder;//同级分类顺序

    private String img;//分类照片

    private String categorySecond_id;//父，二级分类


    @SuppressWarnings({"serial"})
    public static Specification<CategoryThird> getWhereClause(final CategoryThirdDto categoryThirdDto){
        return new Specification<CategoryThird>(){
            @Override
            public Predicate toPredicate(Root<CategoryThird> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();

                //通过名字模糊查询
                if(StringUtils.isNotBlank(categoryThirdDto.getName())){
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class),
                            "%"+categoryThirdDto.getName()+"%"
                    ));
                }


                predicate.add(criteriaBuilder.like(root.get("categorySecond_id").as(String.class),
                        "%"+categoryThirdDto.getCategorySecond_id()+"%"
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
