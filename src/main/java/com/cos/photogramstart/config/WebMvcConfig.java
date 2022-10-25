package com.cos.photogramstart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer{
// web설정
	@Value("${file.path}")
	private String uploadFolder;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		System.out.println("--uploadFolder--: : " + uploadFolder );
		registry.addResourceHandler("/upload/**")  // 이 패턴일경
		.addResourceLocations("file://"+uploadFolder) // 이걸로 변경한
		.setCachePeriod(60*10*6) // 1시간 케
		.resourceChain(true)
		.addResolver(new PathResourceResolver());
	}
}
