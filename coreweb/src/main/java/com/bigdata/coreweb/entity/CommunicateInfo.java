package com.bigdata.coreweb.entity;

import java.io.Serializable;
import java.sql.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通信情况表
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sjhm")
public class CommunicateInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId("id")
    private Long id;
    
    /**
     * 手机号码
     */
    @TableField("sj")
    private Long mobiePhone;

    /**
     * 所属运营商
     */
    @TableField("yys")
    private String belongCompany;

    /**
     * 号码归属地
     */
    @TableField("gsd")
    private String belongZone;
    
    /**
     * 用户类型（武汉返回还是湖北省内其他城市返回）
     */
    @TableField("lx")
    private String type;

    /**
     * 确认时间
     */
    @TableField("date")
    private Date confirmTime;

    /**
     * 到访地区行政编号
     */
    //@TableField("visit_zone_code")
    //private String visitZoneCode;

    /**
     * 到访城市
     */
    @TableField("dfds")
    private String visitZone;

    /**
     * 到访区县
     */
    @TableField("dfxq")
    private String location;

    /**
     * 基站标识
     */
    @TableField("jzbs")
    private String stationId;

    /**
     * 基站名称
     */
    @TableField("jzmc")
    private String stationName;
    
    /**
     * 离开湖北或者武汉的时间（联通有，电信、移动数据无）
     */
    @TableField("lksj")
    private String stayDate;
    
    /**
     * 在湖北或武汉停留的时间（联通有，电信、移动数据无）
     */
    @TableField("zlsj")
    private String levelDate;
}
