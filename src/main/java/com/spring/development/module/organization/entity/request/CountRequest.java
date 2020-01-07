package com.spring.development.module.organization.entity.request;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.organization.entity.request
 * @Author xuzhenkui
 * @Date 2020/1/2 22:17
 */
public class CountRequest implements Serializable {
    private Integer classify;
    private Integer type;
    private Integer host;
    private Integer relation;
    private Integer flag = 1;
    private Timestamp begin;
    private Timestamp end;

    public CountRequest() {
    }

    public CountRequest(Integer classify, Integer type, Integer host, Integer relation, Integer flag, Timestamp begin, Timestamp end) {
        this.classify = classify;
        this.type = type;
        this.host = host;
        this.relation = relation;
        this.flag = flag;
        this.begin = begin;
        this.end = end;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Timestamp getBegin() {
        return begin;
    }

    public void setBegin(Timestamp begin) {
        this.begin = begin;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}
