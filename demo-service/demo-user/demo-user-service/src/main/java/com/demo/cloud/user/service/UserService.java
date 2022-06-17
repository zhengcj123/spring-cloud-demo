package com.demo.cloud.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.cloud.user.entity.po.UserPO;

import java.util.List;


/**
* desc: 用户表 业务接口
*
* @author zhengcj
* date: 2022-3-2 15:04:28
*/
public interface UserService extends IService<UserPO>{

    /**
     * 添加用户
     * @param po:
     * @return: void
     * @throws
     * @author: zhengcj /四川博奥环保科技有限公司
     * @date: 2022/6/16 12:42
     */

    void saveUser(UserPO po);


    /**
     * 分页擦好像
     * @param userName: 用户名
     * @param page:
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.demo.cloud.user.entity.po.UserPO>
     * @throws
     * @author: zhengcj /四川博奥环保科技有限公司
     * @date: 2022/6/16 12:54
     */
    IPage<UserPO> findUserPage(String userName, IPage page);

    /**
     * 用户登录
     * @param userName: 用户名
     * @param password: 密码
     * @return: java.lang.String
     * @throws
     * @author: zhengcj /四川博奥环保科技有限公司
     * @date: 2022/6/16 13:25
     */

    String loginUser(String userName, String password);

    Boolean verifyToken(String token);
}