package com.spring.development.module.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.common.entity.Mail;
import com.spring.development.module.common.entity.request.MailRequest;
import com.spring.development.module.common.service.MailService;
import com.spring.development.module.user.entity.User;
import com.spring.development.module.user.entity.UserInfo;
import com.spring.development.module.user.entity.request.ResetPasswordRequest;
import com.spring.development.module.user.service.UserInfoService;
import com.spring.development.module.user.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.common
 * @Author xuzhenkui
 * @Date 2019/11/24 21:10
 */
@RestController
public class MailController {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Resource
    private MailService mailService;

    @Resource
    private UserService userService;

    @Resource
    private UserInfoService userInfoService;

    private String sendTo;

    private Date sendDate;

    private String verifyCode;

    @RequestMapping("sendVerificationCode")
    public ResultJson sendVerificationCode(@RequestBody MailRequest request){
        Mail mail = new Mail();
        mail.setSendTo(request.getSendTo());
        this.sendTo = request.getSendTo();
        mail.setSubject("重置密码");
        StringBuilder builder = new StringBuilder("您的验证码是: ");
        String verifyCode = String
                .valueOf(new Random().nextInt(899999) + 100000);
        this.verifyCode = verifyCode;
        builder.append(verifyCode).append("\n验证码 30 分钟内有效, 请谨慎保存!");
        mail.setContent(builder.toString());
        mailService.sendVerificationCode(mail);
        this.sendDate = new Date(System.currentTimeMillis());
        return ResultJson.success();
    }

    @RequestMapping("resetPassword")
    public ResultJson resetPassword(@RequestBody MailRequest request){
        Calendar sendTime = Calendar.getInstance();
        Calendar verifyTime = Calendar.getInstance();
        sendTime.setTime(this.sendDate);
        sendTime.add(Calendar.MINUTE,30);
        verifyTime.setTime(new Date());
        if (request.getPassword() == null || verifyTime.after(sendTime) || !this.verifyCode.equals(request.getVerifyCode())){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE,"验证码不正确");
        }

        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mail",this.sendTo);
        Long id = userInfoService.getOne(queryWrapper).getId();

        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.lambda().set(User::getPassword, passwordEncoder.encode(request.getPassword()));
        wrapper.lambda().set(User::getModifyTime, new Timestamp(System.currentTimeMillis()));
        wrapper.lambda().eq(User::getId, id);
        return ResultJson.success(userService.update(wrapper));
    }
    
}
