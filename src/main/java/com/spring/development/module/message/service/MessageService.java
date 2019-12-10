package com.spring.development.module.message.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.module.message.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.development.module.message.entity.MessageRequest;


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
}
