package com.spring.development.module.message.service;

import com.spring.development.module.message.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

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

    List<Message> getMessageList(Message message);

    Message getMessage(Message message);
}
