package com.djdu.Manage.dto;

import com.djdu.Manage.entity.Manage;
import com.djdu.category.dto.CategoryDto;
import com.djdu.common.Enums.ShowOut;
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
 * @ClassName ManageDto
 * @Description TODO 管理员Dto
 * @Author DJDU
 * @Date 2019/3/12 18:35
 * @Version 1.0
 **/

@Data
public class ManageDto {
    private String manage_id;//管理员ID

    private String name;//管理员登陆名

    private String password;//管理员密码

    private String img;//管理员头像

    private boolean state;//管理员状态，0，可用，1，停用

    @SuppressWarnings({"serial"})
    public static Specification<Manage> getWhereClause(final ManageDto manageDto){
        return new Specification<Manage>(){
            @Override
            public Predicate toPredicate(Root<Manage> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();

                if(StringUtils.isNotBlank(manageDto.getName())){
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class),
                            "%"+manageDto.getName()+"%"
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
