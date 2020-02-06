package com.spring.development.module.common.entity;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.common.entity
 * @Author xuzhenkui
 * @Date 2020/2/6 12:58
 */
public class Mail implements Serializable {
    private String sendTo;
    private String subject;
    private String content;

    public Mail() {
    }

    public Mail(String sendTo, String subject, String content) {
        this.sendTo = sendTo;
        this.subject = subject;
        this.content = content;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "sendTo='" + sendTo + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
