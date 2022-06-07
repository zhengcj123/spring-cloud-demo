package com.demo.cloud.common.mybatis.utils;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class BaseDO implements Serializable {

    private static final long serialVersionUID = 7378199372032517559L;

    /**
     * id
     */
    @TableId
    private String id;

    /**
     * 删除标识
     */
    private String delFlag;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;
}
