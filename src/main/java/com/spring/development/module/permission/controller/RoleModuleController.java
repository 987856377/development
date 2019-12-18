package com.spring.development.module.permission.controller;


import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.permission.entity.request.RoleModuleRequest;
import com.spring.development.module.permission.service.RoleModuleService;
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
 * @since 2019-12-12
 */
@RestController
@RequestMapping("/permission/role-module")
public class RoleModuleController {
    @Resource
    private RoleModuleService roleModuleService;

    @RequestMapping("getModulesByRoles")
    public ResultJson getModulesByRoles(@RequestBody RoleModuleRequest request){
        if (request == null) {
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(roleModuleService.getModulesByRoles(request));
    }

    @RequestMapping("addModulesByRole")
    public ResultJson addModulesByRole(@RequestBody RoleModuleRequest request){
        if (request == null) {
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(roleModuleService.addModulesByRole(request));
    }

    @RequestMapping("delModulesByRole")
    public ResultJson delModulesByRole(@RequestBody RoleModuleRequest request){
        if (request == null) {
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(roleModuleService.delModulesByRole(request));
    }

}
