package com.demo.cloud.ops.gateway.filter;
/**
 * 描述:
 * 包名:com.demo.cloud.ops.gateway.filter
 * 版本信息: 版本1.0
 * 日期:2022/6/8
 * Copyright 四川博奥环保科技有限公司
 */


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @describe：
 * @author: zhengcj/四川博奥环保科技有限公司
 * @version: v1.0
 * 2022/6/8 12:34
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
