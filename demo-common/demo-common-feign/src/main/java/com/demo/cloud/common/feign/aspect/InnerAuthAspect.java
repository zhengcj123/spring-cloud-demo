package com.demo.cloud.common.feign.aspect;

import cn.hutool.core.util.StrUtil;
import com.demo.cloud.common.core.constant.AuthConstants;
import com.demo.cloud.common.core.exception.InnerAuthException;
import com.demo.cloud.common.core.util.RequestUtils;
import com.demo.cloud.common.feign.annotation.Inner;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author LiuYuQi
 * @version 1.0
 * @description: 内部接口调用的aop处理
 * @date 2022/3/6 20:23
 */

@Aspect
@Component
public class InnerAuthAspect implements Ordered {

    @Around("@annotation(inner)")
    public Object innerAround(ProceedingJoinPoint point, Inner inner) throws Throwable {
        String source = RequestUtils.getRequest().getHeader(AuthConstants.FROM_SOURCE);
        // 内部请求验证
        if (!StrUtil.equals(AuthConstants.INNER, source)) {
            throw new InnerAuthException("没有内部访问权限，不允许访问");
        }
        return point.proceed();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

}
