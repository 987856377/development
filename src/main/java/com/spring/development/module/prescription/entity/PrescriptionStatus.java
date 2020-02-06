package com.spring.development.module.prescription.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class PrescriptionStatus extends Model<PrescriptionStatus> {

    private static final long serialVersionUID = 1L;

    /**
     * 处方状态ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 处方ID (关联 prescription_detail 表 id)
     */
    private Long pid;

    /**
     * 流转状态: 1:正常,0:停用
     */
    private Integer flag = 0;

    /**
     * 审核员 (关联user表的 id)
     */
    private Long operator;

    /**
     * 审核员 (关联user表的 id)
     */
    private String operatorName;

    /**
     * 审核状态: 1:已审核,0: 未通过, 9: 待审核
     */
    private Integer verify = 0;

    /**
     * 审核日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp verifyTime;

    /**
     * 开停方日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp forbiddenTime;

    /**
     * 是否可流转 1:可以,0: 不可以
     */
    private Integer enable = 0;

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
        return "PrescriptionStatus{" +
                "id=" + id +
                ", pid=" + pid +
                ", flag=" + flag +
                ", operator=" + operator +
                ", operatorName='" + operatorName + '\'' +
                ", verify=" + verify +
                ", verifyTime=" + verifyTime +
                ", forbiddenTime=" + forbiddenTime +
                ", enable=" + enable +
                ", extra='" + extra + '\'' +
                '}';
    }
}
