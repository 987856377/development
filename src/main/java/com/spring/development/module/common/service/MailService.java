package com.spring.development.module.common.service;

import com.spring.development.module.common.entity.Mail;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.common.service
 * @Author xuzhenkui
 * @Date 2020/2/6 12:41
 */
public interface MailService {
    void sendVerificationCode(Mail mail);
}
