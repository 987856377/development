package com.spring.development.module.organization.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Timestamp;

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
 * @since 2019-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Organization extends Model<Organization> {

    private static final long serialVersionUID = 1L;

    /**
     * 机构ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 机构代码
     */
    private String code;

    /**
     * 机构标志
     */
    private String orgflag;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 机构分类: 1. 非营利性医疗机构  2. 盈利性医疗机构  3. 其他医疗机构  

     */
    private Integer classify;

    /**
     * 机构类型:  
1	省卫生厅
2	市卫生局
3	区卫生局
4	卫生社会团体
5	其他卫生机构
6	健康教育所(站、中心)
7	医学教育机构
8	医学科学研究机构
9	卫生监督检验(监测、检测)所(站)
10	卫生监督所(局)
11	疾病防控制中心(防疫站)
12	专科疾病防治院(所、站)
13	医院
14	采供血机构
15	急救中心(站)
16	妇幼保健院(所、站)
17	门诊部、诊所、医务室、村卫生室
18	卫生院
19	社区卫生服务中心(站)
     */
    private Integer type;

    /**
     * 主办单位:  1. 卫生行政部门  2. 其他行政部门  3. 企业  4. 事业单位  5. 社会团体  6. 其他社会组织
     */
    private Integer host;

    /**
     * 隶属关系: 1. 中央属  2. 省,自治区,直辖市属  3. 省辖市(地区,州,盟)  4. 县级市(省辖市区)属  5. 县(旗)属  6. 镇属  7.  乡属  8. 街道属 
     */
    private Integer relation;

    /**
     * 邮政编码
     */
    private String postcode;

    /**
     * 机构地址
     */
    private String address;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 机构邮箱
     */
    private String mail;

    /**
     * 负责人
     */
    private String responser;

    /**
     * 法人代表
     */
    private String officer;

    /**
     * 机构网站
     */
    private String web;

    /**
     * 上级机构
     */
    private String supervising;

    /**
     * 注册日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp date;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 1:正常,0: 停用:9:待激活
     */
    private Integer flag = 0;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrgflag() {
        return orgflag;
    }

    public void setOrgflag(String orgflag) {
        this.orgflag = orgflag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHost() {
        return host;
    }

    public void setHost(Integer host) {
        this.host = host;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getResponser() {
        return responser;
    }

    public void setResponser(String responser) {
        this.responser = responser;
    }

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getSupervising() {
        return supervising;
    }

    public void setSupervising(String supervising) {
        this.supervising = supervising;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
