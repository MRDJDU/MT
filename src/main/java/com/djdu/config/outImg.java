package com.djdu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName outImg
 * @Description TODO
 * @Author DJDU
 * @Date 2019/5/8 17:12
 * @Version 1.0
 **/
@Configuration
public class outImg extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //addResourceHandler是指你想在url请求的路径
        //addResourceLocations是图片存放的真实路径

        registry.addResourceHandler("/image/**").addResourceLocations("file:E://workspace//IdeaProjects//MT//MT后台界面//mtmanager//static/");
        super.addResourceHandlers(registry);
    }
}

