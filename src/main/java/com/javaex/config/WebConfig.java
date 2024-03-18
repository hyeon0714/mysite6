package com.javaex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/upload/**")	//이미지 소스에서 upload로 받아올수 잇게 했다(upload뒤에 있는 파일이름이 무엇이든 /upload/가 앞에 붙으면 실행
	.addResourceLocations("file:C:\\자바Study\\upload\\");	//앞의 핸들러로 실행이 되면 찾을 파일의 위치를 지정한다
	}
}
