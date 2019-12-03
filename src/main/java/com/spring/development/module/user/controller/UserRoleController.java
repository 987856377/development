package com.spring.development.module.user.controller;

import com.spring.development.annotation.Auth;
import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.user.entity.Role;
import com.spring.development.module.user.entity.request.UserRoleRequest;
import com.spring.development.module.user.service.RoleService;
import com.spring.development.module.user.service.UserRoleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.net.NetworkClient;
import sun.nio.ch.Net;

import javax.annotation.Resource;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.controller
 * @Author xuzhenkui
 * @Date 2019/11/12 14:47
 */
@RestController
@RequestMapping("userrole")
public class UserRoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    /*/*
     * @Description
     * @param userVO
        {
            "uid": 1,
            "destRole":"DBA"
        }
     * @Return com.spring.development.common.ResultJson
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/10/2 20:11
     */
    @RequestMapping("addUserRole")
    public ResultJson addUserRole(@RequestBody UserRoleRequest request){
        if (request.getUid() == null){
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        } else if (request.getDestRole() == null || request.getDestRole().equals("")){
            request.setDestRole("USER");
        }
        Role role = roleService.getRoleByCode(request.getDestRole());
        if (role == null){
            return ResultJson.failure(ResultCode.UNAUTHORIZED,"对不起,该用户角色未定义,详情请咨询管理员");
        }
        return ResultJson.success(userRoleService.addUserRole(request.getUid(),role.getId()));
    }

    /*/*
     * @Description
     * @param userVO
        {
            "uid": 1,
            "sourceRole":"USER",
            "destRole":"DBA"
        }
     * @Return com.spring.development.common.ResultJson
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/10/2 20:11
     */
    @Auth(roles = {"ADMIN"})
    @RequestMapping("setUserRole")
    public ResultJson setUserRole(@RequestBody UserRoleRequest request){
        if (request.getUid() == null || request.getSourceRole() == null || request.getSourceRole().equals("") ||
                request.getSourceRole() == null || request.getSourceRole().equals("")){
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }

        Role sourceRole = roleService.getRoleByCode(request.getSourceRole());
        Role destRole = roleService.getRoleByCode(request.getDestRole());

        if (sourceRole == null || destRole == null){
            return ResultJson.failure(ResultCode.UNAUTHORIZED,"对不起,该用户角色未定义,详情请咨询管理员");
        }
        return ResultJson.success(userRoleService.setUserRole(request.getUid(),sourceRole.getId(),destRole.getId()));
    }

    /*/*
     * @Description
     * @param userVO
        {
            "uid": 1,
            "destRole":"DBA"
        }
     * @Return com.spring.development.common.ResultJson
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/10/2 20:11
     */
    @RequestMapping("delUserRole")
    public ResultJson delUserRole(@RequestBody UserRoleRequest request){
        if (request.getUid() == null || request.getDestRole() == null || request.getDestRole().equals("")){
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        Role role = roleService.getRoleByCode(request.getDestRole());
        if (role == null){
            return ResultJson.failure(ResultCode.UNAUTHORIZED,"对不起,该用户角色未定义,详情请咨询管理员");
        }
        return ResultJson.success(userRoleService.delUserRole(request.getUid(),role.getId()));
    }

    /*
    获取用户拥有的角色
    {
            "uid": 1
        }
     */
    @RequestMapping("getRoleList")
    public ResultJson getRoleList(@RequestBody UserRoleRequest request){
        if (request.getUid() == null){
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        return ResultJson.success(userRoleService.getRoleList(request));
    }

    /*
    获取用户可以操作的角色
        {
                "uid": 1
            }
         */
    @RequestMapping("getRoleListAvalible")
    public ResultJson getRoleListAvalible(@RequestBody UserRoleRequest request){
        if (request.getUid() == null){
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        return ResultJson.success(userRoleService.getRoleListAvalible(request));
    }
}
