package com.djdu.goods.repository;

import com.djdu.Manage.entity.Manage;
import com.djdu.goods.entity.Goods;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName GoodsRepository
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 23:40
 * @Version 1.0
 **/
@Repository
public interface GoodsRepository extends PagingAndSortingRepository<Goods,String>, JpaSpecificationExecutor<Goods> {

}
