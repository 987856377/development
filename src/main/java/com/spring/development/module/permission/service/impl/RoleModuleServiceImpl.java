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
import java.util.Objects;
import java.util.stream.Collectors;

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
        List<RoleModuleResponse> roleModuleResponses = roleModuleMapper.getModulesByRoleList(request.getOwn() ,request.getUserRoles(), null,1);
        if (roleModuleResponses.size() != 0) {
            List<Long> pidList = roleModuleResponses.stream().map(RoleModuleResponse::getId).collect(Collectors.toList());
            List<RoleModuleResponse> modulesByRoleList = roleModuleMapper.getModulesByRoleList(request.getOwn(), request.getUserRoles(), pidList, 2);
            if (modulesByRoleList.size() != 0) {
                roleModuleResponses.forEach(s -> s.setChildren(modulesByRoleList.stream().filter(k -> Objects.equals(s.getId(),k.getPid())).collect(Collectors.toList())));
            }
        }
        return roleModuleResponses;
    }

    @Override
    public boolean addModulesByRole(RoleModuleRequest request) {
        if (request.getMidList() == null || request.getRid() == null){
            return false;
        }
        request.getMidList().forEach((mid) -> {
            roleModuleMapper.addModulesByRole(request.getRid(), mid);
        });
        return true;
    }

    @Override
    public boolean delModulesByRole(RoleModuleRequest request) {
        if (request.getMidList() == null || request.getRid() == null){
            return false;
        }
        request.getMidList().forEach((mid) -> {
            roleModuleMapper.delModulesByRole(request.getRid(), mid);
        });
        return true;
    }
}
