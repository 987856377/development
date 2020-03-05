//package com.spring.development.kafka.consumer;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Assert;
//
///**
// * @Description
// * @Project development
// * @Package com.spring.development.kafka.consumer
// * @Author xuzhenkui
// * @Date 2019/12/19 17:26
// */
//@Component
//public class Consumer {
//
//    private static Logger logger = LoggerFactory.getLogger(Consumer.class);
//
//    @KafkaListener(topics = "development")
//    public void listen (ConsumerRecord<?, ?> record) {
//        logger.info("Topic: {} - TaskId: {} - Offset: {}", record.topic(), record.key(), record.offset());
//        logger.info("Message: {}", record.value());
//    }
//}
