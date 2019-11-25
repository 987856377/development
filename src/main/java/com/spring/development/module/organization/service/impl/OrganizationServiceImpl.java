package com.spring.development.module.organization.service.impl;

import com.spring.development.module.organization.entity.Organization;
import com.spring.development.module.organization.entity.request.OrgRequest;
import com.spring.development.module.organization.mapper.OrganizationMapper;
import com.spring.development.module.organization.service.OrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-11
 */
@Service
@Transactional
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Resource
    private OrganizationMapper organizationMapper;

    @Override
    public List<Organization> getOrg(OrgRequest request) {
        return organizationMapper.getOrg(request.getId(),request.getCode(),request.getOrgflag(),request.getName(),
                request.getClassify(),request.getType(),request.getHost(),request.getRelation(),
                request.getPhone(), request.getResponser(),request.getOfficer(),request.getSupervising(),
                request.getFlag(),request.getCurrent(),request.getSize());
    }

    @Override
    public Organization getOrgByName(String name) {
        return organizationMapper.getOrgByName(name);
    }

    @Override
    public boolean cancelOrg(Organization organization) {
        if (organization.getCode() == null || organization.getFlag() == null){
            return false;
        }
        return organizationMapper.cancelOrg(organization.getCode(),organization.getFlag());
    }

    @Override
    public List<Organization> getSubOrg(OrgRequest request) {
        return organizationMapper.getSubOrg(request.getOrgflag());
    }
}
