package com.djdu.sku.repository;

import com.djdu.goods.entity.Goods;
import com.djdu.sku.entity.SKU;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName SKURepository
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 23:03
 * @Version 1.0
 **/

@Repository
public interface SKURepository extends PagingAndSortingRepository<SKU,String>, JpaSpecificationExecutor<SKU> {
}
