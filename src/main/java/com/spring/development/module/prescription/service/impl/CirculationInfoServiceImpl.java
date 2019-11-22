package com.spring.development.module.prescription.service.impl;

import com.spring.development.module.prescription.entity.CirculationInfo;
import com.spring.development.module.prescription.mapper.CirculationInfoMapper;
import com.spring.development.module.prescription.service.CirculationInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
public class CirculationInfoServiceImpl extends ServiceImpl<CirculationInfoMapper, CirculationInfo> implements CirculationInfoService {

    @Resource
    private CirculationInfoMapper circulationInfoMapper;

    @Override
    public boolean acceptPrescription(CirculationInfo circulationInfo) {
        if (circulationInfo.getId() == null || circulationInfo.getAcceptStatus() == null){
            return false;
        }
        return circulationInfoMapper.acceptPrescription(circulationInfo.getId(),circulationInfo.getAcceptStatus(), circulationInfo.getChangeTime(),
                circulationInfo.getExtra());
    }
}
