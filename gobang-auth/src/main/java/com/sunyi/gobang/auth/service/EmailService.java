package com.sunyi.gobang.auth.service;

import com.sunyi.gobang.api.auth.vo.CaptchaVO;
import com.sunyi.gobang.common.response.ServerResponseEntity;

/**
 * 邮箱验证
 * @author Sunyi
 * @date 2023/4/7
 */
public interface EmailService
{
    /**
     * 验证码生成
     * @return 生成的验证码
     */
    String captchaGenerate();

    /**
     * 发送验证码邮件
     * @param to 收件人,多个时参数形式 ："xxx@xxx.com,xxx@xxx.com,xxx@xxx.com"
     * @param subject 主题
     * @param verificationCode 验证码
     * @return 发送状态
     */
    boolean sendHtmlMail(String to, String subject, String verificationCode);

    /**
     * 随机生成验证码和key存入redis，并发送验证码邮件
     * @param to 接受方
     * @return 该条验证码对应的key
     */
    ServerResponseEntity<CaptchaVO> sendEmailCaptcha(String to);
}
