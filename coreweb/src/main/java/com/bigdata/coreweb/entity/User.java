package com.bigdata.coreweb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;

    @TableField("salt")
    private String salt;

    @TableField("address")
    private String address;

    @TableField("icon")
    private String icon;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 电话号码
     */
    @TableField("tel_number")
    private String telNumber;

    @TableField("delete_flag")
    private Integer deleteFlag;

    @TableField("locked")
    private Boolean locked;

    /**
     * 区域外键
     */
    @TableField("district_code")
    private String districtCode;


}
