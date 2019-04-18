package com.djdu.imageResource.repository;

import com.djdu.goods.entity.Goods;
import com.djdu.imageResource.entity.ImageResource;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName ImageResourceRepository
 * @Description TODO
 * @Author DJDU
 * @Date 2019/4/14 22:53
 * @Version 1.0
 **/
@Repository
public interface ImageResourceRepository extends PagingAndSortingRepository<ImageResource,String>, JpaSpecificationExecutor<ImageResource> {
}
