package com.djdu.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName CategoryDto
 * @Description TODO 后台管理页面跳转专用控制器
 * @Author DJDU
 * @Date 2019/1/27 20:42
 * @Version 1.0
 **/
@Controller
public class AdminPageController {
	@GetMapping(value="/admin")
    public String admin(){
		return "redirect:admin_category_list";
    }
	@GetMapping(value="/admin_category_list")
	public String listCategory(){
		return "admin/listCategory";
	}
}
