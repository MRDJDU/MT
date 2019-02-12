package com.djdu.category.dto;

import com.djdu.category.entity.Category;
import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Enums.Usable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CategoryDto
 * @Description Category的DTO
 * @Author DJDU
 * @Date 2019/1/27 20:42
 * @Version 1.0
 **/
public class CategoryDto {

    private String name;//分类名

    private ShowOut showOut;//是否展示

    public String getName() {
        return name;
    }

    public ShowOut getShowOut() {
        return showOut;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShowOut(ShowOut showOut) {
        this.showOut = showOut;
    }


    /**
     * @Author DJDU
     * @Description 拼装高级自定义查询条件，动态条件组装
     * @Date 2019/2/11 17:03 
     * @Param [categoryDto]
     * @return org.springframework.data.jpa.domain.Specification<com.djdu.entity.Category>
     **/
    @SuppressWarnings({"serial"})
    public static Specification<Category> getWhereClause(final CategoryDto categoryDto){
        return new Specification<Category>(){
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();

                if(StringUtils.isNotBlank(categoryDto.getName())){
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class),
                            "%"+categoryDto.getName()+"%"
                            ));
                }

                if(null!=categoryDto.getShowOut()){
                    predicate.add(criteriaBuilder.equal(root.get("showOut").as(ShowOut.class),
                            categoryDto.getShowOut()
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
    /**
     * @Author DJDU
     * @Description 重写toString
     * @Date 2019/1/26 21:26
     * @Param []
     * @return java.lang.String
     **/
    public String toString(){
        return "name:"+name+" "+"showOut:"+showOut;
    }

}
