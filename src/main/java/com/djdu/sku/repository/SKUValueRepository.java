package com.djdu.sku.repository;

import com.djdu.goods.entity.Goods;
import com.djdu.sku.entity.SKUValue;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName SKUValueRepository
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 23:03
 * @Version 1.0
 **/
@Repository
public interface SKUValueRepository extends PagingAndSortingRepository<SKUValue,String>, JpaSpecificationExecutor<SKUValue> {
}
