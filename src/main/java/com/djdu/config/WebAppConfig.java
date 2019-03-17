package com.djdu.config;

import com.djdu.common.base.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName WebAppConfig
 * @Description TODO 将LoginInterceptor拦截器添加到SpringBoot的配置中，让SpringBoot项目有这么一个拦截器存在
 * @Author DJDU
 * @Date 2019/3/14 20:53
 * @Version 1.0
 **/
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/mt/manage/login          ");
    }
}
