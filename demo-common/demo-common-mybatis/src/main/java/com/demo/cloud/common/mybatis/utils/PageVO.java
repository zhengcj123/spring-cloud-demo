package com.demo.cloud.common.mybatis.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;

/**
 * 分页参数
 *
 * @author chenqi
 */
@Getter
@Setter
@ToString

public class PageVO {

    @Min(value = 1, message = "页码必须大于0")
    private Integer pageNum;

    @Min(value = 1, message = "分页大小必须大于0")
    private Integer pageSize;

    public PageVO() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    public PageVO(int page, int size) {
        this.pageNum = page;
        this.pageSize = size;
    }
}
