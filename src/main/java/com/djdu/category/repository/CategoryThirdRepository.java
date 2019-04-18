package com.djdu.category.repository;

import com.djdu.category.entity.CategorySecond;
import com.djdu.category.entity.CategoryThird;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @InterfaceName CategoryThirdRepository
 * @Description TODO 三级分类仓库层，用于操作数据库
 * @Author DJDU
 * @Date 2019/2/17 21:37
 * @Version 1.0
 **/
@Repository
public interface CategoryThirdRepository extends PagingAndSortingRepository<CategoryThird,String>, JpaSpecificationExecutor<CategoryThird> {
    /**
     * @Author DJDU
     * @Description TODO 通过名字查找表中是否存在同一二级分类同名的未删除的三级分类名
     * @Date 2019/4/2 22:15
     * @Param [name]
     * @return java.lang.Integer
     **/
    @Query(value = "select count(*) from categorythird a where a.name = ?1 AND a.usable = 0 AND a.categorySecond_id = ?2", nativeQuery = true)
    Integer UnDeletedfindExistsName(String name,String categorySecond_id);

    @Query(value = "select * from categorythird a where a.categoryThird_id = ?1 AND a.usable = 0", nativeQuery = true)
    Optional<CategoryThird> findByID(String  ID);
}
