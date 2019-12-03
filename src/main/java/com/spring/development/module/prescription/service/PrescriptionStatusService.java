package com.spring.development.module.prescription.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.module.prescription.entity.PrescriptionStatus;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.development.module.prescription.entity.request.PrescriptionRequest;
import com.spring.development.module.prescription.entity.response.PrescriptionResponse;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-12
 */
public interface PrescriptionStatusService extends IService<PrescriptionStatus> {

    boolean stopCirculate(PrescriptionStatus prescriptionStatus);

    boolean verifyPrescription(PrescriptionStatus prescriptionStatus);

    boolean enableCirculate(PrescriptionStatus prescriptionStatus);

    List<PrescriptionStatus> getWaitingVarified(PrescriptionStatus prescriptionStatus);

    List<PrescriptionStatus> getCirculated(PrescriptionStatus prescriptionStatus);

    Page<PrescriptionResponse> getPrescriptionInfo(PrescriptionRequest request);
}
