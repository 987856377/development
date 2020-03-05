//package com.spring.development.kafka.producer.request;
//
//import java.io.Serializable;
//
///**
// * @Description
// * @Project development
// * @Package com.spring.development.kafka.producer.request
// * @Author xuzhenkui
// * @Date 2019/12/19 20:03
// */
//public class SendRequest implements Serializable {
//    private String topic;
//    private String taskId;
//    private String message;
//
//    public SendRequest() {
//    }
//
//    public SendRequest(String topic, String taskId, String message) {
//        this.topic = topic;
//        this.taskId = taskId;
//        this.message = message;
//    }
//
//    public String getTopic() {
//        return topic;
//    }
//
//    public void setTopic(String topic) {
//        this.topic = topic;
//    }
//
//    public String getTaskId() {
//        return taskId;
//    }
//
//    public void setTaskId(String taskId) {
//        this.taskId = taskId;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//}
