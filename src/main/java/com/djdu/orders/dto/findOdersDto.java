package com.djdu.orders.dto;

import com.djdu.orders.entity.Orders;
import com.djdu.shoppingCart.dto.ShoppingCartDto;
import com.djdu.shoppingCart.entity.ShoppingCart;
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
 * @ClassName findOdersDto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/10 17:54
 * @Version 1.0
 **/
@Data
public class findOdersDto {
    private  String user_id;
    private String order_type;
    private  int state;


    @SuppressWarnings({"serial"})
    public static Specification<Orders> getWhereClause(final findOdersDto findOdersDtos){
        return new Specification<Orders>(){
            @Override
            public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();
                //查询品牌名不为空的话，拼装查询条件
                if(StringUtils.isNotBlank(findOdersDtos.getUser_id())){
                    predicate.add(criteriaBuilder.like(root.get("user_id").as(String.class),
                            "%"+findOdersDtos.getUser_id()+"%"
                    ));
                }

                predicate.add(criteriaBuilder.equal(root.get("state").as(Integer.class),
                        findOdersDtos.getState()
                ));

                Predicate[] pre =new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

}
