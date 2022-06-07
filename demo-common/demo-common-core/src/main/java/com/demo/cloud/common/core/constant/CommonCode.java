package com.demo.cloud.common.core.constant;
/**
 * 描述:
 * 包名:com.zhengcj.cloud.demo.common.core.constant
 * 版本信息: 版本1.0
 * 日期:2022/6/6
 * Copyright 四川博奥环保科技有限公司
 */


/**
 * @describe：
 * @author: zhengcj/四川博奥环保科技有限公司
 * @version: v1.0
 * 2022/6/6 17:50
 */
public enum CommonCode implements IErrorCode {
    /**
     * 操作成功
     */
    SUCCESS(200, "success"),
    /**
     * 业务异常
     */
    FAILURE(400, "业务异常"),
    /**
     * 请求未授权
     */
    UN_AUTHORIZED(401, "请求未授权"),

    /**
     * 404 没找到请求
     */
    NOT_FOUND(404, "404 没找到请求"),
    /**
     * 消息不能读取
     */
    MSG_NOT_READABLE(400, "消息不能读取"),
    /**
     * 不支持当前请求方法
     */
    METHOD_NOT_SUPPORTED(405, "不支持当前请求方法"),
    /**
     * 不支持当前媒体类型
     */
    MEDIA_TYPE_NOT_SUPPORTED(415, "不支持当前媒体类型"),
    /**
     * 请求被拒绝
     */
    REQ_REJECT(403, "请求被拒绝"),
    /**
     * 服务器异常
     */
    INTERNAL_SERVER_ERROR(500, "服务器异常"),


    NOT_FOUND_SERVER(503, "未找到服务"),
    /**
     * 缺少必要的请求参数
     */
    PARAM_MISS(400, "缺少必要的请求参数"),
    /**
     * 请求参数类型错误
     */
    PARAM_TYPE_ERROR(400, "请求参数类型错误"),
    /**
     * 请求参数绑定错误
     */
    PARAM_BIND_ERROR(400, "请求参数绑定错误"),
    /**
     * 参数校验失败
     */
    PARAM_VALID_ERROR(400, "参数校验失败"),

    CLIENT_AUTHENTICATION_FAILED(1001,"客户端认证失败"),
    USERNAME_OR_PASSWORD_ERROR(1002,"用户名或密码错误"),
    UNSUPPORTED_GRANT_TYPE(1003, "不支持的认证模式"),
    INVALID_CLIENT(1004,"不支持的客户端"),
    INTERNAL_AUTHENTICATION_SERVICE(1005,"内部认证服务异常"),
    NOT_PARSING_USERID(1006,"不能解析获取用户信息"),
    NOT_MATCH_PERMISSION(1007,"权限不足"),
    FACTORY_AUTH_FAIL(1008,"工厂授权失败"),
    USER_DISABLED(1009,"用户账号已被禁用"),
    TOKEN_EXPIRED(1010,"TOKEN已过期");

    private final int code;

    private final String msg;

    CommonCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public static CommonCode getValue(int code) {
        for (CommonCode value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return SUCCESS;
    }
}
