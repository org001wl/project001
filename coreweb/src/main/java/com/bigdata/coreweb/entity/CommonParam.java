package com.bigdata.coreweb.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通用参数表
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("common_param")
public class CommonParam implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 参数类型
     */
    @TableField("param_type")
    private String paramType;

    /**
     * 参数值
     */
    @TableField("param_value")
    private String paramValue;

    /**
     * 参数描述
     */
    @TableField("param_desc")
    private String paramDesc;


}
