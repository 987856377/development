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
import java.util.List;

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
            "flag":1
        }
     */
    @RequestMapping("saveOrUpdate")
    public ResultJson saveOrUpdate(@RequestBody Organization organization){
        if (organization==null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(organizationService.saveOrUpdate(organization));
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
        wrapper.eq("name",request.getName());
        return ResultJson.success(organizationService.getOne(wrapper));
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

    @RequestMapping("getSubOrg")
    public ResultJson getSubOrg(@RequestBody OrgRequest request){
        if (request.getOrgflag() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(organizationService.getSubOrg(request));
    }

}
