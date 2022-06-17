package com.demo.cloud.user.entity.vo;

import com.demo.cloud.common.core.validator.AddGroup;
import com.demo.cloud.common.core.validator.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
* desc: 用户表 查询实体封装类
*
* @author zhengcj
* date: 2022-3-2 15:04:28
*/
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO implements Serializable {

    @ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空", groups = UpdateGroup.class)
    private Integer userId;

    @ApiModelProperty(value = "用户名称")
    @NotBlank(message = "用户名称不能为空!", groups = {AddGroup.class, UpdateGroup.class})
    private String userName;

    @ApiModelProperty(value = "真实姓名")
    @NotBlank(message = "用户名称不能为空!", groups = {AddGroup.class, UpdateGroup.class})
    private String realName;


    @ApiModelProperty(value = "用户电话")
    private String userPhone;


    @ApiModelProperty(value = "用户邮箱")
    private String userEmail;


    @ApiModelProperty(value = "用户性别")
    private Boolean userSex;

    @ApiModelProperty(value = "是否可用")
    private Boolean enable;

    @ApiModelProperty(value = "备注")
    private String remarks;


}