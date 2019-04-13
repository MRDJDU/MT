package com.djdu.brand.entity;

import com.djdu.common.base.BaseEmtity;
import com.djdu.goods.entity.Goods;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName  Brand
 * @Description TODO 品牌实体类，如华硕，联想
 * @Author DJDU
 * @Date 2019/2/19 1:15
 * @Version 1.0
 **/

@Data
@Entity
@Table(name = "brand")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Brand extends BaseEmtity {
    @Id
    @Column(name = "brand_id")
    private String brand_id;//品牌id

    private String name;//品牌名

    private String IcoURL;//Ico路径

    public String Category_id; // 所属分类id

    public String Category_name; // 所属分类id

    @Transient
    public List<String> Category_ids;
}
