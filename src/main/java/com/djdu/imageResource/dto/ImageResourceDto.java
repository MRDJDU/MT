package com.djdu.imageResource.dto;

import com.djdu.common.Enums.ImageCategory;
import com.djdu.common.Enums.Usable;
import com.djdu.imageResource.entity.ImageResource;
import com.djdu.norms.dto.NormsDto;
import com.djdu.norms.entity.Norms;
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
 * @ClassName ImageResourceDto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 22:52
 * @Version 1.0
 **/
@Data
public class ImageResourceDto {
    private String imageResource_id;//图片id

    private String ImageURL;//图片路径

    private ImageCategory imageCategory;//图片分类，主图和详情图

    private Integer morder;//同图片分类展示时顺序

    private String goods_id;//父，商品

    @SuppressWarnings({"serial"})
    public static Specification<ImageResource> getWhereClause(final ImageResourceDto imageResourceDto){
        return new Specification<ImageResource>(){
            @Override
            public Predicate toPredicate(Root<ImageResource> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder){
                List<Predicate> predicate = new ArrayList<>();

                if(StringUtils.isNotBlank(imageResourceDto.getGoods_id())){
                    predicate.add(criteriaBuilder.like(root.get("goods_id").as(String.class),
                            "%"+imageResourceDto.getGoods_id()+"%"
                    ));
                }

                //默认已删除的不能查询
                predicate.add(criteriaBuilder.equal(root.get("imageCategory").as(ImageCategory.class),
                        imageResourceDto.getImageCategory()
                ));

                Predicate[] pre =new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
