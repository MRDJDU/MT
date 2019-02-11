package com.djdu.repository;

import com.djdu.entity.Category;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName CategoryRepository
 * @Description 仓库层，进行对数据库的各种操作，JPA
 * @Author DJDU
 * @Date 2019/1/27 20:08
 * @Version 1.0
 **/

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category,String>, JpaSpecificationExecutor<Category> {

}
