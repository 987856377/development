package com.spring.development.module.permission.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-12-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Module extends Model<Module> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    private String label;

    /**
     * 父菜单id
     */
    private Integer pid;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 菜单层级
     */
    private String level;

    /**
     * 启用标志
     */
    private Integer flag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
