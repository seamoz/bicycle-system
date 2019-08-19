package com.ps.bicycleh5app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 处理AJAX请求跨域的问题
 * extends WebMvcConfigurerAdapter
 * @author Levin
 * @time 2017-07-13
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    static final String[] ORIGINS = new String[]{"GET", "POST", "PUT", "DELETE"};
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
                allowedOrigins("*").
                allowCredentials(true)
                .allowedMethods(ORIGINS)
                .maxAge(3600);
    }
}