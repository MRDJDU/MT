package com.djdu.goods.dto;

import com.djdu.Manage.dto.ManageDto;
import com.djdu.Manage.entity.Manage;
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
 * @ClassName AddgoodsDto
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 10:05
 * @Version 1.0
 **/
@Data
public class AddgoodsDto {
    public String goodsname;
    public List<String> selectedaddOptions;
    public List<String> selectedaddBrandOptions;
    public List<String> showcropImgs;
    public List<String> othercropImgs;
    public List<NormsDto> normsDtos;
    public List<SKUDto> skuDtos;
}
