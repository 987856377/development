package com.spring.development.module.prescription.service;

import com.spring.development.module.prescription.entity.CirculationInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-12
 */
public interface CirculationInfoService extends IService<CirculationInfo> {

    boolean acceptPrescription(CirculationInfo circulationInfo);
}
