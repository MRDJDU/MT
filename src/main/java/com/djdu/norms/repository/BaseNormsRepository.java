package com.djdu.norms.repository;

import com.djdu.norms.entity.BaseNorms;
import com.djdu.sku.entity.SKU;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName BaseNormsRepository
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/12 4:29
 * @Version 1.0
 **/
@Repository
public interface BaseNormsRepository extends PagingAndSortingRepository<BaseNorms,String>, JpaSpecificationExecutor<BaseNorms> {
    @Query(value = "select count(*) from basenorms a where a.name = ?1 AND a.usable = 0 AND a.categoryThird_id = ?2", nativeQuery = true)
    Integer UnDeletedfindExistsName(String name , String categoryThird_id);
}
