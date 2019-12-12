package com.spring.development.module.permission.service;

import com.spring.development.common.ResultJson;
import com.spring.development.module.permission.entity.Module;
import com.spring.development.module.permission.entity.RoleModule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.development.module.permission.entity.request.RoleModuleRequest;
import com.spring.development.module.permission.entity.response.RoleModuleResponse;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-12-12
 */
public interface RoleModuleService extends IService<RoleModule> {

    List<RoleModuleResponse> getModulesByRoles(RoleModuleRequest request);
}
