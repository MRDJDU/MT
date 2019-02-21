package com.djdu.category.service;

import com.djdu.category.entity.Category;
import com.djdu.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName CategoryService
 * @Description TODO 业务逻辑层，调用仓库层接口对数据进行处理
 * @Author DJDU
 * @Date 2019/1/27 20:10
 * @Version 1.0
 **/
@Service
@Transactional//开启事务处理
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository; //注入仓库层

    /**
     * @Author DJDU
     * @Description TODO 保存分类实体
     * @Date 2019/2/11 11:13
     * @Param [category]
     * @return void
     **/
    public void save(Category category){
        categoryRepository.save(category);
    }

    /**
     * @Author DJDU
     * @Description TODO 查找所有分类实体
     * @Date 2019/2/11 11:13 
     * @Param []
     * @return java.util.List<com.djdu.entity.Category>
     **/
    @Transactional(readOnly=true)
    public Page<Category> findAll(Specification<Category> specification,Pageable pageable){
        return categoryRepository.findAll(specification,pageable);
    }

    /**
     * @Author DJDU
     * @Description TODO 通过id查找对应得分类实体
     * @Date 2019/2/13 10:32
     * @Param [category_id]
     * @return java.util.Optional<com.djdu.category.entity.Category>
     **/
    @Transactional(readOnly=true)
    public Optional<Category> findById(String category_id){
        return categoryRepository.findById(category_id);
    }

    /**
     * @Author DJDU
     * @Description TODO 通过ids数组查找对应的分类实体列表
     * @Date 2019/2/13 10:38
     * @Param [category_ids]
     * @return java.util.List<com.djdu.category.entity.Category>
     **/
    @Transactional(readOnly=true)
    public List<Category> findAllById(List<String> category_ids){
        return (List<Category>)categoryRepository.findAllById(category_ids);
    }
}
