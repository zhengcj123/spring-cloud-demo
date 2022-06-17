package com.demo.cloud.user.entity.po;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* desc: 用户表
*
* @author zhengcj
* date: 2022-3-2 15:04:28
*/
@Data
@Accessors(chain = true)
@TableName("sys_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPO implements Serializable{

    /**
    * 用户ID
    */
    @TableId(type = IdType.AUTO, value = "user_id")
    private Integer userId;

    private Integer factoryId;

    /**
    * 用户名称
    */
    @TableField("user_name")
    private String userName;

    /**
     * 用户密码
     */
    @TableField("password")
    private String password;

    /**
    * 真实姓名
    */
    @TableField("real_name")
    private String realName;
    /**
    * 头像地址
    */
    @TableField("avatar_address")
    private String avatarAddress;
    /**
    * 用户电话
    */
    @TableField("user_phone")
    private String userPhone;
    /**
    * 用户邮箱
    */
    @TableField("user_email")
    private String userEmail;
    /**
    * 用户性别
    */
    @TableField("user_sex")
    private Boolean userSex;
    /**
    * 备注
    */
    @TableField("remarks")
    private String remarks;
    /**
    * 是否可用
    */
    @TableField("enable")
    private Boolean enable;
    /**
    * 是否删除
    */
    @TableLogic
    @TableField("deleted")
    private Boolean deleted;
    /**
    * 预留字段
    */
    @TableField("open_id")
    private String openId;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}