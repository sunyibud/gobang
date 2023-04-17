package com.sunyi.gobang.auth.controller;

import com.sunyi.gobang.api.auth.dto.LoginDTO;
import com.sunyi.gobang.api.auth.vo.TokenVO;
import com.sunyi.gobang.auth.service.DBUserService;
import com.sunyi.gobang.common.util.JwtUtil;
import com.sunyi.gobang.common.database.model.User;
import com.sunyi.gobang.common.response.ServerResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 登录和退出登录
 *
 * @author Sunyi
 * @date 2023/4/6
 */
@RestController
public class LoginController
{
    @Autowired
    DBUserService dbUserService;

    @PostMapping("/auth/login")
    public ServerResponseEntity<TokenVO> login(
            @Valid @RequestBody LoginDTO param,
            BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return ServerResponseEntity.showFailMsg(
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        User res = dbUserService.getUserByUsernamePassword(param.getUsername(), param.getPassword());
        if (res == null)
            return ServerResponseEntity.showFailMsg("用户名或密码错误");
        String token = JwtUtil.generateToken(res.getId());
        token = "bearer "+token;
        return ServerResponseEntity.success(TokenVO.builder().Authorization(token).build());
    }
}
