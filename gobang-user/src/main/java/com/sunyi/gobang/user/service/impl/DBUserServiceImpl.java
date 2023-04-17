package com.sunyi.gobang.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sunyi.gobang.api.user.dto.UserInfoDTO;
import com.sunyi.gobang.api.user.vo.UserInfoVO;
import com.sunyi.gobang.common.database.model.User;
import com.sunyi.gobang.common.database.mapper.UserMapper;
import com.sunyi.gobang.user.service.DBUserService;
import org.springframework.stereotype.Service;

/**
 * @author Sunyi
 * @date 2023/4/7
 */
@Service
public class DBUserServiceImpl extends ServiceImpl<UserMapper, User> implements DBUserService
{

    /**
     * 邮箱地址数据库中是否已存在
     *
     * @param email 邮箱地址
     * @return 是否存在
     */
    @Override
    public boolean isEmailExistInDatabase(String email)
    {
        User user = this.getOne(new QueryWrapper<User>().eq("email", email));
        return user != null;
    }

    /**
     * 用户名数据库中是否已存在
     *
     * @param username 用户名
     * @return 是否存在
     */
    @Override
    public boolean isUsernameExistInDatabase(String username)
    {
        User user = this.getOne(new QueryWrapper<User>().eq("username", username));
        return user != null;
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param userId id
     * @return user
     */
    @Override
    public UserInfoVO getUserInfoByUserId(Long userId)
    {
        User user = this.getById(userId);
        return UserInfoVO.builder().userId(user.getId()).username(user.getUsername())
                .sex(user.getSex()).email(user.getEmail()).headPath(user.getHeadPath())
                .level(user.getLevel()).build();
    }

    @Override
    public boolean updateUserInfoByUserId(UserInfoDTO dto, long userId)
    {
        User user = this.getById(userId);
        user.setUsername(dto.getUsername());
        user.setSex(dto.getSex());
        return this.updateById(user);
    }

    /**
     * 重置密码
     *
     * @param email       邮箱
     * @param newPassword 新密码
     * @return success
     */
    @Override
    public boolean resetPassword(String email, String newPassword)
    {
        User user = this.getOne(new QueryWrapper<User>().eq("email", email));
        user.setPassword(newPassword);
        return this.updateById(user);
    }


}
