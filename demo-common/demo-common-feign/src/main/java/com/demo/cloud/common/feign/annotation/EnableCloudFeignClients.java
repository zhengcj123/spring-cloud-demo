package com.demo.cloud.common.feign.annotation;

import com.demo.cloud.common.feign.config.FeignRequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@EnableFeignClients(basePackages = {"com.demo.cloud.*.api"})
@Import(FeignRequestInterceptor.class)
public @interface EnableCloudFeignClients {
}
