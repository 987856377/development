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
//        List<RoleModuleResponse> roleModuleResponses = roleModuleMapper.getModulesByRoles(request.getUserRoles(),1);
//        for (RoleModuleResponse response : roleModuleResponses){
//            response.setSubModule(roleModuleMapper.getSubModulesByRoles(request.getUserRoles(), response.getId(),2));
//        }
        List<RoleModuleResponse> roleModuleResponses = roleModuleMapper.getModulesByRoleList(request.getOwn() ,request.getUserRoles(), "",1);
        for (RoleModuleResponse response : roleModuleResponses){
            response.setChildren(roleModuleMapper.getModulesByRoleList(request.getOwn() ,request.getUserRoles(), response.getId().toString(),2));
        }
        return roleModuleResponses;
    }

    @Override
    public boolean addModulesByRole(RoleModuleRequest request) {
        if (request.getMidList() == null || request.getRid() == null){
            return false;
        }
        return roleModuleMapper.addModulesByRole(request.getRid(),request.getMidList());
    }

    @Override
    public boolean delModulesByRole(RoleModuleRequest request) {
        if (request.getMidList() == null || request.getRid() == null){
            return false;
        }
        return roleModuleMapper.delModulesByRole(request.getRid(),request.getMidList());
    }
}
