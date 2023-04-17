package com.sunyi.gobang.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sunyi.gobang.api.user.dto.UserInfoDTO;
import com.sunyi.gobang.api.user.vo.UserInfoVO;
import com.sunyi.gobang.common.database.model.User;

/**
 * @author Sunyi
 * @date 2023/4/7
 */
public interface DBUserService extends IService<User>
{
    /**
     * 邮箱地址数据库中是否已存在
     * @param email 邮箱地址
     * @return 是否存在
     */
    boolean isEmailExistInDatabase(String email);

    /**
     * 用户名数据库中是否已存在
     * @param username 用户名
     * @return 是否存在
     */
    boolean isUsernameExistInDatabase(String username);

    /**
     * 根据用户id获取用户信息
     * @param userId 用户id
     * @return
     */
    UserInfoVO getUserInfoByUserId(Long userId);

    /**
     * 更新用户普通信息
     * @param dto 新的信息
     * @param userId 用户id
     * @return success
     */
    boolean updateUserInfoByUserId(UserInfoDTO dto, long userId);

    /**
     * 重置密码
     * @param email 邮箱
     * @param newPassword 新密码
     * @return success
     */
    boolean resetPassword(String email, String newPassword);
}
