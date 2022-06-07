package com.demo.cloud.common.core.thread;
/**
 * 描述:
 * 包名:com.zhengcj.cloud.demo.common.core.thread
 * 版本信息: 版本1.0
 * 日期:2022/6/6
 * Copyright 四川博奥环保科技有限公司
 */


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: zhengcj/四川博奥环保科技有限公司
 * @version: v1.0
 * 2022/6/6 17:57
 */
@EnableConfigurationProperties(ThreadPoolConfigProperties.class)
@Configuration
public class ThreadConfig {
    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties pool) {
        //corePoolSize
        //maximumPoolSize,只有在缓冲队列满了之后才会申请超过核心线程数的线程
        //QueueCapacity缓存队列
        //KeepAliveSeconds允许的空闲时间,当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        return new ThreadPoolExecutor(
                pool.getCoreSize(),
                pool.getMaxSize(),
                pool.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(40),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
