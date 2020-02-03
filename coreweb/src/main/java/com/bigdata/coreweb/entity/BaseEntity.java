package com.bigdata.coreweb.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 实体通用字段
 */
@Data
public class BaseEntity {
    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 更新时间
     */
    private Long updateTime;
    /**
     * 创建时间
     */
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Long createTime;
}
