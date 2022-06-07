package com.demo.cloud.common.core.lang;


/**
 * @author chenqi
 */
public interface Action<T> {
    /**
     * 执行方法
     *
     * @param source 参数
     */
    void invoke(T source);
}
