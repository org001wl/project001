package com.bigdata.coreweb.model;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.bigdata.coreweb.entity.Flowpeople;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class FlowPeopleParam implements Serializable {
    private String startDate;
    private String endDate;
    private String name;
    private Boolean goWuhan;
    private Boolean goHubei;
    private Boolean isFever;
    private String telephone;
    private String accountId;
    private String organizationId;

    /**
     * 检查地点
     */
    private String checkAddress;

    /**
     * 检查人员
     */
    private String checkPeople;

    /**
     * 车牌
     */
    private String carNumber;

//    /**
//     * 检查时间
//     */
//    private long checkTime;

    public void buildQuery(LambdaQueryChainWrapper<Flowpeople> query) {
        if (StrUtil.isNotEmpty(startDate)) {
            query.ge(Flowpeople::getCheckTime, this.getDataLong(startDate));
        }
        if (StrUtil.isNotEmpty(endDate)) {
            query.le(Flowpeople::getCheckTime, this.getDataLong(endDate));
        }
        if (isFever != null) {
            query.eq(Flowpeople::getIsFever, isFever);
        }
        if (goWuhan != null) {
            query.eq(Flowpeople::getGoWuhan, goWuhan);
        }
        if (goHubei != null) {
            query.eq(Flowpeople::getGoHubei, goHubei);
        }
        if (StrUtil.isNotEmpty(checkAddress)) {
            query.eq(Flowpeople::getCheckAddress, checkAddress);
        }
        if (StrUtil.isNotEmpty(carNumber)) {
            query.eq(Flowpeople::getCarNumber, carNumber);
        }
        if (StrUtil.isNotEmpty(name)) {
            query.eq(Flowpeople::getName, name);
        }
        if (StrUtil.isNotEmpty(telephone)) {
            query.eq(Flowpeople::getTelephone, telephone);
        }
        if (StrUtil.isNotEmpty(organizationId)) {
            query.likeRight(Flowpeople::getOrganizationId, organizationId);
        }
        if (StrUtil.isNotEmpty(accountId)) {
            query.eq(Flowpeople::getAccountId, accountId);
        }
        if (StrUtil.isNotEmpty(checkPeople)) {
            query.eq(Flowpeople::getCheckPeople, checkPeople);
        }
        query.orderByDesc(Flowpeople::getCheckTime);
        query.orderByAsc(Flowpeople::getCheckPeople);
    }

    private Long getDataLong(String dateStr) {
//        dateStr = "2020-02-01";
        DateTime date = DateUtil.parse(dateStr, "yyyy-MM-dd");
        return date.getTime();

    }
}
