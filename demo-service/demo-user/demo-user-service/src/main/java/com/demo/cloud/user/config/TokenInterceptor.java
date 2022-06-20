package com.demo.cloud.user.config;
/**
 * 描述:
 * 包名:com.demo.cloud.user.config
 * 版本信息: 版本1.0
 * 日期:2022/6/16
 * Copyright 四川博奥环保科技有限公司
 */


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.demo.cloud.common.core.exception.BusinessException;
import com.demo.cloud.user.entity.po.UserPO;
import com.demo.cloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @describe：
 * @author: zhengcj/四川博奥环保科技有限公司
 * @version: v1.0
 * 2022/6/16 16:19
 */


public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        String token = request.getHeader("token");

        String method = request.getMethod();
        if (!method.equals("OPTIONS")){
            // 查询验证token
            if (!userService.verifyToken(token)){
                throw new BusinessException("token :"+url+" 权限不足");
            }
        }
        return true;
//        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // System.out.println("处理请求完成后视图渲染之前的处理操作");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // System.out.println("视图渲染之后的操作");
    }

}
