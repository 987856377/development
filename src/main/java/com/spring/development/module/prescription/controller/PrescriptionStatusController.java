package com.spring.development.module.prescription.controller;


import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.prescription.entity.PrescriptionStatus;
import com.spring.development.module.prescription.entity.request.PrescriptionRequest;
import com.spring.development.module.prescription.service.PrescriptionStatusService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-12
 */
@RestController
@RequestMapping("/prescription/status")
public class PrescriptionStatusController {

    @Resource
    private PrescriptionStatusService prescriptionStatusService;

    /*
        description: 只需传 pid, flag, extra
     */
    @RequestMapping("stopCirculate")
    public ResultJson stopCirculate(@RequestBody PrescriptionStatus prescriptionStatus){
        if (prescriptionStatus.getPid() == null || prescriptionStatus.getFlag() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        prescriptionStatus.setForbiddenTime(new Timestamp(System.currentTimeMillis()));
        return ResultJson.success(prescriptionStatusService.stopCirculate(prescriptionStatus));
    }

    /*
        description: 只需传 pid, operator,operatorName, verify, extra
     */
    @RequestMapping("verifyPrescription")
    public ResultJson verifyPrescription(@RequestBody PrescriptionStatus prescriptionStatus){
        if (prescriptionStatus.getPid() == null || prescriptionStatus.getOperator() == null || prescriptionStatus.getVerify() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        prescriptionStatus.setVerifyTime(new Timestamp(System.currentTimeMillis()));
        return ResultJson.success(prescriptionStatusService.verifyPrescription(prescriptionStatus));
    }

    /*
        description: 只需传 pid, enable, extra
     */
    @RequestMapping("enableCirculate")
    public ResultJson enableCirculate(@RequestBody PrescriptionStatus prescriptionStatus){
        if (prescriptionStatus.getPid() == null || prescriptionStatus.getEnable() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(prescriptionStatusService.enableCirculate(prescriptionStatus));
    }

    /*
        description: 只需传 varify
        "verify":0
     */
    @RequestMapping("getWaitingVarified")
    public ResultJson getWaitingVarified(@RequestBody PrescriptionStatus prescriptionStatus){
        if (prescriptionStatus.getVerify() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(prescriptionStatusService.getWaitingVarified(prescriptionStatus));
    }

    /*
        description: 只需传 flag

        "flag":0
     */
    @RequestMapping("getCirculated")
    public ResultJson getCirculated(@RequestBody PrescriptionStatus prescriptionStatus){
        if (prescriptionStatus.getFlag() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(prescriptionStatusService.getCirculated(prescriptionStatus));
    }

    /*
    {
        "orgname":"淄博市中心医院",
        "verify":0
        }
     */
    @RequestMapping("getPrescriptionList")
    public ResultJson getPrescriptionList(@RequestBody PrescriptionRequest request){
        if (request == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(prescriptionStatusService.getPrescriptionList(request));
    }

}
