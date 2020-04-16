package com.spring.development.module.organization.mapper;

import com.spring.development.module.organization.entity.response.CountResponse;
import com.spring.development.module.organization.entity.response.OrgResponse;
import com.spring.development.module.organization.entity.Organization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.development.module.organization.entity.response.TargetUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-11
 */
@Repository
public interface OrganizationMapper extends BaseMapper<Organization> {
    List<Organization> getOrg(@Param("id") Long id, @Param("code") String code,
                              @Param("orgflag") String orgflag,@Param("name") String name,
                              @Param("classify") Integer classify, @Param("type") Integer type,
                              @Param("host") Integer host, @Param("relation") Integer relation,
                              @Param("phone") String phone,@Param("responser") String responser,
                              @Param("officer") String officer,@Param("supervising") String supervising,
                              @Param("flag") Integer flag,
                              @Param("current") Long current, @Param("size") Long size);

    Organization getOrgByName(@Param("name") String name);

    boolean cancelOrg(@Param("code") String code,@Param("flag") Integer flag);

    List<OrgResponse> getSubOrg(@Param("orgflag") String orgflag);

    OrgResponse getOrgInfoByUid(@Param("id") Long id);

    List<TargetUser> getOrgCodeAndUsersByName(@Param("name") String name);

    String getOrgIntroductionByCode(@Param("code") String code);

    CountResponse countClassify(@Param("classify") Integer classify,@Param("flag") Integer flag,@Param("begin") Timestamp begin, @Param("end") Timestamp end);
    CountResponse countType(@Param("type") Integer type,@Param("flag") Integer flag,@Param("begin") Timestamp begin, @Param("end") Timestamp end);
    CountResponse countHost(@Param("host") Integer host,@Param("flag") Integer flag,@Param("begin") Timestamp begin, @Param("end") Timestamp end);
    CountResponse countRelation(@Param("relation") Integer relation,@Param("flag") Integer flag,@Param("begin") Timestamp begin, @Param("end") Timestamp end);
    CountResponse countTotal(@Param("flag") Integer flag,@Param("begin") Timestamp begin, @Param("end") Timestamp end);

    List<Organization> getPeerAndSubOrgListByOrgFlag(@Param("orgflag") String orgFlag);
}
