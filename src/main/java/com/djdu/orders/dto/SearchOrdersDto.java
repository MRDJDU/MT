package com.djdu.orders.dto;

import com.djdu.orders.entity.Orders;
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
 * @ClassName SearchOrdersDto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/10 23:40
 * @Version 1.0
 **/
@Data
public class SearchOrdersDto {
    private int state;

    @SuppressWarnings({"serial"})
    public static Specification<Orders> getWhereClause(final SearchOrdersDto searchOrdersDto){
        return new Specification<Orders>(){
            @Override
            public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();
                //查询品牌名不为空的话，拼装查询条件
                if(searchOrdersDto.getState()!=0){
                    predicate.add(criteriaBuilder.equal(root.get("state").as(Integer.class),
                            searchOrdersDto.getState()
                    ));
                }

                predicate.add(criteriaBuilder.notEqual(root.get("state").as(Integer.class),
                        1
                ));
                Predicate[] pre =new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
