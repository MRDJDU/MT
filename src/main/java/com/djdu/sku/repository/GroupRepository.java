package com.djdu.sku.repository;

import com.djdu.goods.entity.Goods;
import com.djdu.sku.entity.Group;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName GroupRepository
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 23:02
 * @Version 1.0
 **/
@Repository
public interface GroupRepository extends PagingAndSortingRepository<Group,String>, JpaSpecificationExecutor<Group> {
}
