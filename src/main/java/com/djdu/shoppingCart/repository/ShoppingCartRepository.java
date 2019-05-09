package com.djdu.shoppingCart.repository;

import com.djdu.shoppingCart.entity.ShoppingCart;
import com.djdu.user.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName ShoppingCartRepository
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/9 11:13
 * @Version 1.0
 **/
@Repository
public interface ShoppingCartRepository extends PagingAndSortingRepository<ShoppingCart,String>, JpaSpecificationExecutor<ShoppingCart>{
}