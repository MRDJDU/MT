package com.djdu.brand.repository;

import com.djdu.brand.entity.Brand;
import com.djdu.common.Enums.Usable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @InterfaceName BrandRepository
 * @Description TODO 仓库层，进行对数据库的各种操作，逻辑删除故删除标记量为未删除，0为未删除
 * @Author DJDU
 * @Date 2019/2/21 9:49
 * @Version 1.0
 **/
@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand,String>, JpaSpecificationExecutor<Brand> {

    /**
     * @Author DJDU
     * @Description TODO 查询未删除品牌名数据是否存在
     * @Date 2019/2/28 9:00
     * @Param [name]
     * @return java.lang.Integer
     **/
    @Query(value = "select count(*) from brand a where a.name = ?1 AND a.usable = 0", nativeQuery = true)
    Integer UnDeletedfindExistsName(String name);

    /**
     * @Author DJDU
     * @Description TODO 通过名字查询未删除品牌的id
     * @Date 2019/2/28 14:38 
     * @Param [name]
     * @return java.lang.String
     **/
    @Query(value = "select brand_id from brand a where a.name = ?1 AND a.usable = 0", nativeQuery = true)
    String UnDeletedfindNameGetId(String name);
}
