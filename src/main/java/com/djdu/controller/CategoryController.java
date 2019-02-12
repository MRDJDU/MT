package com.djdu.controller;


import com.djdu.common.Message.ResponseMessage;
import com.djdu.dto.CategoryDto;
import com.djdu.entity.Category;
import com.djdu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
	@Autowired
	ResponseMessage responseMessage;

	/**
	 * @Author DJDU
	 * @Description 处理前端保存或更新分类的请求
	 * @Date 2019/2/12 11:10
	 * @Param [category]
	 * @return com.djdu.common.Message.ResponseMessage
	 **/
	@PostMapping(name="/saveOrUpdateCategory",consumes= MediaType.APPLICATION_JSON_VALUE)
	public  ResponseMessage saveOrUpdateCategory(@RequestBody Category category){
		try{
			categoryService.save(category);
			responseMessage.setStatuCode(0);
			responseMessage.setMessage("保存成功！");
		}catch (Exception e){
			responseMessage.setStatuCode(1);
			responseMessage.setMessage("保存失败！");
		}
		return responseMessage;
	}

	/**
	 * @Author DJDU
	 * @Description 处理前端获取全部分类的请求
	 * @Date 2019/2/12 11:09
	 * @Param []
	 * @return java.util.List<com.djdu.entity.Category>
	 **/
	@GetMapping("/findAllCategories")
    public List<Category> list(){
    	return categoryService.findAll();
    }

}

