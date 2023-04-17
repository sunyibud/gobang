package com.sunyi.gobang.user.controller;

import com.sunyi.gobang.api.user.dto.UserIdDTO;
import com.sunyi.gobang.api.user.dto.UserInfoDTO;
import com.sunyi.gobang.api.user.vo.UserInfoVO;
import com.sunyi.gobang.common.database.model.User;
import com.sunyi.gobang.common.response.ResponseEnum;
import com.sunyi.gobang.common.response.ServerResponseEntity;
import com.sunyi.gobang.common.util.JwtUtil;
import com.sunyi.gobang.user.service.DBUserService;
import com.sunyi.gobang.user.util.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

/**
 * @author Sunyi
 * @date 2023/4/9
 */
@RestController
public class UserInfoController
{
    @Autowired
    HttpServletRequest request;

    @Autowired
    DBUserService dbUserService;

    /**
     * 当访问到这个方法时(不在gateway白名单)，说明了通过了gateway的权限校验，token有效可直接用
     *
     * @return userinfo
     */
    @PostMapping("/user/getLoginUserInfo")
    public ServerResponseEntity<UserInfoVO> getLoginUserInfo()
    {
        String token = request.getHeader("Authorization");
        String realToken = token.replaceFirst("bearer ", "");
        long userId = Long.parseLong(Objects.requireNonNull(
                JwtUtil.getClaimByToken(realToken)).getSubject());
        return ServerResponseEntity.success(dbUserService.getUserInfoByUserId(userId));
    }

    @PostMapping("/user/getUserInfoByUserId")
    public ServerResponseEntity<UserInfoVO> getUserInfoByUserId(@RequestBody UserIdDTO param)
    {
        dbUserService.getUserInfoByUserId(param.getUserId());
        return ServerResponseEntity.success(dbUserService.getUserInfoByUserId(param.getUserId()));
    }

    @PostMapping("/user/updateUserInfo")
    public ServerResponseEntity<Void> updateUserInfo(
            @Valid @RequestBody UserInfoDTO param
            , BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
            return ServerResponseEntity.showFailMsg(
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        if (!param.getUsername().equals(this.getLoginUserInfo().getData().getUsername())
                && dbUserService.isUsernameExistInDatabase(param.getUsername()))
            return ServerResponseEntity.showFailMsg("名称被别人用过了，换一个吧^_^");
        String token = request.getHeader("Authorization");
        String realToken = token.replaceFirst("bearer ", "");
        long userId = Long.parseLong(Objects.requireNonNull(
                JwtUtil.getClaimByToken(realToken)).getSubject());
        boolean success = dbUserService.updateUserInfoByUserId(param, userId);
        if (success)
            return ServerResponseEntity.success();
        return ServerResponseEntity.fail(ResponseEnum.EXCEPTION);
    }

    @PostMapping("/user/uploadHeadIcon")
    public ServerResponseEntity<String> uploadHeadIcon(MultipartFile file)
    {
        String res = ImageUpload.upload(file);
        if(res.contains("images"))
        {
            String token = request.getHeader("Authorization");
            String realToken = token.replaceFirst("bearer ", "");
            long userId = Long.parseLong(Objects.requireNonNull(
                    JwtUtil.getClaimByToken(realToken)).getSubject());
            User user = dbUserService.getById(userId);
            user.setHeadPath(res);
            dbUserService.updateById(user);
            return ServerResponseEntity.success(res);
        }
        return ServerResponseEntity.showFailMsg(res);
    }
}
