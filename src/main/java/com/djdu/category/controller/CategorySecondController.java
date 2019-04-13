package com.djdu.category.controller;

import com.djdu.Manage.entity.Manage;
import com.djdu.category.dto.CategoryFirstDto;
import com.djdu.category.dto.CategorySecondDto;
import com.djdu.category.entity.CategoryFirst;
import com.djdu.category.entity.CategorySecond;
import com.djdu.category.service.CategorySecondService;
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
 * @ClassName CategorySecondController
 * @Description TODO 二级分类控制层，接收前台请求，发送给service逻辑层处理
 * @Author DJDU
 * @Date 2019/4/2 22:08
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mt/categorySecond")
public class CategorySecondController {
    @Autowired
    CategorySecondService categorySecondService;
    private String success = "";
    private String fail = "";

    /**
     * @Author DJDU
     * @Description TODO 新增二级分类，必须给一级分类的id
     * @Date 2019/4/2 23:17
     * @Param [models]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/addCategorySecond",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage addCategorySecond(@RequestBody CategorySecond categorySecond){
       return categorySecondService.addCategorySecond(categorySecond);
    }



    @PostMapping(value="/findAllCategorySecond",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage getCategorySecondPage(@RequestBody Map<String, Object> models){
        fail = "分页查找二级分类失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<CategorySecond>>();
        try {
            CategorySecondDto categorySecondDto = JsonXMLUtils.map2obj((Map<String, Object>) models.get("categorySecondDto"), CategorySecondDto.class);
            MyPagaRequest myPagaRequest=JsonXMLUtils.map2obj((Map<String, Object>)models.get("myPagaRequest"),MyPagaRequest.class);
            return categorySecondService.getCategorySecondPage(CategorySecondDto.getWhereClause(categorySecondDto),myPagaRequest.getPageable());
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
    @PostMapping(value="/deleteCategorySecond",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage deleteCategorySecond(@RequestBody CategorySecond categorySecond){
        return categorySecondService.delete(categorySecond);
    }

    /**
     * @Author DJDU
     * @Description TODO 展示与冻结分类
     * @Date 2019/4/11 3:27
     * @Param [categoryFirst]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/lockCategorySecond",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage lockCategorySecond(@RequestBody CategorySecond categorySecond){
        return categorySecondService.changesShowOut(categorySecond);
    }
}
