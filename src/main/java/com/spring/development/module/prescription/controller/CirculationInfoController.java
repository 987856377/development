package com.spring.development.module.prescription.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.prescription.entity.CirculationInfo;
import com.spring.development.module.prescription.entity.request.CirculationInfoRequest;
import com.spring.development.module.prescription.entity.request.PrescriptionRequest;
import com.spring.development.module.prescription.service.CirculationInfoService;
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
@RequestMapping("/prescription/circulationinfo")
public class CirculationInfoController {

    @Resource
    private CirculationInfoService circulationInfoService;

    /*
    {
	"pid":4,
	"sender":3,
	"senderName":"许振奎",
	"originCode":"3C151659",
	"originName":"淄博市中心医院",
	"achieveCode":"3C151686",
	"achieveName":"淄博市石桥卫生院",
	"receiver":12,
	"receiverName":"张昌帅",
	"extra":"请接收"
}
     */
    @RequestMapping("saveOrUpdate")
    public ResultJson saveOrUpdate(@RequestBody CirculationInfo circulationInfo){
        if (circulationInfo == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        circulationInfo.setOriginTime(new Timestamp(System.currentTimeMillis()));
        return ResultJson.success(circulationInfoService.saveOrUpdate(circulationInfo));
    }

    /*
        id, acceptStatus, extra
     */

    @RequestMapping("acceptPrescription")
    public ResultJson acceptPrescription(@RequestBody CirculationInfo circulationInfo){
        if (circulationInfo.getId() == null || circulationInfo.getAcceptStatus() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        circulationInfo.setChangeTime(new Timestamp(System.currentTimeMillis()));
        return ResultJson.success(circulationInfoService.acceptPrescription(circulationInfo));
    }

    /*
    {
        "orgname":"淄博市中心医院",
        "verify":0
        }
     */
    @RequestMapping("getCirculationInfo")
    public ResultJson getCirculationInfo(@RequestBody CirculationInfo circulationInfo){
        if (circulationInfo == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        QueryWrapper<CirculationInfo> wrapper = new QueryWrapper();
        wrapper.eq("pid",circulationInfo.getPid());
        return ResultJson.success(circulationInfoService.list(wrapper));
    }

    /*
    {
        "code":"3C151659",
        }
     */
    @RequestMapping("getCirculationInfoList")
    public ResultJson getCirculationInfoList(@RequestBody CirculationInfoRequest request){
        if (request.getCode() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        QueryWrapper<CirculationInfo> wrapper = new QueryWrapper();
        wrapper.eq("origin_code",request.getCode()).or().eq("achieve_code",request.getCode());
        return ResultJson.success(circulationInfoService.page(request.getPage(),wrapper));
    }

}
