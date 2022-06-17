package com.demo.cloud.user.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* desc: 用户表 传输实体封装类
*
* @author zhengcj
* date: 2022-3-2 15:04:28
*/
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable {

    /**
    * 用户ID
    */
    private Integer userId;
    /**
    * 用户名称
    */
    private String userName;
    /**
    * 真实姓名
    */
    private String realName;

    /*
     * 用户密码
     */
    private String password;

    /**
    * 头像地址
    */
    private String avatarAddress;
    /**
    * 用户电话
    */
    private String userPhone;
    /**
    * 用户邮箱
    */
    private String userEmail;
    /**
    * 用户性别
    */
    private Boolean userSex;
    /**
    * 备注
    */
    private String remarks;
    /**
    * 是否可用
    */
    private Boolean enable;
    /**
    * 是否删除
    */
    private Boolean deleted;
    /**
    * 预留字段
    */
    private String openId;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改时间
    */
    private Date updateTime;

    /**
     * 角色名称 角色1，角色2
     */
    private String roleName;
}