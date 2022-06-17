package com.demo.cloud.user.service.impl;
/**
 * 描述:
 * 包名:com.demo.cloud.user.service
 * 版本信息: 版本1.0
 * 日期:2022/6/9
 * Copyright 四川博奥环保科技有限公司
 */


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.cloud.common.core.constant.CommonConstants;
import com.demo.cloud.common.core.exception.BusinessException;
import com.demo.cloud.user.entity.po.UserPO;
import com.demo.cloud.user.mapper.UserMapper;
import com.demo.cloud.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe：
 * @author: zhengcj/四川博奥环保科技有限公司
 * @version: v1.0
 * 2022/6/9 12:42
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {
    private Long expiration = 7L;
    @Override
    public void saveUser(UserPO po) {
        String password = DigestUtil.md5Hex(CommonConstants.PWD_DEF);
        po.setPassword(password);
        save(po);
    }

    @Override
    public IPage<UserPO> findUserPage(String userName, IPage page) {
        return lambdaQuery().like(StrUtil.isNotEmpty(userName), UserPO::getUserName, userName)
                .orderByDesc(UserPO::getCreateTime)
                .page(page);
    }

    @Override
    public String loginUser(String userName, String password) {
        UserPO sysUser = lambdaQuery().eq(UserPO::getUserName, userName)
                .eq(UserPO::getPassword, DigestUtil.md5Hex(password))
                .one();
        if(sysUser == null) {
            throw new BusinessException("登录失败请检查用户名或密码");
        }
        return createToken(sysUser);
    }


    private String createToken(UserPO sysUser) {
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.DAY_OF_MONTH, 10);

        Map<String,Object> payload = new HashMap<>();
        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //载荷
        payload.put("userName", sysUser.getUserName());
        payload.put("userId", sysUser.getUserId());

        String token = JWTUtil.createToken(payload, CommonConstants.jwtSecret.getBytes());
        return token;
    }

    @Override
    public Boolean verifyToken(String token) {
        if(StrUtil.isBlank(token)) {
            return false;
        }
        JWT jwt = JWTUtil.parseToken(token);

        boolean verifyKey = jwt.setKey(CommonConstants.jwtSecret.getBytes()).verify();
        if(verifyKey) {
            if(jwt.validate(0)) {
                return true;
            }
        }
        return false;
    }




}
