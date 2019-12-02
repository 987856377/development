package com.spring.development.module.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.annotation.Auth;
import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.user.entity.User;
import com.spring.development.module.user.entity.UserInfo;
import com.spring.development.module.user.entity.request.UserListRequest;
import com.spring.development.module.user.entity.request.UserRequest;
import com.spring.development.module.user.entity.response.UserResponse;
import com.spring.development.module.user.service.UserInfoService;
import com.spring.development.module.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        else if (userInfo.getId() == null || userInfo.getName() == null || userInfo.getIdentity() == null || userInfo.getPhone() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE, "请完善用户信息");
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.ne("name",userInfo.getName());
        wrapper.eq("phone",userInfo.getPhone());
        wrapper.eq("identity",userInfo.getIdentity());
        if (userInfoService.getOne(wrapper) != null || !validPhone(userInfo.getPhone())){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"手机号已被使用或手机号格式有误");
        }
        User user = userService.getById(userInfo.getId());
        if (user == null){
            ResultJson.failure(ResultCode.CONFLICT,"完善用户信息失败, 获取不到用户 id");
        }
        return ResultJson.success(userInfoService.saveOrUpdate(userInfo));
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
    @PreAuthorize("hasAnyAuthority('ADMIN','DBA')")
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
    @RequestMapping("getUserInfoByOrgCodeOrName")
    public ResultJson getUserInfoByOrgCodeOrName(@RequestBody UserInfo userInfo){
        if (userInfo == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(userInfoService.getUserInfoByOrgCodeOrName(userInfo));
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
    @PreAuthorize("hasAnyAuthority('ADMIN','DBA')")
    @RequestMapping("getUserInfoPage")
    public ResultJson getUserInfoPage(@RequestBody UserListRequest request){
        return ResultJson.success(userInfoService.userInfoPage(request));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','DBA')")
    @RequestMapping("getUserInfo")
    public ResultJson getUserInfo(@RequestBody UserRequest request){
        return ResultJson.success(userInfoService.getUserInfo(request));
    }
}
