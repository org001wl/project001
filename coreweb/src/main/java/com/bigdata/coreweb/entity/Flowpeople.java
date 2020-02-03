package com.bigdata.coreweb.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 流动人员登记表
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bat_flowpeople")
public class Flowpeople implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableField("update_Time")
    private long updateTime;

    @TableField("create_Time")
    private long createTime;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 身份证
     */
    @TableField("idcard")
    private String idcard;
    /**
     * 手机号
     */
    @TableField("telephone")
    private String telephone;

    /**
     * 车牌
     */
    @TableField("car_number")
    private String carNumber;

    /**
     * 检查时间
     */
    @TableField("check_time")
    private long checkTime;

    /**
     * 来源地点
     */
    @TableField("source_address")
    private String sourceAddress;

    /**
     * 目的地址
     */
    @TableField("target_address")
    private String targetAddress;

    /**
     * 是否去过武汉
     */
    @TableField("go_wuhan")
    private Boolean goWuhan;

    /**
     * 是否去过湖北
     */
    @TableField("go_hubei")
    private Boolean goHubei;

    /**
     * 是否发烧
     */
    @TableField("is_fever")
    private Boolean isFever;

    /**
     * 检查地点
     */
    @TableField("check_address")
    private String checkAddress;

    /**
     * 检查人员
     */
    @TableField("check_people")
    private String checkPeople;

    /**
     * 备注
     */
    @TableField("description")
    private String description;

    /**
     * 其他
     */
    @TableField("other")
    private String other;


    /**
     * 录入数据账号
     */
    @TableField("account_id")
    private String accountId;

    /**
     * 录入数据账号所属组织
     */
    @TableField("organization_id")
    private String organizationId;
}
