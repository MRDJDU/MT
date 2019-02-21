package com.djdu.brand.repository;

import com.djdu.brand.entity.Brand;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName BrandRepository
 * @Description TODO 仓库层，进行对数据库的各种操作
 * @Author DJDU
 * @Date 2019/2/21 9:49
 * @Version 1.0
 **/
@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand,String>, JpaSpecificationExecutor<Brand> {
}
