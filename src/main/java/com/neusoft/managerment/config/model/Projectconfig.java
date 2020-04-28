package com.neusoft.managerment.config.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class Projectconfig implements WebMvcConfigurer {
		
		//配置全局CORS
		@Override
		public void addCorsMappings(CorsRegistry registry)  {
					registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("GET","POST","PUT", "DELETE","HEAD");
					System.out.println("全局CORS设置。。。");
				}

	}


