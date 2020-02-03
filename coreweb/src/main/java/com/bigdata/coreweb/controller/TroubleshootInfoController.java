package com.bigdata.coreweb.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.coreweb.common.ResultInfo;
import com.bigdata.coreweb.constant.ResultStatus;
import com.bigdata.coreweb.entity.TroubleshootInfo;
import com.bigdata.coreweb.exception.ContentException;
import com.bigdata.coreweb.model.LoginInfo;
import com.bigdata.coreweb.model.TroubleshootInfoParam;
import com.bigdata.coreweb.service.ITroubleshootInfoService;
import com.bigdata.coreweb.util.DateTimeUtil;
import com.bigdata.coreweb.util.RedisUtil;
import com.bigdata.coreweb.util.ResultInfoUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 *  排查人员信息
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
@RestController
@RequestMapping("/troubleshootInfo")
public class TroubleshootInfoController {

    @Autowired
    private ITroubleshootInfoService troubleshootInfoService;
    @Autowired
    private RedisUtil redisUtil;
    @ApiOperation("添加排查人员信息")
    @PostMapping("/save")
    public ResultInfo save(@RequestBody TroubleshootInfo troubleshootInfo, @RequestHeader String token) throws ContentException {
        //获取当前用户
        LoginInfo user = userInfo(token);
        troubleshootInfo.setCreator(user.getName());
        troubleshootInfo.setTime(new Date());
        troubleshootInfo.setCreateTime(DateTimeUtil.nowLong());
        troubleshootInfo.setAuthCode(user.getDistrictCode());
        troubleshootInfoService.save(troubleshootInfo);
        return ResultInfoUtil.success();
    }

    @ApiOperation("查询排查人员信息")
    @GetMapping("/list")
    public ResultInfo list(Page page, TroubleshootInfoParam param, @RequestHeader String token) throws ContentException {
        //获取当前用户
        LoginInfo user = userInfo(token);
        param.setAuthCode(user.getDistrictCode());
        Page data = troubleshootInfoService.list(param,page);
        return ResultInfoUtil.success(data);
    }
    @ApiOperation("查询排查人员信息")
    @GetMapping("/listTest")
    public ResultInfo listTest(Page page, TroubleshootInfoParam param) throws ContentException {
        //获取当前用户

        param.setAuthCode("100");
        Page data = troubleshootInfoService.list(param,page);
        return ResultInfoUtil.success(data);
    }

    private LoginInfo userInfo(String token) throws ContentException {
        Object obj = redisUtil.get(token);
        if (obj == null) {
            throw new ContentException(ResultStatus.TOKEN_IS_VVALID);
        }
        LoginInfo user = (LoginInfo)obj;
        return user;
    }
}
