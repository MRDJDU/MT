package com.djdu.category.controller;

import com.djdu.Manage.dto.ManageDto;
import com.djdu.Manage.entity.Manage;
import com.djdu.category.dto.CategoryFirstDto;
import com.djdu.category.entity.CategoryFirst;
import com.djdu.category.service.CategoryFirstService;
import com.djdu.common.Message.MyPagaRequest;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.JsonXMLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName CategoryFirstController
 * @Description TODO 一级分类控制层，接收前台请求，发送给service逻辑层处理
 * @Author DJDU
 * @Date 2019/4/2 22:07
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mt/categoryFirst")
public class CategoryFirstController {
    @Autowired
    CategoryFirstService categoryFirstService;
    private String success = "";
    private String fail = "";

    /**
     * @Author DJDU
     * @Description TODO 新增一级分类
     * @Date 2019/4/2 22:47
     * @Param [categoryFirst]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/addCategoryFirst",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage addCategoryFirst(@RequestBody CategoryFirst categoryFirst){
        return categoryFirstService.addCategoryFirst(categoryFirst);
    }

    /**
     * @Author DJDU
     * @Description TODO 分页查找一级分类
     * @Date 2019/4/9 11:19
     * @Param [models]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/findAllCategoryFirst",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage getCategoryFirstPage(@RequestBody Map<String, Object> models){
        fail = "分页查找一级分类失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<CategoryFirst>>();
        try {
            CategoryFirstDto categoryFirstDto = JsonXMLUtils.map2obj((Map<String, Object>) models.get("categoryFirstDto"), CategoryFirstDto.class);
            MyPagaRequest myPagaRequest=JsonXMLUtils.map2obj((Map<String, Object>)models.get("myPagaRequest"),MyPagaRequest.class);
            return categoryFirstService.getCategoryFirstPage(CategoryFirstDto.getWhereClause(categoryFirstDto),myPagaRequest.getPageable());
        }
        catch (Exception e){
            responseMessage.makeError(fail,e.getMessage());
            return responseMessage;
        }
    }

    /**
     * @Author DJDU
     * @Description TODO 删除分类
     * @Date 2019/4/11 3:27
     * @Param [categoryFirst]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/deleteCategoryFirst",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage deleteCategoryFirst(@RequestBody CategoryFirst categoryFirst){
        return categoryFirstService.delete(categoryFirst);
    }

    /**
     * @Author DJDU
     * @Description TODO 展示与冻结分类
     * @Date 2019/4/11 3:27
     * @Param [categoryFirst]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/lockCategoryFirst",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage lockCategoryFirst(@RequestBody CategoryFirst categoryFirst){
        return categoryFirstService.changesShowOut(categoryFirst);
    }
}
