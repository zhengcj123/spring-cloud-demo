package com.demo.cloud.common.mybatis.utils;

import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.cloud.common.core.lang.Action;
import com.demo.cloud.common.core.lang.Func;
import com.demo.cloud.common.core.util.CommonUtils;
import com.demo.cloud.common.core.util.R;


public class PageAssembler {
    public static <T> PagedDTO<T> convert(IPage<T> page) {
        if (page == null) {
            return new PagedDTO<>();
        }
        int pageIndex = (int) page.getCurrent();
        int pageSize = (int) page.getSize();
        return new PagedDTO<>(page.getTotal(), page.getRecords(), pageIndex, pageSize);
    }

    public static <T, TS> PagedDTO<T> convert(IPage<TS> page, Class<T> clazz) {
        return convert(page, clazz, null);
    }

    public static <T, TS> PagedDTO<T> convert(IPage<TS> page, Class<T> clazz, CopyOptions copyOptions) {
        if (page == null) {
            return new PagedDTO<>();
        }
        IPage<T> convertPage = page.convert(r -> CommonUtils.toBean(r, clazz, copyOptions));
        return convert(convertPage);
    }

    public static <T, TS> PagedDTO<T> convert(IPage<TS> page, Func<T, TS> convertFunc) {
        if (page == null) {
            return new PagedDTO<>();
        }
        IPage<T> convertPage = page.convert(convertFunc::invoke);
        return convert(convertPage);
    }

    public static <T, TS> PagedDTO<T> convert(PagedDTO<TS> pagedDTO, Class<T> clazz) {
        return convert(pagedDTO, clazz, null);
    }

    public static <T, TS> PagedDTO<T> convert(PagedDTO<TS> pagedDTO, Class<T> clazz, CopyOptions copyOptions) {
        return convert(pagedDTO, clazz, copyOptions, null);
    }

    public static <T, TS> PagedDTO<T> convert(PagedDTO<TS> pagedDTO, Class<T> clazz, CopyOptions copyOptions, Action<T> convertAction) {
        return pagedDTO.convert(clazz, copyOptions, convertAction);
    }

    public static <T, TS> PagedDTO<T> convert(PagedDTO<TS> pagedDTO, Func<T, TS> convertFunc) {
        return pagedDTO.convert(convertFunc);
    }

    public static <T, TS> R<PagedDTO<T>> result(IPage<TS> page, Class<T> clazz) {
        return result(page, clazz, null);
    }

    public static <T, TS> R<PagedDTO<T>> result(IPage<TS> page, Class<T> clazz, CopyOptions copyOptions) {
        PagedDTO<T> pagedDTO = convert(page, clazz, copyOptions);
        return R.ok(pagedDTO);
    }


    public static <T, TS> R<PagedDTO<T>> result(PagedDTO<TS> pagedDTO, Class<T> clazz) {
        return result(pagedDTO, clazz, null);
    }

    public static <T, TS> R<PagedDTO<T>> result(PagedDTO<TS> pagedDTO, Class<T> clazz, CopyOptions copyOptions) {
        return R.ok(pagedDTO.convert(clazz, copyOptions));
    }
}
