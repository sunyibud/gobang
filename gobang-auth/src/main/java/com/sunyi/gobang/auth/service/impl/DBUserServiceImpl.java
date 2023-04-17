package com.sunyi.gobang.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunyi.gobang.auth.service.DBUserService;
import com.sunyi.gobang.common.database.mapper.UserMapper;
import com.sunyi.gobang.common.database.model.User;

import org.springframework.stereotype.Service;

/**
 * @author Sunyi
 * @date 2023/4/7
 */
@Service
public class DBUserServiceImpl extends ServiceImpl<UserMapper, User> implements DBUserService
{

    /**
     * 根据用户名密码在数据库中获取用户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    @Override
    public User getUserByUsernamePassword(String username, String password)
    {
        User userByUsername = getOne(new QueryWrapper<User>().eq("username", username));
        if(userByUsername == null)
            return null;
        if(!userByUsername.getPassword().equals(password))
            return null;
        return userByUsername;
    }
}
