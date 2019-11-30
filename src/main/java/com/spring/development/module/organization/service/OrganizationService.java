package com.spring.development.module.organization.service;

import com.spring.development.module.organization.entity.response.OrgResponse;
import com.spring.development.module.organization.entity.Organization;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.development.module.organization.entity.request.OrgRequest;

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
}
