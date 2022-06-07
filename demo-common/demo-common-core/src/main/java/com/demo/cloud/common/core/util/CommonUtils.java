package com.demo.cloud.common.core.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import com.demo.cloud.common.core.lang.Action;

import java.util.ArrayList;
import java.util.List;


/**
 * 通用辅助类
 * @author chenqi
 */
public final class CommonUtils {
    public static boolean isEmpty(Object value) {
        if (null == value) {
            return true;
        }

        return ObjectUtil.isEmpty(value);
    }

    public static boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }


    public static <T> T toBean(Object source, Class<T> clazz) {
        if (null == source) {
            return null;
        }
        return BeanUtil.toBean(source, clazz, CopyOptions.create().ignoreError());
    }


    public static <T> T toBean(Object source, Class<T> clazz, CopyOptions copyOptions) {
        if (null == source) {
            return null;
        }
        return BeanUtil.toBean(source, clazz, copyOptions);
    }

    public static <T, TS> List<T> toListBean(List<TS> sourceList, Class<T> clazz) {
        if (ObjectUtil.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        for (TS item : sourceList) {
            list.add(toBean(item, clazz));
        }
        return list;
    }

    public static <T, TS> List<T> toListBean(List<TS> sourceList, Class<T> clazz, CopyOptions copyOptions) {
        return toListBean(sourceList, clazz, copyOptions, null);
    }

    public static <T, TS> List<T> toListBean(List<TS> sourceList, Class<T> clazz, Action<T> convertAction) {
        return toListBean(sourceList, clazz, null, convertAction);
    }

    public static <T, TS> List<T> toListBean(List<TS> sourceList, Class<T> clazz, CopyOptions copyOptions, Action<T> convertAction) {
        if (ObjectUtil.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        for (TS source : sourceList) {
            T item = toBean(source, clazz, copyOptions);
            if (item == null) {
                continue;
            }
            if (convertAction != null) {
                convertAction.invoke(item);
            }
            list.add(item);
        }
        return list;
    }

}
