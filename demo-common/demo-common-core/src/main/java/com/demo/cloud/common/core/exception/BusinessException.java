package com.demo.cloud.common.core.exception;
/**
 * 描述:
 * 包名:com.zhengcj.cloud.demo.common.core.exception
 * 版本信息: 版本1.0
 * 日期:2022/6/6
 * Copyright 四川博奥环保科技有限公司
 */


import com.demo.cloud.common.core.constant.CommonCode;
import lombok.Getter;

/**
 * @describe：
 * @author: zhengcj/四川博奥环保科技有限公司
 * @version: v1.0
 * 2022/6/6 17:50
 */
@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -1142902058141338115L;
    private final Integer code;
    private final String message;

    public BusinessException(CommonCode commonCode) {
        super(commonCode.getMsg());
        this.code = commonCode.getCode();
        this.message = commonCode.getMsg();
    }

    public BusinessException(String message) {
        super(message);
        this.code = CommonCode.FAILURE.getCode();
        this.message = message;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
