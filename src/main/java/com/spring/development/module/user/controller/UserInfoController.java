package com.spring.development.module.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.annotation.Auth;
import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.user.entity.User;
import com.spring.development.module.user.entity.UserInfo;
import com.spring.development.module.user.service.UserInfoService;
import com.spring.development.module.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.controller
 * @Author xuzhenkui
 * @Date 2019/11/12 14:46
 */
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    @Resource
    private UserService userService;

    @Resource
    private UserInfoService userInfoService;

    /*/*
         * @Description
         * @param userInfo
            {
                "id": 1,
                "orgcode": "3C151659",
                "orgname": "淄博市中心医院",
                "name": "许振奎",
                "nation":"汉族",
                "identity":410928199702151659,
                "sex":"男",
                "age":22,
                "phone":18530320215,
                "mail":"ahaxzk@gmail.com",
            }
         * @Return com.spring.development.common.ResultJson
         * @Author XuZhenkui
         * @Creed: Talk is cheap,show me the code
         * @Date 2019/10/2 19:01
    */
    @RequestMapping("completeUserInfo")
    public ResultJson completeUserInfo(@RequestBody UserInfo userInfo){
        if (userInfo==null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        else if (userInfo.getId() == null || userInfo.getName() == null || userInfo.getIdentity() == null || userInfo.getPhone() == null || userInfo.getMail() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        User user = userService.getById(userInfo.getId());
        if (user == null){
            ResultJson.failure(ResultCode.CONFLICT);
        }
        return ResultJson.success(userInfoService.saveOrUpdate(userInfo));
    }

    /*/*
 * @Description
 * @param userInfo
    {
        "id": 1
    }
 * @Return com.spring.development.common.ResultJson
 * @Author XuZhenkui
 * @Creed: Talk is cheap,show me the code
 * @Date 2019/10/2 19:05
 */
    @RequestMapping("getUserInfoById")
    public ResultJson getUserInfoById(@RequestBody UserInfo userInfo){
        if (userInfo.getId() == null){
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        return ResultJson.success(userInfoService.getById(userInfo.getId()));
    }


    /*/*
     * @Description
     * @param page
        {
            "current": 1,
            "size": 5
        }
     * @Return com.spring.development.common.ResultJson
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/10/2 20:50
     */
    @Auth(roles = {"ADMIN"})
    @RequestMapping("getUserInfoList")
    public ResultJson getUserInfoList(@RequestBody Page<UserInfo> page){
        return ResultJson.success(userInfoService.page(page));
    }

    @RequestMapping("getUserName")
    public ResultJson getUserNameByOrgCodeOrName(@RequestBody UserInfo userInfo){
        if (userInfo == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(userInfoService.getUserNameByOrgCodeOrName(userInfo));
    }

    /*
    {
            "orgcode":"3C151659"
        }
     */
    @RequestMapping("getUserInfo")
    public ResultJson getUserInfo(@RequestBody UserInfo userInfo){
        if (userInfo == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(userInfoService.getUserInfoByOrgCodeOrName(userInfo));
    }

    /*
    {
            "current": 1,
            "size": 5
        }
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','DBA')")
    @RequestMapping("getUserInfoByPage")
    public ResultJson getUserInfoByPage(@RequestBody Page<UserInfo> page){
        if (page.getCurrent() <=0 || page.getSize() <= 0){
            page.setCurrent(1);
            page.setSize(10);
        }
        return ResultJson.success(userInfoService.page(page));
    }
}
