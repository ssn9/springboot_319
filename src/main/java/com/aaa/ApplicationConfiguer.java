package com.aaa;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfiguer implements WebMvcConfigurer {

    // 静态资源配置，虚拟路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // com/aaa/dao  f:\\upload\\1.jpeg==f:/upload/1.jpeg
        registry.addResourceHandler("/upload/**").addResourceLocations("file:F:/");
    }
}
