package com.djdu.category.repository;

import com.djdu.category.entity.CategorySecond;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName CategorySecondRepository
 * @Description TODO 二级分类仓库层，用于操作数据库
 * @Author DJDU
 * @Date 2019/2/17 21:37
 * @Version 1.0
 **/
@Repository
public interface CategorySecondRepository extends PagingAndSortingRepository<CategorySecond,String>, JpaSpecificationExecutor<CategorySecond> {
    /**
     * @Author DJDU
     * @Description TODO 通过名字查找表中是否存在同一一级分类同名的未删除的二级分类名
     * @Date 2019/4/2 22:15
     * @Param [name]
     * @return java.lang.Integer
     **/
    @Query(value = "select count(*) from categorysecond a where a.name = ?1 AND a.usable = 0 AND a.categoryFirst_id = ?2", nativeQuery = true)
    Integer UnDeletedfindExistsName(String name,String categoryFirst_id);
}
