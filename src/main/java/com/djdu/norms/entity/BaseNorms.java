package com.djdu.norms.entity;

import com.djdu.brand.dto.BrandDto;
import com.djdu.brand.entity.Brand;
import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Enums.Usable;
import com.djdu.common.base.BaseEmtity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BaseNorms
 * @Description TODO 基础规格属性
 * @Author DJDU
 * @Date 2019/4/12 4:22
 * @Version 1.0
 **/

@Data
@Entity
@Table(name = "basenorms")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class BaseNorms extends BaseEmtity {
    @Id
    @Column(name = "baseNorms_id")
    private String baseNorms_id;

    private String name;//规格名称

    private String categoryThird_id;//分类的id


}