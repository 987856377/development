package com.spring.development.module.prescription.controller;


import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.prescription.entity.PrescriptionDetail;
import com.spring.development.module.prescription.service.PrescriptionDetailService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-12
 */
@RestController
@RequestMapping("/prescription/detail")
public class PrescriptionDetailController {

    @Resource
    private PrescriptionDetailService prescriptionDetailService;

    /*
    {
        "orgcode":"3C151659",
        "uid":1,
        "type":1,
        "doctorName":"马乾光",
        "doctorPhone":"13783939561",
        "checkDoctor":"龚振星",
        "checkPhone":"13193681197",
        "symptom":"感冒",
        "sex":"男,女",
        "age":23,
        "date":"2019-11-13 00:00:00",
        "medicine":"阿莫西林 x3",
        "advice":"多喝热水",
        "price":15.6

        description:
        创建触发器, 在 prescription_detail 表添加过一条处方之后, 自动在 prescription_status 表里插入一条数据
        create trigger t_insert_detail
            after insert on prescription_detail
            for each row
		        insert into prescription_status(pid,flag,verify,enable) values (NEW.id,0,0,0);
}
     */
    @RequestMapping("saveOrUpdate")
    public ResultJson saveOrUpdate(@RequestBody PrescriptionDetail prescriptionDetail){
        if (prescriptionDetail==null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(prescriptionDetailService.saveOrUpdate(prescriptionDetail));
    }

}
