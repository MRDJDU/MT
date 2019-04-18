package com.djdu.norms.repository;

import com.djdu.goods.entity.Goods;
import com.djdu.norms.entity.Norms;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName NormsRepository
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 22:55
 * @Version 1.0
 **/
@Repository
public interface NormsRepository extends PagingAndSortingRepository<Norms,String>, JpaSpecificationExecutor<Norms> {
}
