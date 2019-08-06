package com.cafe24.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cafe24.interceptor.AuthAdminInterceptor;
import com.cafe24.interceptor.AuthUserInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:/cafe24mall-uploads/");
	}
	
	// Interceptor
	@Bean
	public AuthUserInterceptor authUserInterceptor() {
		return new AuthUserInterceptor();
	}
	
	@Bean
	public AuthAdminInterceptor authAdminInterceptor() {
		return new AuthAdminInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(authUserInterceptor())
		.addPathPatterns("/**");
		
		registry
		.addInterceptor(authAdminInterceptor())
		.addPathPatterns("/**");
	}
}
