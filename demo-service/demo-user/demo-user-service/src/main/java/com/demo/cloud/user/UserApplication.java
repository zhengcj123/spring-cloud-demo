package com.demo.cloud.user;


import com.demo.cloud.common.core.constant.CommonConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;

/**
 * @author LiuYuQi
 * @version 1.0
 * @description: TODO
 * @date 2022/2/28 15:32
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = CommonConstants.BASE_PACKAGES)
//@EnableCloudFeignClients
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
