package com.spring.development.module.permission.service.impl;

import com.spring.development.module.permission.entity.Module;
import com.spring.development.module.permission.entity.RoleModule;
import com.spring.development.module.permission.entity.request.RoleModuleRequest;
import com.spring.development.module.permission.entity.response.RoleModuleResponse;
import com.spring.development.module.permission.mapper.RoleModuleMapper;
import com.spring.development.module.permission.service.RoleModuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-12-12
 */
@Service
public class RoleModuleServiceImpl extends ServiceImpl<RoleModuleMapper, RoleModule> implements RoleModuleService {

    @Resource
    private RoleModuleMapper roleModuleMapper;

    @Override
    public List<RoleModuleResponse> getModulesByRoles(RoleModuleRequest request) {
        if (request == null){
            return null;
        }
//        List<RoleModuleResponse> roleModuleResponses = new ArrayList<>();
//        roleModuleResponses = roleModuleMapper.getModulesByRoles(request.getUserRoles(),1);
//        for (RoleModuleResponse response : roleModuleResponses){
//            response.setSubModule(roleModuleMapper.getSubModulesByRoles(request.getUserRoles(), response.getId(),2));
//        }
        List<RoleModuleResponse> roleModuleResponses = new ArrayList<>();
        roleModuleResponses = roleModuleMapper.getModulesByRoleList(request.getUserRoles(), "",1);
        for (RoleModuleResponse response : roleModuleResponses){
            response.setSubModule(roleModuleMapper.getModulesByRoleList(request.getUserRoles(), response.getId().toString(),2));
        }
        return roleModuleResponses;
    }
}
