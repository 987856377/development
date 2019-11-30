package com.spring.development.module.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.annotation.Auth;
import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.user.entity.Role;
import com.spring.development.module.user.entity.User;
import com.spring.development.module.user.entity.UserInfo;
import com.spring.development.module.user.entity.request.UserRequest;
import com.spring.development.module.user.entity.request.UserRoleRequest;
import com.spring.development.module.user.service.RoleService;
import com.spring.development.module.user.service.UserInfoService;
import com.spring.development.module.user.service.UserRoleService;
import com.spring.development.module.user.service.UserService;
import com.spring.development.util.encrypt.BCryptPasswordEncoder;
import com.spring.development.util.encrypt.PasswordEncoder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @Date 2019/9/18 15:17
 */
@RestController
@RequestMapping("/user")
public class UserController {
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
            "password":"123456"
        }
     * @Return
     * @Author XuZhenkui
     * @Creed: Talk is cheap,show me the code
     * @Date 2019/9/22 1:01
     */
    @RequestMapping("resetPassword")
    public ResultJson resetPassword(@RequestBody User user){
        if (user.getId() == null || user.getPassword() == null){
            return ResultJson.failure(ResultCode.NOT_ACCEPTABLE);
        }
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.lambda().set(User::getPassword,passwordEncoder.encode(user.getPassword()));
        wrapper.lambda().eq(User::getId,user.getId());
        return ResultJson.success(userService.update(wrapper));
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
}
