package com.djdu.brand.service;

import com.djdu.brand.entity.Brand;
import com.djdu.brand.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName BrandService
 * @Description TODO 业务逻辑层，调用仓库层接口对数据进行处理
 * @Author DJDU
 * @Date 2019/2/21 9:51
 * @Version 1.0
 **/

@Service
@Transactional//开启事务处理
public class BrandService {
    @Autowired
    BrandRepository brandRepository;//注入品牌仓库层

    /**
     * @Author DJDU
     * @Description TODO 新增品牌实体或修改保存品牌实体
     * @Date 2019/2/21 10:00
     * @Param [brand]
     * @return void
     **/
    public void save(Brand brand){
        brandRepository.save(brand);
    }

    /**
     * @Author DJDU
     * @Description TODO 根据条件分页查找品牌
     * @Date 2019/2/21 10:05
     * @Param [specification, pageable]
     * @return org.springframework.data.domain.Page<com.djdu.brand.entity.Brand>
     **/
    @Transactional(readOnly = true)
    public Page<Brand> findAll(Specification<Brand> specification, Pageable pageable){
        return brandRepository.findAll(specification,pageable);
    }

    /**
     * @Author DJDU
     * @Description TODO 通过id查找品牌
     * @Date 2019/2/21 10:15
     * @Param [brand_id]
     * @return java.util.Optional<com.djdu.brand.entity.Brand>
     **/
    @Transactional(readOnly = true)
    public Optional<Brand> findOneById(String brand_id){
        return brandRepository.findById(brand_id);
    }

    /**
     * @Author DJDU
     * @Description TODO 通过id集合查找对应的品牌集合
     * @Date 2019/2/21 10:19
     * @Param [brand_ids]
     * @return java.util.List<com.djdu.brand.entity.Brand>
     **/
    @Transactional(readOnly = true)
    public List<Brand> findAllByIds(List<String> brand_ids){
        return (List<Brand>)brandRepository.findAllById(brand_ids);
    }
}
