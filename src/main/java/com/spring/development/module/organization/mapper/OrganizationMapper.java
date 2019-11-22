package com.spring.development.module.organization.mapper;

import com.spring.development.module.organization.entity.Organization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-11-11
 */
public interface OrganizationMapper extends BaseMapper<Organization> {
    List<Organization> getOrg(@RequestParam("id") Long id, @RequestParam("code") String code,
                              @RequestParam("orgflag") String orgflag,@RequestParam("name") String name,
                              @RequestParam("classify") Integer classify, @RequestParam("type") Integer type,
                              @RequestParam("host") Integer host, @RequestParam("relation") Integer relation,
                              @RequestParam("phone") String phone,@RequestParam("responser") String responser,
                              @RequestParam("officer") String officer,@RequestParam("supervising") String supervising,
                              @RequestParam("flag") Integer flag,
                              @RequestParam("current") Long current, @RequestParam("size") Integer size);

    Organization getOrgByName(@RequestParam("name") String name);

    boolean cancelOrg(@RequestParam("code") String code,@RequestParam("flag") Integer flag);

    List<Organization> getSubOrg(@RequestParam("orgflag") String orgflag);
}
