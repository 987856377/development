package com.spring.development.module.message.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.message.entity
 * @Author xuzhenkui
 * @Date 2019/12/10 20:47
 */
public class MessageRequest implements Serializable {
    private Page<Message> page;
    private Long sender;
    private Long receiver;
    private Integer sendFlag;

    public MessageRequest() {
    }

    public MessageRequest(Page<Message> page, Long sender, Long receiver, Integer sendFlag) {
        this.page = page;
        this.sender = sender;
        this.receiver = receiver;
        this.sendFlag = sendFlag;
    }

    public Page<Message> getPage() {
        return page;
    }

    public void setPage(Page<Message> page) {
        this.page = page;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    public Integer getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(Integer sendFlag) {
        this.sendFlag = sendFlag;
    }
}
