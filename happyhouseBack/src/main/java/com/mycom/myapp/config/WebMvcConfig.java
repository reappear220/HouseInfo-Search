package com.mycom.myapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mycom.myapp.common.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns(
				"/",
				"/codes",
				"/freeboard",
				"/index.html",
				"/main",
				"/login",
				"/favicon.ico", 
				"/css/**", 
				"/js/**"); // static folder
	}
	
//	@Override
//    public void addResourceHandlers (ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/").
//                  addResourceLocations("classpath:/static/index.html");
//    }
	
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//	    registry.addViewController("/").setViewName("forward:/index.html");
//	}
}
