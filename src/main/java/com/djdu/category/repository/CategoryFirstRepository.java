package com.djdu.category.repository;

import com.djdu.Manage.entity.Manage;
import com.djdu.category.entity.CategoryFirst;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @InterfaceName CategoryFirstRepository
 * @Description TODO 一级分类仓库层，用于操作数据库，逻辑删除故删除标记量为未删除，0为未删除
 * @Author DJDU
 * @Date 2019/2/17 21:36
 * @Version 1.0
 **/
@Repository
public interface CategoryFirstRepository extends PagingAndSortingRepository<CategoryFirst,String>, JpaSpecificationExecutor<CategoryFirst> {

    /**
     * @Author DJDU
     * @Description TODO 通过名字查找表中是否存在同名的未删除的一级分类名
     * @Date 2019/4/2 22:04
     * @Param [name]
     * @return java.lang.Integer
     **/
    @Query(value = "select count(*) from categoryfirst a where a.name = ?1 AND a.usable = 0", nativeQuery = true)
    Integer UnDeletedfindExistsName(String name);

    @Query(value = "select * from categoryfirst a where a.categoryFirst_id = ?1 AND a.usable = 0", nativeQuery = true)
    Optional<CategoryFirst> findByID(String  ID);
}

