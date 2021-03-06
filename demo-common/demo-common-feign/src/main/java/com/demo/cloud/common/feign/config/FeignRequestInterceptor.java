package com.demo.cloud.common.feign.config;

import cn.hutool.core.util.StrUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 1 我们可以从request的上下文环境里面获取token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        String header = null;
        if (requestAttributes == null) {
            log.info("没有请求的上下文,故无法进行token的传递");
            //header = "bearer " + Constants.INSIDE_TOKEN;
        } else {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            // 获取我们请求上下文的头里面的AUTHORIZATION
            header = request.getHeader(HttpHeaders.AUTHORIZATION);
        }

        if (StrUtil.isNotBlank(header)) {
            requestTemplate.header(HttpHeaders.AUTHORIZATION, header);
            log.info("本次token传递成功,token的值为:{}", header);
        }
    }
}
