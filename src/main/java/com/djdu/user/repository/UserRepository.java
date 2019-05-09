package com.djdu.user.repository;

import com.djdu.sku.entity.SKU;
import com.djdu.user.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName UserRepository
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/8 16:01
 * @Version 1.0
 **/
@Repository
public interface UserRepository extends PagingAndSortingRepository<User,String>, JpaSpecificationExecutor<User>{
}