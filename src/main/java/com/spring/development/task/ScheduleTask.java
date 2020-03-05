package com.spring.development.task;

import com.spring.development.module.common.entity.Mail;
import com.spring.development.module.common.service.MailService;
import com.spring.development.module.message.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.task
 * @Author xuzhenkui
 * @Date 2020/3/5 16:16
 */
@Component
@EnableScheduling
@EnableAsync
public class ScheduleTask {

    private Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    @Resource
    private MessageService messageService;

    @Resource
    private MailService mailService;

    @Async
    @Scheduled(cron = "0 0/30 * * * ?") //间隔30分钟
    public void UnReadMessageTask(){
        List<String> list = messageService.getUserMail();
        if (list != null)
        list.stream().forEach(e -> {
            if (!"".equals(e) || e != null){
                Mail mail = new Mail();
                mail.setSendTo(e);
                mail.setSubject("处方流转平台");
                mail.setContent(e+": \n您好, 处方流转平台有一条您的消息还未阅读, 快去查看吧!");
                mailService.send(mail);
                logger.info(mail.toString());
            }
        });
    }
}
