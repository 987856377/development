package com.spring.development.module.message.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.module.message.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
 * @since 2019-11-13
 */
@Repository
public interface MessageMapper extends BaseMapper<Message> {

    Page<Message> getMessageList(Page<Message> page, @Param("sender") Long sender, @Param("receiver") Long receiver, @Param("sendFlag") Integer sendFlag);
    Message getMessage(@Param("id") Long id, @Param("sender") Long sender, @Param("receiver") Long receiver, @Param("sendFlag") Integer sendFlag);
    boolean isRead(@Param("id") Long id, @Param("readTime") Timestamp readTime, @Param("readFlag") Integer readFlag);

    Integer getUnReadCount(@Param("receiver") Long receiver);

    Integer sendDraftMessage(@Param("id") Long id);

    List<String> getUserMail();
}
