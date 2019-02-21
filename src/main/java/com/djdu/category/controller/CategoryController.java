package com.djdu.category.controller;


import com.djdu.category.dto.CategoryDto;
import com.djdu.category.entity.Category;
import com.djdu.category.service.CategoryService;
import com.djdu.common.Enums.ShowOut;
import com.djdu.common.Enums.Usable;
import com.djdu.common.Message.MyPagaRequest;
import com.djdu.common.Message.ResponseMessage;
import com.djdu.common.Tool.BeanUtils;
import com.djdu.common.Tool.JsonXMLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryDto
 * @Description TODO Category的Controller层，中间层，接收前端请求数据，给service处理
 * @Author DJDU
 * @Date 2019/1/27 20:42
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mt/category")
public class CategoryController {
	@Autowired
    CategoryService categoryService;


	/**
	 * @Author DJDU
	 * @Description TODO 处理前端新增分类的请求
	 * @Date 2019/2/12 11:10
	 * @Param [category]
	 * @return com.djdu.common.Message.ResponseMessage
	 **/
	@PostMapping(value="/saveCategory",consumes= MediaType.APPLICATION_JSON_VALUE)
	public  ResponseMessage save(@RequestBody Category category){
		ResponseMessage responseMessage = new ResponseMessage<Category>();
		try{
			categoryService.save(category);
			responseMessage.setStatuCode(true);
			responseMessage.setMessage("新增成功！");
			responseMessage.setData(category);
		}catch (Exception e){
			responseMessage.setStatuCode(false);
			responseMessage.setMessage("新增失败！");
			responseMessage.setErrorMessage(e.getMessage());
		}
		return responseMessage;
	}

	/**
	 * @Author DJDU
	 * @Description TODO 处理前端删除分类操作，逻辑删除更改标记值，可批量删除
	 * @Date 2019/2/13 13:49
	 * @Param [models]
	 * @return com.djdu.common.Message.ResponseMessage
	 **/
	@DeleteMapping(value="/deleteCategories",consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage delete(@RequestBody Map<String, Object> models){
		ResponseMessage responseMessage = new ResponseMessage<Category>();
		try{
			ArrayList<String> category_ids = (ArrayList<String>) models.get("category_ids");
			List<Category> categories = categoryService.findAllById(category_ids);
			for(Category category:categories){
				category.setUsable(Usable.Deleted);
				categoryService.save(category);
			}
			responseMessage.setStatuCode(true);
			responseMessage.setMessage("删除成功！");
			responseMessage.setData(categories);
		}catch (Exception e){
			responseMessage.setStatuCode(false);
			responseMessage.setMessage("删除失败！");
			responseMessage.setErrorMessage(e.getMessage());
		}
		return responseMessage;
	}

	/**
	 * @Author DJDU
	 * @Description TODO 分类是否显示在客户端，批量操作
	 * @Date 2019/2/13 15:14 
	 * @Param [models]
	 * @return com.djdu.common.Message.ResponseMessage
	 **/
	@PutMapping(value="/showCategories",consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage show(@RequestBody Map<String, Object> models){
		ResponseMessage responseMessage = new ResponseMessage<Category>();
		ShowOut showOut=null;
		try{
			ArrayList<String> category_ids = (ArrayList<String>) models.get("category_ids");
			showOut=ShowOut.valueOf((String) models.get("showOut"));
			List<Category> categories = categoryService.findAllById(category_ids);
			for(Category category:categories){
				category.setShowOut(showOut);
				categoryService.save(category);
			}
			responseMessage.setStatuCode(true);
			if(showOut==ShowOut.Show){
				responseMessage.setMessage("显示成功！");
			}
			else{
				responseMessage.setMessage("隐藏成功！");
			}
			responseMessage.setData(categories);
		}catch (Exception e){
			responseMessage.setStatuCode(false);
			if(showOut==ShowOut.Show){
				responseMessage.setMessage("显示失败！");
			}
			else{
				responseMessage.setMessage("隐藏失败！");
			}
			responseMessage.setErrorMessage(e.getMessage());
		}
		return responseMessage;
	}

	/**
	 * @Author DJDU
	 * @Description TODO 处理前端修改分类操作，使用Dto接收数据再用自定义BeanUtils工具将值赋值到entity，null值数据不处理不赋值,根据id，修改分类名字和是否显示
	 * @Date 2019/2/13 13:46
	 * @Param [categoryDto]
	 * @return com.djdu.common.Message.ResponseMessage
	 **/
	@PutMapping(value ="/editCategory",consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseMessage edit(@RequestBody CategoryDto categoryDto){
		ResponseMessage responseMessage = new ResponseMessage<Category>();
		try{
			Category category = categoryService.findById(categoryDto.getCategory_id()).get();
			if(category!=null){
				BeanUtils.copyProperties(categoryDto,category);
				categoryService.save(category);
			}
			responseMessage.setStatuCode(true);
			responseMessage.setMessage("修改成功！");
			responseMessage.setData(category);
		}catch (Exception e){
			responseMessage.setStatuCode(false);
			responseMessage.setMessage("修改失败！");
			responseMessage.setErrorMessage(e.getMessage());
		}
		return responseMessage;
	}


	/**
	 * @Author DJDU
	 * @Description TODO 处理前端获取全部分类的请求,前端请求有json格式的多个对象参数时封装在map再转对象，分页条件对象和查询条件对象（分类名字和是否显示）
	 * @Date 2019/2/12 11:09
	 * @Param []
	 * @return java.util.List<com.djdu.entity.Category>
	 **/
	@GetMapping(value="/findAllCategories")
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

