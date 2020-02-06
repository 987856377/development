package com.spring.development.module.common.entity.request;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.common.entity.request
 * @Author xuzhenkui
 * @Date 2020/2/6 12:54
 */
public class MailRequest implements Serializable {
    private String sendTo;
    private String verifyCode;
    private String password;
    public MailRequest() {
    }

    public MailRequest(String sendTo, String verifyCode, String password) {
        this.sendTo = sendTo;
        this.verifyCode = verifyCode;
        this.password = password;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "MailRequest{" +
                "sendTo='" + sendTo + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
