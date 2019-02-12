package com.djdu.service;

import com.djdu.entity.Category;
import com.djdu.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName CategoryService
 * @Description 业务逻辑层，调用仓库层接口对数据进行处理
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
     * @Description 保存分类实体
     * @Date 2019/2/11 11:13
     * @Param [category]
     * @return void
     **/
    public void save(Category category){
        categoryRepository.save(category);
    }

    /**
     * @Author DJDU
     * @Description 查找所有分类实体
     * @Date 2019/2/11 11:13 
     * @Param []
     * @return java.util.List<com.djdu.entity.Category>
     **/
    @Transactional(readOnly=true)
    public List<Category> findAll(){
        return (List<Category>)categoryRepository.findAll();
    }

}
