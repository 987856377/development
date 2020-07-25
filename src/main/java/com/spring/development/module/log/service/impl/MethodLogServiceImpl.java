package com.spring.development.module.log.service.impl;

import com.spring.development.module.log.entity.MethodLog;
import com.spring.development.module.log.mapper.MethodLogMapper;
import com.spring.development.module.log.service.MethodLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-07-25
 */
@Service
public class MethodLogServiceImpl extends ServiceImpl<MethodLogMapper, MethodLog> implements MethodLogService {

}
