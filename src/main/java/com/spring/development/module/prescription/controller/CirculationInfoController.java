package com.spring.development.module.prescription.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.common.entity.Mail;
import com.spring.development.module.common.service.MailService;
import com.spring.development.module.prescription.entity.CirculationInfo;
import com.spring.development.module.prescription.entity.request.CirculationInfoRequest;
import com.spring.development.module.prescription.entity.request.PrescriptionRequest;
import com.spring.development.module.prescription.service.CirculationInfoService;
import com.spring.development.module.user.entity.UserInfo;
import com.spring.development.module.user.service.UserInfoService;
import com.spring.development.util.ThreadPoolExecutorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(CirculationInfoController.class);

    @Resource
    private CirculationInfoService circulationInfoService;

    @Resource
    private MailService mailService;

    @Resource
    private UserInfoService userInfoService;

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
        if(circulationInfo.getAcceptStatus() == 0){
            circulationInfo.setOriginTime(new Timestamp(System.currentTimeMillis()));
        }
        circulationInfo.setChangeTime(new Timestamp(System.currentTimeMillis()));
        ThreadPoolExecutorFactory.getThreadPoolExecutor().execute(() -> {
            Mail mail = new Mail();
            UserInfo userInfo = userInfoService.getById(circulationInfo.getReceiver());
            mail.setSendTo(userInfo.getMail());
            mail.setSubject("处方流转平台");
            mail.setContent("您有一条新的处方, 请注意查收!");
            logger.info("处方发送邮件通知: " + mail.toString());
            mailService.send(mail);
        });
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
