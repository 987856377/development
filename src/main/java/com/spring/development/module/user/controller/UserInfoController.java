package com.spring.development.module.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.user.entity.User;
import com.spring.development.module.user.entity.UserInfo;
import com.spring.development.module.user.entity.request.UserAndInfoRequest;
import com.spring.development.module.user.entity.request.UserListRequest;
import com.spring.development.module.user.entity.request.UserRequest;
import com.spring.development.module.user.service.UserInfoService;
import com.spring.development.module.user.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
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

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /*/*
         * @Description
         * @param userInfo
            {"user":{
                "username":"admin123456",
                 "password":"as"
                 "flag":"1",
                },
             "userInfo":{
                "orgcode": "3C151659",
                "orgname": "淄博市中心医院",
                "name": "许振奎",
                "nation":"汉族",
                "identity":410928199122151659,
                "sex":"男",
                "age":22,
                "phone":18531323215,
                "mail":"aha11k@gmail.com"
            }}
         * @Return com.spring.development.common.ResultJson
         * @Author XuZhenkui
         * @Creed: Talk is cheap,show me the code
         * @Date 2019/10/2 19:01
    */
    @RequestMapping("create")
    public ResultJson create(@RequestBody UserAndInfoRequest request){
        User user = request.getUser();
        UserInfo userInfo = request.getUserInfo();
        if (user==null || user.getUsername() == null || "".equals(user.getUsername())
                || user.getPassword() == null || "".equals(user.getPassword())
                || userInfo==null
                || userInfo.getName() == null || userInfo.getIdentity() == null
                || userInfo.getPhone() == null|| userInfo.getMail() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        if (userInfoService.getOne(userInfo) != null || !validPhone(userInfo.getPhone()) || !validEmail(userInfo.getMail())){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"身份证号, 手机号, 邮箱已被注册或格式不合法");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userService.getIdByUsername(user.getUsername()) != null){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"用户名已被注册");
        } else {
            user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
            user.setModifyTime(new Timestamp(System.currentTimeMillis()));
            boolean isUserCreated = userService.create(user);
            userInfo.setId(user.getId());
            boolean isUserInfoCreated = userInfoService.save(userInfo);
            if (isUserCreated && isUserInfoCreated){
                return ResultJson.success(user.getId());
            }
            return ResultJson.failure(ResultCode.INTERNAL_SERVER_ERROR);
        }
    }

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
        else if (userInfo.getId() == null || userInfo.getName() == null || userInfo.getIdentity() == null || userInfo.getPhone() == null|| userInfo.getMail() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE, "请完善用户信息");
        }

        User user = userService.getById(userInfo.getId());
        if (user == null){
            ResultJson.failure(ResultCode.CONFLICT,"完善用户信息失败, 获取不到用户 id");
        }
        if (userInfoService.getOne(userInfo) != null || !validPhone(userInfo.getPhone()) || !validEmail(userInfo.getMail())){
            return ResultJson.failure(ResultCode.BAD_REQUEST,"身份证号, 手机号, 邮箱已被注册或格式不合法");
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

    public static boolean validEmail(String email){
        if (null==email || "".equals(email)){
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(email);
        if(m.matches()){
            return true;
        }else{
            return false;
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

    /*/*
     * @Description
     * @param page
        {
            "username": "admin"
        }
     * @Return com.spring.development.common.ResultJson
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/10/2 20:50
     */
    @PreAuthorize("hasAnyAuthority('ADMIN','DBA')")
    @RequestMapping("getUserInfo")
    public ResultJson getUserInfo(@RequestBody UserRequest request){
        return ResultJson.success(userInfoService.getUserInfo(request));
    }

    /*/*
     * @Description
     * @param page
        {
            "username": "admin"
        }
     * @Return com.spring.development.common.ResultJson
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/10/2 20:50
     */
    @RequestMapping("getUserInfoByUsername")
    public ResultJson getUserInfoByUsername(@RequestBody UserRequest request){
        return ResultJson.success(userInfoService.getUserInfoByUsername(request));
    }

    @RequestMapping("countUser")
    public ResultJson countUser(){
        return ResultJson.success(userInfoService.countUser());
    }

    /*/*
    * @Description
    * @param page
       {
           "username": "admin"
       }
    * @Return com.spring.development.common.ResultJson
    * @Author XuZhenkui
    * @Creed: Talk is cheap,show me the code
    * @Date 2019/10/2 20:50
    */
    @RequestMapping("getUserAndOrgInfoByUsername")
    public ResultJson getUserAndOrgInfoByUsername(@RequestBody UserRequest request){
        return ResultJson.success(userInfoService.getUserAndOrgInfoByUsername(request));
    }
}
