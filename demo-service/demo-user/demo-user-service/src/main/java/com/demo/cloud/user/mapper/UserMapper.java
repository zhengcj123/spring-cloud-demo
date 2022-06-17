package com.demo.cloud.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.cloud.user.entity.dto.UserDTO;
import com.demo.cloud.user.entity.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* desc: mapper
*
* @author zhengcj
* date: 2022-3-2 15:04:28
*/
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

}