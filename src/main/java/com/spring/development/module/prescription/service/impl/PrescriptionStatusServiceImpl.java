package com.spring.development.module.prescription.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.module.prescription.entity.PrescriptionStatus;
import com.spring.development.module.prescription.entity.request.PrescriptionRequest;
import com.spring.development.module.prescription.entity.response.PrescriptionResponse;
import com.spring.development.module.prescription.mapper.PrescriptionStatusMapper;
import com.spring.development.module.prescription.service.PrescriptionStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-12
 */
@Service
@Transactional
public class PrescriptionStatusServiceImpl extends ServiceImpl<PrescriptionStatusMapper, PrescriptionStatus> implements PrescriptionStatusService {

    @Resource
    private PrescriptionStatusMapper prescriptionStatusMapper;

    @Override
    public boolean stopCirculate(PrescriptionStatus prescriptionStatus) {
        if (prescriptionStatus.getPid() == null || prescriptionStatus.getFlag() == null || prescriptionStatus.getForbiddenTime() == null){
            return false;
        }
        return prescriptionStatusMapper.stopCirculate(prescriptionStatus.getPid(),prescriptionStatus.getFlag(),prescriptionStatus.getForbiddenTime(),
                prescriptionStatus.getExtra());
    }

    @Override
    public boolean verifyPrescription(PrescriptionStatus prescriptionStatus) {
        if (prescriptionStatus.getPid() == null || prescriptionStatus.getOperator() == null || prescriptionStatus.getOperatorName() == null
                || prescriptionStatus.getVerify() == null || prescriptionStatus.getVerifyTime() == null){
            return false;
        }
        return prescriptionStatusMapper.verifyPrescription(prescriptionStatus.getPid(),prescriptionStatus.getOperator(),prescriptionStatus.getOperatorName(),
                prescriptionStatus.getVerify(),prescriptionStatus.getVerifyTime(), prescriptionStatus.getExtra());
    }

    @Override
    public boolean enableCirculate(PrescriptionStatus prescriptionStatus) {
        if (prescriptionStatus.getPid() == null || prescriptionStatus.getEnable() == null){
            return false;
        }
        return prescriptionStatusMapper.enableCirculate(prescriptionStatus.getPid(),prescriptionStatus.getEnable(), prescriptionStatus.getExtra());
    }

    @Override
    public List<PrescriptionStatus> getWaitingVarified(PrescriptionStatus prescriptionStatus) {
        if (prescriptionStatus.getVerify() == null){
            return new ArrayList<>();
        }
        return prescriptionStatusMapper.getWaitingVarified(prescriptionStatus.getVerify());
    }

    @Override
    public List<PrescriptionStatus> getCirculated(PrescriptionStatus prescriptionStatus) {
        if (prescriptionStatus.getFlag() == null){
            return new ArrayList<>();
        }
        return prescriptionStatusMapper.getCirculated(prescriptionStatus.getFlag());
    }

    @Override
    public Page<PrescriptionResponse> getPrescriptionList(PrescriptionRequest request) {
        if (request == null){
            return null;
        }
        return prescriptionStatusMapper.getPrescriptionList(request.getPage(), request.getId(), request.getOrgname(), request.getDepartment(), request.getType(),
                request.getSymptom(),request.getFlag(),request.getVerify(),request.getEnable());
    }
}
