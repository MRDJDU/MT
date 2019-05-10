package com.djdu.orderDetails.repository;

import com.djdu.orderDetails.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName OrderDetailsRepository
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/10 14:50
 * @Version 1.0
 **/
@Repository
public interface OrderDetailsRepository extends PagingAndSortingRepository<OrderDetails,String>, JpaSpecificationExecutor<OrderDetails> {
}
