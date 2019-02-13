package com.djdu.category.controller;


import com.djdu.category.dto.CategoryDto;
import com.djdu.category.entity.Category;
import com.djdu.category.service.CategoryService;
import com.djdu.common.Message.MyPagaRequest;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.JsonXMLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryDto
 * @Description Category的Controller层，中间层，接收前端请求数据，给service处理
 * @Author DJDU
 * @Date 2019/1/27 20:42
 * @Version 1.0
 **/
@RestController
public class CategoryController {
	@Autowired
    CategoryService categoryService;


	/**
	 * @Author DJDU
	 * @Description 处理前端保存或更新分类的请求
	 * @Date 2019/2/12 11:10
	 * @Param [category]
	 * @return com.djdu.common.Message.ResponseMessage
	 **/
	@PostMapping(name="/saveOrUpdateCategory",consumes= MediaType.APPLICATION_JSON_VALUE)
	public  ResponseMessage saveOrUpdateCategory(@RequestBody Category category){
		ResponseMessage responseMessage = new ResponseMessage<Category>();
		try{
			categoryService.save(category);
			responseMessage.setStatuCode(true);
			responseMessage.setMessage("操作成功！");
			responseMessage.setData(category);
		}catch (Exception e){
			responseMessage.setStatuCode(false);
			responseMessage.setMessage("操作失败！");
			responseMessage.setErrorMessage(e.getMessage());
		}
		return responseMessage;
	}


	/**
	 * @Author DJDU
	 * @Description 处理前端获取全部分类的请求,前端请求有json格式的多个对象参数时封装在map再转对象
	 * @Date 2019/2/12 11:09
	 * @Param []
	 * @return java.util.List<com.djdu.entity.Category>
	 **/
	@GetMapping(name="/findAllCategories")
	public ResponseMessage getPage(@RequestBody Map<String, Object> models){
		ResponseMessage responseMessage = new ResponseMessage<Page<Category>>();
		try{
			CategoryDto categoryDto = JsonXMLUtils.map2obj((Map<String, Object>)models.get("categoryDto"),CategoryDto.class);
			MyPagaRequest myPagaRequest=JsonXMLUtils.map2obj((Map<String, Object>)models.get("myPagaRequest"),MyPagaRequest.class);
			responseMessage.setStatuCode(true);
			responseMessage.setMessage("查找成功！");
			responseMessage.setData(categoryService.findAll(CategoryDto.getWhereClause(categoryDto),myPagaRequest.getPageable()));
		}
		catch (Exception e){
			responseMessage.setStatuCode(false);
			responseMessage.setMessage("查找失败！");
			responseMessage.setErrorMessage(e.getMessage());
		}
		return responseMessage;
	}
}

