package com.demo.cloud.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.cloud.common.core.util.ApiResponse;
import com.demo.cloud.common.mybatis.utils.PageAssembler;
import com.demo.cloud.common.mybatis.utils.PagedDTO;
import com.demo.cloud.user.entity.ao.UserCreateAO;
import com.demo.cloud.user.entity.ao.UserUpdateAO;
import com.demo.cloud.user.entity.po.UserPO;
import com.demo.cloud.common.mybatis.utils.PageVO;
import com.demo.cloud.user.entity.vo.UserVO;
import com.demo.cloud.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import static com.demo.cloud.common.core.util.ApiResponse.success;


/**
 * @author zhengcj
 * @create 2022-03-02 11:57
 * @description
 */
@Validated
@Api(tags = "用户管理")
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation("用户登录的基本信息")
    @PostMapping("login")
    public ApiResponse<String> findUserLoginInfoById(@NotBlank String userName,@NotBlank String password){
        String token = userService.loginUser(userName, password);
        return success(token);
    }

    @ApiOperation("删除")
    @DeleteMapping
    public ApiResponse deleteUser(Integer userId){
        userService.removeById(userId);
        return success();
    }

    @ApiOperation("添加")
    @PostMapping
    public ApiResponse saveUser(UserCreateAO ao){
        UserPO po = BeanUtil.toBean(ao, UserPO.class);
        userService.saveUser(po);
        return success();
    }

    @ApiOperation("修改")
    @PutMapping
    public ApiResponse updateUser(UserUpdateAO ao){
        UserPO po = BeanUtil.toBean(ao, UserPO.class);
        userService.updateById(po);
        return success();
    }

    @ApiOperation("分页查询")
    @GetMapping
    public ApiResponse<PagedDTO<UserVO>> findPageUser(@RequestParam(required = false) String userName, PageVO pageVO){
        IPage page = new Page(pageVO.getPageNum(), pageVO.getPageSize());
        IPage<UserPO> userPage = userService.findUserPage(userName, page);
        PagedDTO<UserVO> result = PageAssembler.convert(userPage, UserVO.class);
        return success(result);
    }



}
