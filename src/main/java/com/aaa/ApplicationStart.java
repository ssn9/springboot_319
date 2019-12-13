package com.aaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 启动项必须在所有类的最外层
// 将当前类作为Sbpringoot的应用程序
@SpringBootApplication
@MapperScan(basePackages = "tk.mybatis.mapper.common.Mapper")
public class ApplicationStart {

    public static void main(String[] args) {
        // 运行spring应用程序
        SpringApplication.run(ApplicationStart.class);
    }
}
