package com.spring.development.module.organization.service.impl;

import com.spring.development.module.organization.entity.request.CountRequest;
import com.spring.development.module.organization.entity.response.CountResponse;
import com.spring.development.module.organization.entity.response.OrgResponse;
import com.spring.development.module.organization.entity.Organization;
import com.spring.development.module.organization.entity.request.OrgRequest;
import com.spring.development.module.organization.entity.response.OrgUserResponse;
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
                request.getFlag(),request.getPage().getCurrent(),request.getPage().getSize());
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
    public List<OrgResponse> getSubOrg(OrgRequest request) {
        if (request.getOrgflag() == null){
            return null;
        }
        return organizationMapper.getSubOrg(request.getOrgflag());
    }

    @Override
    public OrgResponse getOrgInfoByUid(OrgRequest request) {
        if (request.getId() == null){
            return null;
        }
        OrgResponse response = organizationMapper.getOrgInfoByUid(request.getId());
        response.setSubOrgList(organizationMapper.getSubOrg(response.getOrgflag()));
        return response;
    }

    @Override
    public OrgUserResponse getOrgCodeAndUsersByName(OrgRequest request) {
        if (request.getName() == null){
            return null;
        }
        OrgUserResponse response = new OrgUserResponse();
        response.setCode(organizationMapper.getOrgByName(request.getName()).getCode());
        response.setTargetUserList(organizationMapper.getOrgCodeAndUsersByName(request.getName()));
        return response;
    }

    @Override
    public String getOrgIntroductionByCode(OrgRequest request) {
        if (request.getCode() == null){
            return null;
        }
        return organizationMapper.getOrgIntroductionByCode(request.getCode());
    }

    @Override
    public List<CountResponse> count(CountRequest request) {
        return organizationMapper.count(request.getClassify(),request.getType(),request.getHost(),request.getRelation());
    }
}
