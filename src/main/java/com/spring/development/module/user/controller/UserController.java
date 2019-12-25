package com.spring.development.module.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.annotation.Auth;
import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.user.entity.User;
import com.spring.development.module.user.entity.request.ResetPasswordRequest;
import com.spring.development.module.user.entity.request.UserRequest;
import com.spring.development.module.user.service.UserRoleService;
import com.spring.development.module.user.service.UserService;
import com.spring.development.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.controller
 * @Author xuzhenkui
 * @Date 2019/9/18 15:17
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${spring.servlet.header.location}")
    private String HEADER_PATH;

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /*/*
     * @Description 提交 json 格式的参数.
     * 新建不传id,id自增; 修改传id;
     * @param user
     *
        {
            "username":"frank",
            "password":"as"
        }
     * @Return java.lang.Boolean
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/9/18 18:07
     */
    @RequestMapping("saveOrUpdate")
    public ResultJson saveOrUpdate(@RequestBody User user){
        if (user==null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getId() == null){
            if (userService.getByUsername(user.getUsername()) == null){
                return ResultJson.failure(ResultCode.BAD_REQUEST,"用户名已被注册");
            } else {
                user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
                user.setModifyTime(new Timestamp(System.currentTimeMillis()));
                if (userService.saveOrUpdate(user)){
                    return ResultJson.success(userService.getIdByUsername(user.getUsername()));
                }
                return ResultJson.failure(ResultCode.INTERNAL_SERVER_ERROR);
            }
        }
        user.setModifyTime(new Timestamp(System.currentTimeMillis()));
        return ResultJson.success(userService.saveOrUpdate(user));
    }

    /*/*
     * @Description 提交 json 格式的参数
     * @param id
     *
        {
	        "id":1223
        }
     *
     * @Return com.spring.mybatisplus.model.User
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/9/18 18:06
     */
    @RequestMapping("getUserById")
    public ResultJson getUserById(@RequestBody User user){
        if (user.getId() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(userService.getById(user.getId()));
    }

    /*/*
     * @Description 提交 json 格式的参数
     * @param id
        {
	        "id":1223
        }
     * @Return java.lang.Boolean
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/9/18 18:05
     */
    @RequestMapping("deleteUserById")
    public ResultJson deleteUserById(@RequestBody User user){
        if (user.getId() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(userService.removeById(user.getId()));
    }

    /*/*
     * @Description 提交 json 格式的参数
     * @param null
        {
            "username":"admin"
        }
     * @Return
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/9/22 1:01
     */
    @RequestMapping("getUsersByUsername")
    public ResultJson getUsersByUsername(@RequestBody User user){
        if (user.getUsername() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getUsername,user.getUsername());
        return ResultJson.success(userService.selectList(wrapper));
    }

    /*/*
     * @Description 提交 json 格式的参数
     * @param null
        {
            "username":"admin"
        }
     * @Return
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/9/22 1:01
     */
    @RequestMapping("getByUsername")
    public ResultJson getByUsername(@RequestBody User user){
        if (user.getUsername() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(userService.getByUsername(user.getUsername()));
    }

    /*/*
     * @Description 提交 json 格式的参数
     * @param null
        {
            "username":"admin"
        }
     * @Return
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/9/22 1:01
     */
    @RequestMapping("getIdByUsername")
    public ResultJson getIdByUsername(@RequestBody User user){
        if (user.getUsername() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(userService.getIdByUsername(user.getUsername()));
    }

    /*/*
     * @Description 提交 json 格式的参数
     * @param null
        {
            "username":"admin"
        }
     * @Return
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/9/22 1:01
     */
    @RequestMapping("getByWrapper")
    public ResultJson getByWrapper(@RequestBody User user){
        if (user.getUsername() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getUsername,user.getUsername());
        return ResultJson.success(userService.getByWrapper(wrapper));
    }

    /*/*
     * @Description 提交 json 格式的参数
     * @param null
        {
            "id":"53519",
            "raw":"123456",
            "password":"123456"
        }
     * @Return
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/9/22 1:01
     */
    @RequestMapping("resetPassword")
    public ResultJson resetPassword(@RequestBody ResetPasswordRequest request){
        if (request.getId() == null || request.getRaw() == null || request.getPassword() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        if (passwordEncoder.matches(request.getRaw(), userService.getById(request.getId()).getPassword())) {
            wrapper.lambda().set(User::getPassword, passwordEncoder.encode(request.getPassword()));
            wrapper.lambda().eq(User::getId, request.getId());
            return ResultJson.success(userService.update(wrapper));
        }
        return ResultJson.failure(ResultCode.CONFLICT);
    }

    /*/*
     * @Description 提交 json 格式的参数
     * @param
     *
     * @Return java.util.List<com.spring.mybatisplus.model.User>
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/9/18 18:05
     */
    @RequestMapping("getUserList")
    public ResultJson getUserList(){
        return ResultJson.success(userService.list(null));
    }

    /*/*
     * @Description 提交 json 表单格式的参数, 自定义实现sql分页 , 自定义物理分页
     * @param pageNum
     * @param pageSize
        {
            "current": 1,
            "size": 5
        }
     * @Return com.spring.mybatisplus.common.ResultJson
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/9/22 23:26
     */
    //    @PreAuthorize("hasAuthority('ADMIN') and hasAuthority('DBA')")
    @PreAuthorize("hasAnyAuthority('ADMIN','DBA')")
    @RequestMapping("getUserByPage")
    public ResultJson getUserByPage(@RequestBody Page<User> page){
        if (page.getCurrent() <=0 || page.getSize() <= 0){
            page.setCurrent(1);
            page.setSize(10);
        }
        long offset = (page.getCurrent()-1)*page.getSize();
        return ResultJson.success(userService.getUserByPage(offset,page.getSize()));
    }

    /*/*
     * @Description 提交 json 格式的参数,mybatis 内置分页方式, 框架内置分页
     * @param page
        {
            "current": 1,
            "size": 5
        }
     * @Return com.baomidou.mybatisplus.core.metadata.IPage<com.spring.mybatisplus.model.User>
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/9/18 18:04
     */
    @Auth(roles = {"ADMIN","USER"})
    @RequestMapping("getUserPage")
    public ResultJson getUserPage(@RequestBody Page<User> page){
        return ResultJson.success(userService.page(page));
    }

    @RequestMapping("cancelUserById")
    public ResultJson cancelUserById(@RequestBody UserRequest request){
        if (request.getId() == null || request.getFlag() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(userService.cancelUserById(request.getId(), request.getFlag()));
    }

    @RequestMapping("getRealNameById")
    public ResultJson getRealNameById(@RequestBody UserRequest request){
        if (request.getId() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(userService.getRealNameById(request.getId()));
    }

    @RequestMapping("getHeaderByUsername")
    public ResultJson getHeaderByUsername(@RequestBody UserRequest request){
        if (request.getUsername() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        return ResultJson.success(userService.getOne(new QueryWrapper<User>().eq("username",request.getUsername())).getHeader());
    }

    @RequestMapping(value = "/headerUpload", method = RequestMethod.POST)
    public ResultJson headerUpload(MultipartFile file) {
        if (file.isEmpty()) {
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        String header = file.getOriginalFilename();

        String[] acceptTypes = {"png","jpg","bmp","jpeg","gif","PNG","JPG","BMP","JPEG","GIF"};
        String type = header.substring(header.indexOf(".")+1);
        if (!Arrays.asList(acceptTypes).contains(type)){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }

        // Get the file and save it somewhere
        if (!userService.update(new UpdateWrapper<User>().set("header",file.getOriginalFilename()).eq("username",header.substring(0,header.indexOf("."))))){
            return ResultJson.failure(ResultCode.CONFLICT);
        }
        Path path = Paths.get(HEADER_PATH + file.getOriginalFilename());
        try {
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return ResultJson.failure(ResultCode.INTERNAL_SERVER_ERROR);
        }
        return ResultJson.success();
    }

}