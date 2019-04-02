package com.djdu.category.repository;

import com.djdu.category.entity.CategoryFirst;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @InterfaceName CategoryFirstRepository
 * @Description TODO 一级分类仓库层，用于操作数据库
 * @Author DJDU
 * @Date 2019/2/17 21:36
 * @Version 1.0
 **/
public interface CategoryFirstRepository extends PagingAndSortingRepository<CategoryFirst,String>, JpaSpecificationExecutor<CategoryFirst> {
}
