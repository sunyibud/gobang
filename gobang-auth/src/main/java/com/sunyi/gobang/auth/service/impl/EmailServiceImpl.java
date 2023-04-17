package com.sunyi.gobang.auth.service.impl;

import cn.hutool.core.lang.UUID;
import com.sunyi.gobang.auth.service.EmailService;
import com.sunyi.gobang.api.auth.vo.CaptchaVO;
import com.sunyi.gobang.common.cache.constant.CacheNames;
import com.sunyi.gobang.common.cache.util.RedisUtil;
import com.sunyi.gobang.common.response.ServerResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

/**
 * @author Sunyi
 * @date 2023/4/7
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService
{
    //Spring Boot 提供了一个发送邮件的简单抽象，使用的是下面这个接口，这里直接注入即可使用
    @Autowired
    private JavaMailSender mailSender;

    // 配置文件中我的163邮箱
    @Value("${spring.mail.from}")
    private String from;

    private final int captchaLength = 6;
    /**
     * 验证码生成
     *
     * @return 生成的验证码
     */
    @Override
    public String captchaGenerate()
    {
        //定义一个随机生成数技术，用来生成随机数
        Random random = new Random();
        //2，用String常用API-charAit生成验证码
        //定义一个String变量存放需要的数据，一共58位
        String tempList = "1234567890abcdefghijklmnopqrstuvwxwzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder verificationCode = new StringBuilder();//定义一个空的String变量用来接收生成的验证码
        for (int i = 0; i < captchaLength; i++) {
            int a = random.nextInt(58);//随机生成0-57之间的数，提供索引位置
            verificationCode.append(tempList.charAt(a));//用get 和提供的索引找到相应位置的数据给变量
        }
        return verificationCode.toString();
    }

    /**
     * 发送验证码邮件
     *
     * @param to               收件人,多个时参数形式 ："xxx@xxx.com,xxx@xxx.com,xxx@xxx.com"
     * @param subject          主题
     * @param verificationCode 验证码
     * @return 发送状态
     */
    @Override
    public boolean sendHtmlMail(String to, String subject, String verificationCode)
    {
        //获取MimeMessage对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            String content = "<center><h1><a href=\"http://www.sunyi.asia\" style=\"text-decoration-line: none; color: #00C5CD\">gobang online</a></h1>\n" +
                    "\t<p><b style=\"font-size: 25px;\">welcome!</b></p>\n" +
                    "\t<p style=\"font-size: 18px\">以下是<b style=\" font-size: 22px\">"+ captchaLength  +"</b>位验证码，有效时间为<b style=\"font-size: 22px \">5分钟</b></p><div style=\"background: #5BAFDE; width: 150px\"><h3 style=\"text-align: center; font-size: 30px; color: white\">" +
                    verificationCode +"</h3></div>\n" +
                    "\t<p style=\"color:gray; font-size: 15px;\">(若您从未请求发送过验证码，请忽略此邮件。)</p></center>";
            messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(from);
            //邮件接收人,设置多个收件人地址
            InternetAddress[] internetAddressTo = InternetAddress.parse(to);
            messageHelper.setTo(internetAddressTo);
            //messageHelper.setTo(to);
            //邮件主题
            message.setSubject(subject);
            //邮件内容，html格式
            messageHelper.setText(content, true);
            //发送
            mailSender.send(message);
            //日志信息
            log.info("邮件已经发送。");
            return true;
        } catch (Exception e) {
            log.error("发送邮件时发生异常！", e);
            return false;
        }
    }

    /**
     * 随机生成验证码和key存入redis，并发送验证码邮件
     *
     * @param to 接受方
     * @return 该条验证码对应的key
     */
    @Override
    public ServerResponseEntity<CaptchaVO> sendEmailCaptcha(String to)
    {
        String captcha = this.captchaGenerate();
        boolean success = this.sendHtmlMail(to, "五子棋对战平台", captcha);
        if(success){    //存入redis
            String key = UUID.randomUUID().toString();
            RedisUtil.hashSet(CacheNames.CAPTCHA_KEY, key+to, captcha, 300);
            CaptchaVO captchaVO = CaptchaVO.builder().key(key).build();
            return ServerResponseEntity.success(captchaVO);
        }
        return ServerResponseEntity.showFailMsg("服务器异常，邮箱验证码发送失败");
    }
}
