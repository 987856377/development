package com.spring.development.module.message.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.module.message.entity.Message;
import com.spring.development.module.message.entity.MessageRequest;
import com.spring.development.module.message.mapper.MessageMapper;
import com.spring.development.module.message.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-13
 */
@Service
@Transactional
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public Page<Message> getMessageList(MessageRequest request) {
        if (request == null){
            return null;
        }
        return messageMapper.getMessageList(request.getPage(), request.getSender(), request.getReceiver(), request.getSendFlag());
    }

    @Override
    public Message getMessage(Message message) {
        if (message.getId() == null){
            return null;
        } else if (message.getSender() != null){
            return messageMapper.getMessage(message.getId(),message.getSender(),message.getReceiver(), message.getSendFlag());
        } else if (message.getReceiver() != null && message.getReadFlag() != null){
            message.setReadTime(new Timestamp(System.currentTimeMillis()));
            if (messageMapper.isRead(message.getId(), message.getReadTime(), message.getReadFlag())) {
                return messageMapper.getMessage(message.getId(),message.getSender(),message.getReceiver(), message.getSendFlag());
            }
        }
        return null;
    }

    @Override
    public boolean isRead(Message message) {
        if (message.getId() == null || message.getReadFlag() == null) {
            return false;
        }
        message.setReadTime(new Timestamp(System.currentTimeMillis()));
        return messageMapper.isRead(message.getId(), message.getReadTime(), message.getReadFlag());
    }

    @Override
    public Integer getUnReadCount(Message message) {
        if (message.getReceiver() == null) {
            return 0;
        }
        return messageMapper.getUnReadCount(message.getReceiver());
    }

    @Override
    public Integer sendDraftMessage(Message message) {
        if (message.getId() == null) {
            return 0;
        }
        return messageMapper.sendDraftMessage(message.getId());
    }

    @Override
    public List<String> getUserMail() {
        return messageMapper.getUserMail();
    }
}
