package com.sunyi.gobang.auth.controller;

import com.sunyi.gobang.api.auth.dto.EmailDTO;
import com.sunyi.gobang.auth.service.EmailService;
import com.sunyi.gobang.api.auth.vo.CaptchaVO;
import com.sunyi.gobang.common.response.ServerResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 邮箱验证
 * @author Sunyi
 * @date 2023/4/6
 */
@RestController
public class EmailCaptchaController
{
    @Autowired
    EmailService emailService;

    @PostMapping("/auth/emailCaptcha")
    public ServerResponseEntity<CaptchaVO> emailCaptcha(
            @Valid @RequestBody EmailDTO param,
            BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return ServerResponseEntity.showFailMsg(
                    bindingResult.getAllErrors().get(0).getDefaultMessage());

        return emailService.sendEmailCaptcha(param.getEmail());
    }
}
