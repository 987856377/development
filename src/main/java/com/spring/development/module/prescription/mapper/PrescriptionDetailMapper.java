package com.spring.development.module.prescription.mapper;

import com.spring.development.module.prescription.entity.PrescriptionDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.development.module.prescription.entity.response.PrescriptionCountData;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-12
 */
public interface PrescriptionDetailMapper extends BaseMapper<PrescriptionDetail> {

    List<PrescriptionCountData> countPrescription();
}
