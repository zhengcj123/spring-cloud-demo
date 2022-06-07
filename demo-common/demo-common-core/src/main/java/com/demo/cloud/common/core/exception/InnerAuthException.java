package com.demo.cloud.common.core.exception;
/**
 * 描述:
 * 包名:com.zhengcj.cloud.demo.common.core.exception
 * 版本信息: 版本1.0
 * 日期:2022/6/6
 * Copyright 四川博奥环保科技有限公司
 */


/**
 * @describe：
 * @author: zhengcj/四川博奥环保科技有限公司
 * @version: v1.0
 * 2022/6/6 17:52
 */
public class InnerAuthException extends RuntimeException {

    private static final long serialVersionUID = -1327671319382020363L;

    public InnerAuthException(String message)
    {
        super(message);
    }
}
