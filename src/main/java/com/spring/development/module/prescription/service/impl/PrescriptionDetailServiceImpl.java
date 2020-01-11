package com.spring.development.module.prescription.service.impl;

import com.spring.development.module.prescription.entity.PrescriptionDetail;
import com.spring.development.module.prescription.entity.response.PrescriptionCountData;
import com.spring.development.module.prescription.entity.response.PrescriptionCountResponse;
import com.spring.development.module.prescription.mapper.PrescriptionDetailMapper;
import com.spring.development.module.prescription.service.PrescriptionDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
public class PrescriptionDetailServiceImpl extends ServiceImpl<PrescriptionDetailMapper, PrescriptionDetail> implements PrescriptionDetailService {

    @Resource
    private PrescriptionDetailMapper prescriptionDetailMapper;

    @Override
    public PrescriptionCountResponse countPrescription() {
        PrescriptionCountResponse response = new PrescriptionCountResponse();
        List<PrescriptionCountData> dataList = prescriptionDetailMapper.countPrescription();
        response.setOrgNameList(dataList.stream().map(PrescriptionCountData::getOrgname).collect(Collectors.toList()));
        response.setPreLocalList(dataList.stream().map(PrescriptionCountData::getLocal).collect(Collectors.toList()));
        response.setPreOutsideList(dataList.stream().map(PrescriptionCountData::getOutside).collect(Collectors.toList()));
        response.setPreNormalList(dataList.stream().map(PrescriptionCountData::getNormal).collect(Collectors.toList()));
        response.setPreSpecialList(dataList.stream().map(PrescriptionCountData::getSpecial).collect(Collectors.toList()));
        response.setPreTotalList(dataList.stream().map(PrescriptionCountData::getTotal).collect(Collectors.toList()));
        return response;
    }
}
