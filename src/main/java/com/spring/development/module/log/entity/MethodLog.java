package com.spring.development.module.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-07-25
 */
public class MethodLog extends Model<MethodLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 调用ip
     */
    private String remoteIp;

    /**
     * 服务ip
     */
    private String localIp;

    /**
     * 接口名
     */
    private String methodName;

    /**
     * 调用时间
     */
    private LocalDateTime invokeTime;

    /**
     * 入参
     */
    private String input;

    /**
     * 出参
     */
    private String output;

    /**
     * 耗时 单位 ms
     */
    private String consumeTime;

    public MethodLog() {
    }

    public MethodLog(String remoteIp, String localIp, String methodName, LocalDateTime invokeTime, String input, String output, String consumeTime) {
        this.remoteIp = remoteIp;
        this.localIp = localIp;
        this.methodName = methodName;
        this.invokeTime = invokeTime;
        this.input = input;
        this.output = output;
        this.consumeTime = consumeTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }
    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public LocalDateTime getInvokeTime() {
        return invokeTime;
    }

    public void setInvokeTime(LocalDateTime invokeTime) {
        this.invokeTime = invokeTime;
    }
    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
    public String getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(String consumeTime) {
        this.consumeTime = consumeTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MethodLog{" +
            "id=" + id +
            ", remoteIp=" + remoteIp +
            ", localIp=" + localIp +
            ", methodName=" + methodName +
            ", invokeTime=" + invokeTime +
            ", input=" + input +
            ", output=" + output +
            ", consumeTime=" + consumeTime +
        "}";
    }
}
