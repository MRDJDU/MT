package com.djdu.category.dto;

import com.djdu.Manage.dto.ManageDto;
import com.djdu.Manage.entity.Manage;
import com.djdu.category.entity.CategoryFirst;
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
 * @ClassName CategoryFirstDto
 * @Description TODO 一级分类Dto
 * @Author DJDU
 * @Date 2019/4/8 8:33
 * @Version 1.0
 **/
@Data
public class CategoryFirstDto {
    private String categoryFirst_id;//分类的id

    private String name;//分类名

    private Integer morder;//同级分类顺序

    private String img;//分类照片

    @SuppressWarnings({"serial"})
    public static Specification<CategoryFirst> getWhereClause(final CategoryFirstDto categoryFirstDto){
        return new Specification<CategoryFirst>(){
            @Override
            public Predicate toPredicate(Root<CategoryFirst> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();

                //通过名字模糊查询
                if(StringUtils.isNotBlank(categoryFirstDto.getName())){
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class),
                            "%"+categoryFirstDto.getName()+"%"
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
