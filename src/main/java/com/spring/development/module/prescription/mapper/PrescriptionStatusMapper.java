package com.spring.development.module.prescription.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.module.prescription.entity.PrescriptionStatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.development.module.prescription.entity.response.PrescriptionResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-12
 */
@Repository
public interface PrescriptionStatusMapper extends BaseMapper<PrescriptionStatus> {

    boolean stopCirculate(@Param("pid") Long pid, @Param("flag") Integer flag, @Param("forbiddenTime") Timestamp forbiddenTime,
                          @Param("extra") String extra);

    boolean verifyPrescription(@Param("pid") Long pid, @Param("operator") Long operator, @Param("operatorName") String operatorName,
                               @Param("verify") Integer verify, @Param("verifyTime") Timestamp verifyTime, @Param("extra") String extra);

    boolean enableCirculate(@Param("pid") Long pid, @Param("enable") Integer enable, @Param("extra") String extra);

    List<PrescriptionStatus> getWaitingVarified(@Param("verify") Integer verify);

    List<PrescriptionStatus> getCirculated(@Param("flag") Integer flag);

    Page<PrescriptionResponse> getPrescriptionList(Page<PrescriptionResponse> page,
                                                    @Param("id") Long id, @Param("orgname") String orgname,
                                                   @Param("department") String department, @Param("type") Integer type,
                                                   @Param("symptom") String symptom, @Param("flag") Integer flag,
                                                   @Param("verify") Integer verify, @Param("enable") Integer enable);

    PrescriptionResponse getPrescriptionById(@Param("pid") Long pid);
}
