package com.spring.development.module.message.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Message extends Model<Message> {

    private static final long serialVersionUID = 1L;

    /**
     * 消息主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发送者 (关联user表的 id)
     */
    private Long sender;

    /**
     * 发送者姓名
     */
    private String senderName;

    /**
     * 来源机构 (关联 organization 表 code)
     */
    private String sendOrgCode;

    /**
     * 来源机构名称
     */
    private String sendOrgName;

    /**
     * 发送时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp sendTime;

    /**
     * 发送标记: 1. 已发 0. 未发
     */
    private Integer sendFlag = 1;

    /**
     * 接收者 (关联user表的 id)
     */
    private Long receiver;

    /**
     * 接收者姓名
     */
    private String receiverName;

    /**
     * 接收机构 (关联 organization 表 code)
     */
    private String acceptOrgCode;

    /**
     * 接收机构名称
     */
    private String acceptOrgName;

    /**
     * 查看时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp readTime;

    /**
     * 标题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;

    /**
     * 阅读标记: 1. 已读 0. 未读
     */
    private Integer readFlag = 0;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", senderName='" + senderName + '\'' +
                ", sendOrgCode='" + sendOrgCode + '\'' +
                ", sendOrgName='" + sendOrgName + '\'' +
                ", sendTime=" + sendTime +
                ", sendFlag=" + sendFlag +
                ", receiver=" + receiver +
                ", receiverName='" + receiverName + '\'' +
                ", acceptOrgCode='" + acceptOrgCode + '\'' +
                ", acceptOrgName='" + acceptOrgName + '\'' +
                ", readTime=" + readTime +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", readFlag=" + readFlag +
                '}';
    }
}
