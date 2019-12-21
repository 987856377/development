package com.spring.development.module.user.controller;

import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.user.entity.Role;
import com.spring.development.module.user.entity.request.RoleRequest;
import com.spring.development.module.user.service.RoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;


/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.controller
 * @Author xuzhenkui
 * @Date 2019/11/12 14:47
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @RequestMapping("saveOrUpdate")
    public ResultJson saveOrUpdate(@RequestBody Role role){
        if (role==null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        if (role.getId() == null){
            role.setCreateTime(new Timestamp(System.currentTimeMillis()));
            role.setModifyTime(new Timestamp(System.currentTimeMillis()));
            return ResultJson.success(roleService.saveOrUpdate(role));
        }
        role.setModifyTime(new Timestamp(System.currentTimeMillis()));
        return ResultJson.success(roleService.saveOrUpdate(role));
    }

    @RequestMapping("getRoleList")
    public ResultJson getRoleList(){
        return ResultJson.success(roleService.getRoleList());
    }

    @RequestMapping("updateRoleState")
    public ResultJson updateRoleState(@RequestBody RoleRequest request){
        return ResultJson.success(roleService.updateRoleState(request));
    }
}
