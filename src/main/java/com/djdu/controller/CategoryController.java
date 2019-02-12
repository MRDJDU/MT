package com.djdu.controller;


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

@RestController
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@GetMapping("/findAllCategories")
    public List<Category> list() throws Exception {
    	return categoryService.findAll();
    }

	@PostMapping(name="/saveOrUpdateCategory",consumes= MediaType.APPLICATION_JSON_VALUE)
	public  Category saveOrUpdateCategory(@RequestBody Category category) throws Exception {
		return category;
	}
}

