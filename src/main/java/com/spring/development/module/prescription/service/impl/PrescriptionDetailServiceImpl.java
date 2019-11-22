package com.spring.development.module.prescription.service.impl;

import com.spring.development.module.prescription.entity.PrescriptionDetail;
import com.spring.development.module.prescription.mapper.PrescriptionDetailMapper;
import com.spring.development.module.prescription.service.PrescriptionDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
