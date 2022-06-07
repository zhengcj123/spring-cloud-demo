package com.demo.cloud.common.core.lang;


/**
 * @author chenqi
 */
public interface Func<R, T> {
    /**
     * 执行方法
     *
     * @param source 参数
     * @return R
     */
    R invoke(T source);
}
