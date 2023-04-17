package com.sunyi.gobang.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunyi.gobang.common.database.model.User;

/**
 * @author Sunyi
 * @date 2023/4/7
 */
public interface DBUserService extends IService<User>
{
    /**
     * 根据用户名密码在数据库中获取用户信息
     * @param username 用户民
     * @param password 密码
     * @return 用户信息
     */
    public User getUserByUsernamePassword(String username, String password);
}
