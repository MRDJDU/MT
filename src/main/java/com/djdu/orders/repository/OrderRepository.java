package com.djdu.orders.repository;

import com.djdu.orderDetails.entity.OrderDetails;
import com.djdu.orders.entity.Orders;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName OrderRepository
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/10 14:51
 * @Version 1.0
 **/
@Repository
public interface OrderRepository extends PagingAndSortingRepository<Orders,String>, JpaSpecificationExecutor<Orders> {
    /**
     * @Author DJDU
     * @Description TODO 通过名字查找表中是否存在同名的未删除的用户名
     * @Date 2019/3/11 11:10
     * @Param [name]
     * @return java.lang.Integer
     **/
    @Query(value = "select count(*) from orders a where a.state = 1 AND a.user_id = ?1", nativeQuery = true)
    Integer Pay_num(String user_id);

    @Query(value = "select count(*) from orders a where a.state = 3 AND a.user_id = ?1", nativeQuery = true)
    Integer Rec_num(String user_id);

    @Query(value = "select count(*) from orders a where a.state = 4 AND a.user_id = ?1", nativeQuery = true)
    Integer Finish_num(String user_id);

}
