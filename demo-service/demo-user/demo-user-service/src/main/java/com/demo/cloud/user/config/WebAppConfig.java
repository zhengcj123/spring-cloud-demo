package com.demo.cloud.user.config;
/**
 * 描述:
 * 包名:com.demo.cloud.user.config
 * 版本信息: 版本1.0
 * 日期:2022/6/16
 * Copyright 四川博奥环保科技有限公司
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;

/**
 * @describe：
 * @author: zhengcj/四川博奥环保科技有限公司
 * @version: v1.0
 * 2022/6/16 16:27
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }
    /**
     * 跨域支持
     * @param registry
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods("GET", "POST", "DELETE", "PUT")
//                .maxAge(3600 * 24);
//    }

    // 配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(crosInterceptor).addPathPatterns("/**");   // 跨域拦截器
        registry.addInterceptor(tokenInterceptor())// token 验证拦截器
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login","/doc.html","/swagger-resources","/webjars/**")
        ;
    }

    /**
     * 当集成swagger防止后台报错
     * No mapping found for HTTP request with URI [/swagger-resources/configuration/ui] in DispatcherServlet with name 'dispatcherServlet'
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //过滤swagger
        // 指的是对外暴露的访问路径
        registry.addResourceHandler("doc.html")
                // 指的是内部文件放置的目录
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
