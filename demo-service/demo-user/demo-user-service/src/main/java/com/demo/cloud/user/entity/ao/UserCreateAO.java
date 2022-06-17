package com.demo.cloud.user.entity.ao;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
* desc: 用户表 查询实体封装类
*
* @author zhengcj
* date: 2022-3-2 15:04:28
*/
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreateAO implements Serializable {

    private static final long serialVersionUID = 8732788371155861672L;

    @ApiModelProperty(value = "工厂id(登录当前工厂)", hidden = true)
    private Integer factoryId;

    @ApiModelProperty(value = "用户名称", required = true)
    @NotBlank(message = "用户名称不能为空!")
    private String userName;

    @ApiModelProperty(value = "真实姓名", required = true)
    @NotBlank(message = "真实姓名不能为空!")
    private String realName;


    @ApiModelProperty(value = "用户电话")
    private String userPhone;


    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;


    @ApiModelProperty(value = "用户性别")
    private Boolean userSex;

    @ApiModelProperty(value = "是否可用", required = true)
    @NotNull(message = "是否可用不能为空!")
    private Boolean enable;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "工厂id", hidden = true)
    private List<Integer> factoryIds;

}