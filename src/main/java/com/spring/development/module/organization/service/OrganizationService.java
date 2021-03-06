package com.spring.development.module.organization.service;

import com.spring.development.module.organization.entity.request.CountRequest;
import com.spring.development.module.organization.entity.response.CountResponse;
import com.spring.development.module.organization.entity.response.OrgResponse;
import com.spring.development.module.organization.entity.Organization;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.development.module.organization.entity.request.OrgRequest;
import com.spring.development.module.organization.entity.response.OrgUserResponse;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-11
 */
public interface OrganizationService extends IService<Organization> {
    List<Organization> getOrg(OrgRequest request);
    Organization getOrgByName(String name);
    boolean cancelOrg(Organization organization);
    List<OrgResponse> getSubOrg(OrgRequest request);

    OrgResponse getOrgInfoByUid(OrgRequest request);

    OrgUserResponse getOrgCodeAndUsersByName(OrgRequest request);

    String getOrgIntroductionByCode(OrgRequest request);

    List<CountResponse> countOrganization(CountRequest request);

    List<Organization> getPeerAndSubOrgListByOrgFlag(OrgRequest request);

    Organization getOrgByNameFromMaster(String name);

    Organization getOrgByNameFromSlave(String name);
}
