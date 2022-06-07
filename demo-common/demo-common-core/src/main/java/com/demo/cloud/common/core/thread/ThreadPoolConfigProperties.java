package com.demo.cloud.common.core.thread;
/**
 * 描述:
 * 包名:com.zhengcj.cloud.demo.common.core.thread
 * 版本信息: 版本1.0
 * 日期:2022/6/6
 * Copyright 四川博奥环保科技有限公司
 */


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @describe：
 * @author: zhengcj/四川博奥环保科技有限公司
 * @version: v1.0
 * 2022/6/6 17:55
 */
@ConfigurationProperties(prefix = "pool")
@Data
public class ThreadPoolConfigProperties {

    /**
     * 每秒需要多少个线程处理?
     */
    private Integer coreSize=6;

    /**
     * 线程池维护线程的最大数量
     */
    private Integer maxSize=20;

    /**
     * 允许的空闲时间,当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
     */
    private Integer keepAliveTime=100;
}
