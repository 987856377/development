package com.spring.development.module.common.service.impl;

import com.spring.development.module.common.entity.Mail;
import com.spring.development.module.common.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.common.service.impl
 * @Author xuzhenkui
 * @Date 2020/2/6 12:42
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sendFrom;
    @Override
    public void sendVerificationCode(Mail mail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sendFrom);
        simpleMailMessage.setTo(mail.getSendTo());
        simpleMailMessage.setSubject(mail.getSubject());
        simpleMailMessage.setText(mail.getContent());
        try{
            javaMailSender.send(simpleMailMessage);
            logger.info(mail.getSendTo() + "的验证码邮件已经发送");
        }catch (Exception e){
            logger.error("发送普通邮件时发生异常: " + mail.getSendTo(),e);
        }
    }
}
