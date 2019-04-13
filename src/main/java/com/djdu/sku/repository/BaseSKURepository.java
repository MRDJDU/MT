package com.djdu.sku.repository;

import com.djdu.norms.entity.BaseNorms;
import com.djdu.sku.entity.BaseSKU;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName BaseSKURepository
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/12 21:25
 * @Version 1.0
 **/
@Repository
public interface BaseSKURepository  extends PagingAndSortingRepository<BaseSKU,String>, JpaSpecificationExecutor<BaseSKU> {
    @Query(value = "select count(*) from basesku a where a.name = ?1 AND a.usable = 0 AND a.categoryThird_id = ?2", nativeQuery = true)
    Integer UnDeletedfindExistsName(String name , String categoryThird_id);
}
