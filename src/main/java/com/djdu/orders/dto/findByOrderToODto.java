package com.djdu.orders.dto;

import com.djdu.orderDetails.entity.OrderDetails;
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
 * @ClassName findByOrderToODto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/10 18:35
 * @Version 1.0
 **/
@Data
public class findByOrderToODto {
    private String orders_id;

    @SuppressWarnings({"serial"})
    public static Specification<OrderDetails> getWhereClause(final findByOrderToODto findByOrderToODtos){
        return new Specification<OrderDetails>(){
            @Override
            public Predicate toPredicate(Root<OrderDetails> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();
                //查询品牌名不为空的话，拼装查询条件
                if(StringUtils.isNotBlank(findByOrderToODtos.getOrders_id())){
                    predicate.add(criteriaBuilder.like(root.get("orders_id").as(String.class),
                            "%"+findByOrderToODtos.getOrders_id()+"%"
                    ));
                }

                Predicate[] pre =new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }


}
