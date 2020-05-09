package com.spring.development.module.prescription.service;

import com.spring.development.module.prescription.entity.PrescriptionDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.development.module.prescription.entity.request.CountPrescriptionRequest;
import com.spring.development.module.prescription.entity.response.PrescriptionCountResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-12
 */
public interface PrescriptionDetailService extends IService<PrescriptionDetail> {

    PrescriptionCountResponse countPrescription(CountPrescriptionRequest countPrescriptionRequest);
}
