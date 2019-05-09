package com.djdu.goods.dto;

import com.djdu.common.Enums.Usable;
import com.djdu.goods.entity.Goods;
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
 * @ClassName GoodsDto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/18 14:32
 * @Version 1.0
 **/
@Data
public class GoodsDto {
    private String goods_id;//商品id

    private String name;//商品名

    private String category_id;//父，三级分类b

    private String brand_id;//父，品牌

    private int type;


    @SuppressWarnings({"serial"})
    public static Specification<Goods> getWhereClause(final GoodsDto goodsDto){
        return new Specification<Goods>(){
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();

                //通过名字模糊查询
                if(StringUtils.isNotBlank(goodsDto.getName())){
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class),
                            "%"+goodsDto.getName()+"%"
                    ));
                }

                if(StringUtils.isNotBlank(goodsDto.getGoods_id())){
                    predicate.add(criteriaBuilder.like(root.get("goods_id").as(String.class),
                            "%"+goodsDto.getGoods_id()+"%"
                    ));
                }

                if(StringUtils.isNotBlank(goodsDto.getCategory_id())){
                    predicate.add(criteriaBuilder.like(root.get("category_id").as(String.class),
                            "%"+goodsDto.getCategory_id()+"%"
                    ));
                }

                if(goodsDto.getType()!=0){
                    predicate.add(criteriaBuilder.equal(root.get("type").as(int.class),
                            goodsDto.getType()
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
