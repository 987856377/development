package com.spring.development.module.prescription.mapper;

import com.spring.development.module.prescription.entity.CirculationInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-12
 */
public interface CirculationInfoMapper extends BaseMapper<CirculationInfo> {

    boolean acceptPrescription(@Param("id") Long id, @Param("acceptStatus") Integer acceptStatus, @Param("changeTime") Timestamp changeTime, @Param("extra") String extra);
}
