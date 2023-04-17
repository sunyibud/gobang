package com.sunyi.gobang.user.controller;

import com.sunyi.gobang.api.user.dto.ResetPasswordDTO;
import com.sunyi.gobang.common.cache.constant.OAuthCacheNames;
import com.sunyi.gobang.common.cache.util.RedisUtil;
import com.sunyi.gobang.common.response.ResponseEnum;
import com.sunyi.gobang.common.response.ServerResponseEntity;
import com.sunyi.gobang.user.service.DBUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 重置密码
 * @author Sunyi
 * @date 2023/4/6
 */
@RestController
public class ResetPasswordController
{
    @Autowired
    DBUserService dbUserService;

    @PostMapping("/user/resetPassword")
    public ServerResponseEntity<Void> resetPassword(
            @Valid @RequestBody ResetPasswordDTO param,
            BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return ServerResponseEntity.showFailMsg(
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        Object o = RedisUtil.hashGet(OAuthCacheNames.CAPTCHA_KEY, param.getKey() + param.getEmail());
        if (o == null || !param.getEmailCode().equalsIgnoreCase(o.toString()))
            return ServerResponseEntity.showFailMsg("邮箱验证码错误");
        boolean isEmailExist = dbUserService.isEmailExistInDatabase(param.getEmail());
        if(!isEmailExist)
            return ServerResponseEntity.showFailMsg("该邮箱尚未注册");
        boolean success = dbUserService.resetPassword(param.getEmail(), param.getNewPassword());
        if(!success)
            return ServerResponseEntity.fail(ResponseEnum.EXCEPTION);
        return ServerResponseEntity.success();
    }
}
