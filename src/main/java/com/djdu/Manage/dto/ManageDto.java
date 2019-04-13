package com.djdu.Manage.dto;

import com.djdu.Manage.entity.Manage;
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

    private Integer state;//管理员状态，0，可用，1，停用

    private Integer grade;//管理员等级，0-超级管理员 1-普通管理员，超级管理员只能脚本添加

    @SuppressWarnings({"serial"})
    public static Specification<Manage> getWhereClause(final ManageDto manageDto){
        return new Specification<Manage>(){
            @Override
            public Predicate toPredicate(Root<Manage> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();

                //通过名字模糊查询
                if(StringUtils.isNotBlank(manageDto.getName())){
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class),
                            "%"+manageDto.getName()+"%"
                    ));
                }
                //通过用户状态查询
                if(manageDto.getState()!=null){
                    predicate.add(criteriaBuilder.equal(root.get("state").as(Integer.class),
                            manageDto.getState()
                    ));
                }
                //查询维修管理员或购物管理员
                if(manageDto.getGrade()!=null){
                    predicate.add(criteriaBuilder.equal(root.get("grade").as(Integer.class),
                            manageDto.getGrade()
                    ));
                }
                //默认不能查询超级管理员
                predicate.add(criteriaBuilder.notEqual(root.get("grade").as(Integer.class),
                        0
                ));
                predicate.add(criteriaBuilder.notEqual(root.get("grade").as(Integer.class),
                        3
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
