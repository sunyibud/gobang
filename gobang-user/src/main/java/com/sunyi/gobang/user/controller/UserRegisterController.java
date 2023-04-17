package com.sunyi.gobang.user.controller;

import com.sunyi.gobang.common.bo.Const;
import com.sunyi.gobang.common.cache.constant.OAuthCacheNames;
import com.sunyi.gobang.common.cache.util.RedisUtil;
import com.sunyi.gobang.common.database.model.User;
import com.sunyi.gobang.common.response.ResponseEnum;
import com.sunyi.gobang.common.response.ServerResponseEntity;
import com.sunyi.gobang.api.user.dto.UserRegisterDTO;
import com.sunyi.gobang.api.user.vo.UserRegisterVO;
import com.sunyi.gobang.user.service.DBUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户注册接口
 *
 * @author Sunyi
 * @date 2023/4/6
 */
@RestController
public class UserRegisterController
{

    @Autowired
    DBUserService DBUserService;

    @PostMapping("/user/register")
    public ServerResponseEntity<UserRegisterVO> register(
            @Valid @RequestBody UserRegisterDTO param,
            BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return ServerResponseEntity.showFailMsg(
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        if(param.getKey()==null)
            return ServerResponseEntity.showFailMsg("邮箱验证码错误");
        Object o = RedisUtil.hashGet(OAuthCacheNames.CAPTCHA_KEY, param.getKey() + param.getEmail());
        if (o == null || !param.getEmailCode().equalsIgnoreCase(o.toString()))
            return ServerResponseEntity.showFailMsg("邮箱验证码错误");
        if (DBUserService.isEmailExistInDatabase(param.getEmail()))
            return ServerResponseEntity.showFailMsg("邮箱已被注册");
        if (DBUserService.isUsernameExistInDatabase(param.getUsername()))
            return ServerResponseEntity.showFailMsg("用户名已经存在，换一个吧");
        boolean saveSuccess = DBUserService.save(new User(param.getUsername(), param.getPassword(),
                param.getSex(), param.getEmail(), Const.UserDefaultHeadPath, Const.UserInitLevel));
        if (!saveSuccess)
            return ServerResponseEntity.fail(ResponseEnum.EXCEPTION);
        RedisUtil.hashDel(OAuthCacheNames.CAPTCHA_KEY, param.getKey() + param.getEmail());
        return ServerResponseEntity.success(UserRegisterVO.builder().username(param.getUsername()).build());
    }
}
