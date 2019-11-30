package com.spring.development.module.user.controller;

import com.spring.development.common.ResultJson;
import com.spring.development.module.user.entity.Role;
import com.spring.development.module.user.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @RequestMapping("getRoleList")
    public ResultJson getRoleList(){
        return ResultJson.success(roleService.getRoleList());
    }
}
