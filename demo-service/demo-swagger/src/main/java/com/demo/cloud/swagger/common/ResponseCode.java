package com.demo.cloud.swagger.common;

/**
 * 枚举类, 定义本项目的业务返回码
 */
public enum ResponseCode {

    OK(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    NOT_ACCEPTABLE(406),
    CONFLICT(409),
    INTERNAL_SERVER_ERROR(500);

    private Integer value;

    ResponseCode(Integer value) {
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }
}
