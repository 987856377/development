package com.spring.development.kafka.producer;

import com.spring.development.common.ResultJson;
import com.spring.development.kafka.producer.request.SendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.kafka.producer
 * @Author xuzhenkui
 * @Date 2019/12/19 17:26
 */
@RestController
@RequestMapping("kafka")
public class ProducerController {
    private final Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping(value = "send", method = RequestMethod.POST)
    public ResultJson send(@RequestBody SendRequest request){
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(request.getTopic(), request.getTaskId(), request.getMessage());
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.error("Topic: " + request.getTopic() + " 生产者: 发送消息失败：" + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Topic: " + request.getTopic() + " 生产者: 发送消息成功：" + result.toString());
            }
        });
        return ResultJson.success();
    }
}
