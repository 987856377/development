package com.spring.development.module.message.controller;


import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.message.entity.Message;
import com.spring.development.module.message.entity.MessageRequest;
import com.spring.development.module.message.mapper.MessageMapper;
import com.spring.development.module.message.service.MessageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-13
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    private MessageService messageService;

    /*
    {
        "sender":"3",
        "senderName":"许振奎",
        "sendOrgCode":"3C151659",
        "sendOrgName":"淄博市中心医院",
        "receiver":"12",
        "receiverName":"张昌帅",
        "acceptOrgCode":"3C151686",
        "acceptOrgName":"淄博市石桥卫生院",
        "subject":"普通门诊处方请求",
        "content":"我处接收到一病人, 身体多处感觉不适, 头晕, 恶心, 四肢冰凉, 望上级部门开处适用处方"
        }
     */
    @RequestMapping("saveOrUpdate")
    public ResultJson saveOrUpdate(@RequestBody Message message){
        if (message == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        message.setSendTime(new Timestamp(System.currentTimeMillis()));
        return ResultJson.success(messageService.saveOrUpdate(message));
    }

    /*
        sender or reciever, sendFlag
        {
            "sender":"3"
        }
     */
    @RequestMapping("getMessageList")
    public ResultJson getMessageList(@RequestBody MessageRequest request){
        if (request == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(messageService.getMessageList(request));
    }

    /*
        发送者: id , sender
        接收者: id , reciever , readflag, sendFlag
        {
            "sender":"3"
        }
     */
    @RequestMapping("getMessage")
    public ResultJson getMessage(@RequestBody Message message){
        if (message.getId() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(messageService.getMessage(message));
    }

    /*
        {
            "ids": [1 ,2]
        }
     */
    @RequestMapping("delMessage")
    public ResultJson delMessage(@RequestBody List<Long> ids){
        if (ids == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(messageService.removeByIds(ids));
    }

    @RequestMapping("isRead")
    public ResultJson isRead(@RequestBody Message message){
        if (message == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(messageService.isRead(message));
    }

    @RequestMapping("getUnReadCount")
    public ResultJson getUnReadCount(@RequestBody Message message){
        if (message == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(messageService.getUnReadCount(message));
    }
}
