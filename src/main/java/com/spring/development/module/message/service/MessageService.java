package com.spring.development.module.message.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.module.message.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.development.module.message.entity.MessageRequest;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-13
 */
public interface MessageService extends IService<Message> {

    Page<Message> getMessageList(MessageRequest request);

    Message getMessage(Message message);

    boolean isRead(Message message);

    Integer getUnReadCount(Message message);

    Integer sendDraftMessage(Message message);

    List<String> getUserMail();
}
