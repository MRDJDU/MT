package com.djdu.category.controller;

import com.djdu.category.dto.CategorySecondDto;
import com.djdu.category.dto.CategoryThirdDto;
import com.djdu.category.entity.CategorySecond;
import com.djdu.category.entity.CategoryThird;
import com.djdu.category.service.CategoryThirdService;
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
 * @ClassName CategoryThirdController
 * @Description TODO 三级分类控制层，接收前台请求，发送给service逻辑层处理
 * @Author DJDU
 * @Date 2019/4/2 22:08
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mt/categoryThird")
public class CategoryThirdController {
    @Autowired
    CategoryThirdService categoryThirdService;
    private String success = "";
    private String fail = "";

    /**
     * @Author DJDU
     * @Description TODO 新增三级分类，必须给二级分类id
     * @Date 2019/4/2 23:18
     * @Param [models]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/addCategoryThird",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage addCategorySecond(@RequestBody CategoryThird categoryThird){
        return categoryThirdService.addCategoryThird(categoryThird);
    }



    @PostMapping(value="/findAllCategoryThird",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage getCategorySecondPage(@RequestBody Map<String, Object> models){
        fail = "分页查找二级分类失败！";
        ResponseMessage responseMessage = new ResponseMessage<Page<CategorySecond>>();
        try {
            CategoryThirdDto categoryThirdDto = JsonXMLUtils.map2obj((Map<String, Object>) models.get("categoryThirdDto"), CategoryThirdDto.class);
            MyPagaRequest myPagaRequest=JsonXMLUtils.map2obj((Map<String, Object>)models.get("myPagaRequest"),MyPagaRequest.class);
            return categoryThirdService.getCategoryThirdPage(CategoryThirdDto.getWhereClause(categoryThirdDto),myPagaRequest.getPageable());
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
    @PostMapping(value="/deleteCategoryThird",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage deleteCategoryThird(@RequestBody CategoryThird categoryThird){
        return categoryThirdService.delete(categoryThird);
    }

    /**
     * @Author DJDU
     * @Description TODO 展示与冻结分类
     * @Date 2019/4/11 3:27
     * @Param [categoryFirst]
     * @return com.djdu.common.Message.ResponseMessage
     **/
    @PostMapping(value="/lockCategoryThird",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseMessage lockCategoryThird(@RequestBody CategoryThird categoryThird){
        return categoryThirdService.changesShowOut(categoryThird);
    }
}
