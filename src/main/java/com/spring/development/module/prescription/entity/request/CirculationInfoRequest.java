package com.spring.development.module.prescription.entity.request;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.module.prescription.entity.CirculationInfo;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.prescription.entity.request
 * @Author xuzhenkui
 * @Date 2019/12/7 21:27
 */
public class CirculationInfoRequest implements Serializable {

    private Page<CirculationInfo> page;
    private String code;

    public CirculationInfoRequest() {
    }

    public CirculationInfoRequest(Page<CirculationInfo> page, String code) {
        this.page = page;
        this.code = code;
    }

    public Page<CirculationInfo> getPage() {
        return page;
    }

    public void setPage(Page<CirculationInfo> page) {
        this.page = page;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CirculationInfoRequest{" +
                "page=" + page +
                ", code='" + code + '\'' +
                '}';
    }
}
