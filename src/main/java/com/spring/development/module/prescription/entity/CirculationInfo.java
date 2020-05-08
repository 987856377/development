package com.spring.development.module.prescription.entity;

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
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CirculationInfo extends Model<CirculationInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 流转id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 处方ID (关联 prescription 表 id)
     */
    private Long pid;

    /**
     * 处方流转者 (关联user表的 id)
     */
    private Long sender;

    /**
     * 处方流转者姓名
     */
    private String senderName;

    /**
     * 处方来源机构  (关联 organization表 code)
     */
    private String originCode;

    /**
     * 处方来源机构名称
     */
    private String originName;

    /**
     * 处方来源时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp originTime;

    /**
     * 到达机构 (关联 organization表 code)
     */
    private String achieveCode;

    /**
     * 到达机构名称
     */
    private String achieveName;

    /**
     * 接收者  (关联user表的 id)
     */
    private Long receiver;

    /**
     * 接收者姓名
     */
    private String receiverName;

    /**
     * 接收状态 1:已接收 ,0:未接收, 9. 拒绝, -1. 已撤回
     */
    private Integer acceptStatus = 0;

    /**
     * 接收状态改变时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp changeTime;

    /**
     * 备注
     */
    private String extra;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CirculationInfo{" +
                "id=" + id +
                ", pid=" + pid +
                ", sender=" + sender +
                ", senderName='" + senderName + '\'' +
                ", originCode='" + originCode + '\'' +
                ", originName='" + originName + '\'' +
                ", originTime=" + originTime +
                ", achieveCode='" + achieveCode + '\'' +
                ", achieveName='" + achieveName + '\'' +
                ", receiver=" + receiver +
                ", receiverName='" + receiverName + '\'' +
                ", acceptStatus=" + acceptStatus +
                ", changeTime=" + changeTime +
                ", extra='" + extra + '\'' +
                '}';
    }
}
