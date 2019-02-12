package com.djdu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName CategoryDto
 * @Description CORSConfiguration配置请求跨域问题
 * @Author DJDU
 * @Date 2019/1/27 20:42
 * @Version 1.0
 **/
@Configuration
public class CORSConfiguration extends WebMvcConfigurerAdapter{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//所有请求都允许跨域
		registry.addMapping("/**")
				//是否允许证书 不再默认开启，这里没设置导致使用接口工具时后台无法接收数据
				.allowCredentials(true)
				//设置允许跨域请求的域名
				.allowedOrigins("*")
				//设置允许的方法
				.allowedMethods("*")
				.allowedHeaders("*")
				//跨域允许时间
				.maxAge(3600);
	}
}