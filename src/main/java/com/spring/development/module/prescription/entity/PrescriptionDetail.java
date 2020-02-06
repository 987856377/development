package com.spring.development.module.prescription.entity;

import java.math.BigDecimal;
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
public class PrescriptionDetail extends Model<PrescriptionDetail> {

    private static final long serialVersionUID = 1L;

    /**
     * 处方代码
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 机构代码 (关联 organization 表 code)
     */
    private String orgcode;

    /**
     * 机构名称
     */
    private String orgname;

    /**
     * 录入处方者 (关联 user 表 id)
     */
    private Long uid;

    /**
     * 录入处方者姓名
     */
    private String uname;

    /**
     * 处方类型: 1. 普通处方  2. 特殊处方
     */
    private Integer type;

    /**
     * 开方医生
     */
    private String doctorName;

    /**
     * 开方医生手机号
     */
    private String doctorPhone;

    /**
     * 归属科室
     */
    private String department;

    /**
     * 审核医生
     */
    private String checkDoctor;

    /**
     * 审核医生手机号
     */
    private String checkPhone;

    /**
     * 适用症状
     */
    private String symptom;

    /**
     * 适用性别
     */
    private String sex;

    /**
     * 使用年龄
     */
    private String age;

    /**
     * 开方日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp date;

    /**
     * 用药
     */
    private String medicine;

    /**
     * 医嘱
     */
    private String advice;

    /**
     * 建议价格
     */
    private BigDecimal price;

    /**
     * 处方来源: 1:本机构,9:外来
     */
    private Integer origin = 1;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PrescriptionDetail{" +
                "id=" + id +
                ", orgcode='" + orgcode + '\'' +
                ", orgname='" + orgname + '\'' +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                ", type=" + type +
                ", doctorName='" + doctorName + '\'' +
                ", doctorPhone='" + doctorPhone + '\'' +
                ", department='" + department + '\'' +
                ", checkDoctor='" + checkDoctor + '\'' +
                ", checkPhone='" + checkPhone + '\'' +
                ", symptom='" + symptom + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", date=" + date +
                ", medicine='" + medicine + '\'' +
                ", advice='" + advice + '\'' +
                ", price=" + price +
                ", origin=" + origin +
                '}';
    }
}
