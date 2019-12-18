package com.spring.development.module.organization.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.organization.entity.Organization;
import com.spring.development.module.organization.entity.request.OrgRequest;
import com.spring.development.module.organization.service.OrganizationService;
import com.spring.development.module.user.entity.UserInfo;
import com.spring.development.module.user.service.UserInfoService;
import com.spring.development.module.user.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-11
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Resource
    private OrganizationService organizationService;

    @Resource
    private UserService userService;

    /*
    {
            "code":"3C151659",
            "name":"淄博市中心医院",
            "classify":2,
            "type":13,
            "host":4,
            "relation":4,
            "postcode":457001,
            "address":"山东省淄博市张店区共青团路54号",
            "phone":"18530320215",
            "mail":"ahaxzh@gmail.com",
            "responser":"许振奎",
            "officer":"许振奎",
            "web":"www.zbzxyy.com",
            "supervising":"淄博市卫生局",
            "introduction":"淄博市卫生局",
            "flag":1
        }
     */
    @RequestMapping("saveOrUpdate")
    public ResultJson saveOrUpdate(@RequestBody Organization organization){
        if (organization==null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.ne("name",organization.getName());
        wrapper.eq("phone",organization.getPhone());
        if (organizationService.getOne(wrapper) != null || !validPhone(organization.getPhone())){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"手机号已被使用或手机号格式有误");
        }
//        添加
        if (organization.getId() == null){
            QueryWrapper codeExisted = new QueryWrapper();
            codeExisted.eq("code",organization.getCode());
            if (organizationService.getOne(codeExisted) != null){
                return ResultJson.failure(ResultCode.BAD_REQUEST,"机构代码已被注册,请核对您的机构代码");
            }
            QueryWrapper superWrapper = new QueryWrapper();
            superWrapper.eq("name",organization.getSupervising());
            Organization one = organizationService.getOne(superWrapper);
            if (one == null){
//            1. 添加的机构没有上级机构: orgflag 生成规则: 机构的 code 前两位拼接后两位
                organization.setOrgflag(organization.getCode().substring(0,2)+organization.getCode().substring(organization.getCode().length()-2));
            }else {
//            2. 添加的机构有上级机构: orgflag生成规则: 上级机构的 orgflag 拼接本机构 code 的后两位
                organization.setOrgflag(one.getOrgflag() + organization.getCode().substring(organization.getCode().length() - 2));
            }
            organization.setDate(new Timestamp(System.currentTimeMillis()));
            return ResultJson.success(organizationService.saveOrUpdate(organization));
        }
//        更新
        return ResultJson.success(organizationService.saveOrUpdate(organization));
    }

    public static boolean validPhone(String phone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (isMatch) {
                return true;
            } else {
                return false;
            }
        }
    }


    /*
    {
            "id":1
        }
     */
    @RequestMapping("getOrgById")
    public ResultJson getOrgById(@RequestBody OrgRequest request){
        if (request.getId() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(organizationService.getById(request.getId()));
    }

    /*
    {
            "name":"潍坊市人民医院"
        }
     */
    @RequestMapping("getOrgByName")
    public ResultJson getOrgByName(@RequestBody OrgRequest request){
        if (request.getName() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("name",request.getName());
        return ResultJson.success(organizationService.list(wrapper));
    }

    /*
    {
            "code":"3C151652",
            "name":"潍坊市人民医院",
            "orgflag":"A"
            "classify":2,
            "type":13,
            "host":4,
            "relation":4,
            "phone":"18530320215",
            "responser":"许振奎",
            "officer":"许振奎",
            "supervising":"潍坊市卫生局",
            "flag":1,
            "current":1,
            "size":5
        }
     */
    @RequestMapping("getOrg")
    public ResultJson getOrg(@RequestBody OrgRequest request){
        if (request == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        if (request.getCurrent() <=0 || request.getSize() <= 0){
            request.setCurrent((long) 1);
            request.setSize(10);
        }
//        设置数据索引偏移量
        Long offset = (request.getCurrent()-1)*request.getSize();
        request.setCurrent(offset);
        return ResultJson.success(organizationService.getOrg(request));
    }

    @RequestMapping("getOrgList")
    public ResultJson getOrgList(@RequestBody Page<Organization> page){
        return ResultJson.success(organizationService.page(page));
    }


    /*
    {
            "name":"淄博市中心医院",
            "flag":1

        }
     */
    @RequestMapping("cancelOrg")
    public ResultJson cancelOrg(@RequestBody OrgRequest request){
        if (request.getName() == null || request.getFlag() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        Organization organization = organizationService.getOrgByName(request.getName());
        organization.setFlag(request.getFlag());
        if (organization == null){
            return ResultJson.failure(ResultCode.GONE);
        } else if (organizationService.cancelOrg(organization)){
            return ResultJson.success(userService.cancelUserOrgCode(organization.getCode(),organization.getFlag()));
        }
        return ResultJson.failure(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /*
    *
    * {
    *   "orgflag": ""
    * }
    *
    * */
    @RequestMapping("getSubOrg")
    public ResultJson getSubOrg(@RequestBody OrgRequest request){
        if (request.getOrgflag() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(organizationService.getSubOrg(request));
    }

    /*
     *
     * {
     *   "id": ""
     * }
     *
     * */
    @RequestMapping("getOrgInfoByUid")
    public ResultJson getOrgInfoByUid(@RequestBody OrgRequest request){
        if (request.getId() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(organizationService.getOrgInfoByUid(request));
    }

    /*
     *
     * {
     *   "name": ""
     * }
     *
     * */
    @RequestMapping("getOrgCodeAndUsersByName")
    public ResultJson getOrgCodeAndUsersByName(@RequestBody OrgRequest request){
        if (request.getName() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(organizationService.getOrgCodeAndUsersByName(request));
    }

    /*
     *
     * {
     *   "code": ""
     * }
     *
     * */
    @RequestMapping("getOrgIntroductionByCode")
    public ResultJson getOrgIntroductionByCode(@RequestBody OrgRequest request){
        if (request.getCode() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(organizationService.getOrgIntroductionByCode(request));
    }
}
