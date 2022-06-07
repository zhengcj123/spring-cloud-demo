package com.demo.cloud.ops.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author LiuYuQi
 * @version 1.0
 * @description: TODO
 * @date 2022/2/28 13:51
 */
@SpringBootApplication
@EnableEurekaServer
public class DcsYunEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(DcsYunEurekaApplication.class,args);
    }
}
