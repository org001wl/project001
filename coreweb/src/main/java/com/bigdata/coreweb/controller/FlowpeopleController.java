package com.bigdata.coreweb.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.coreweb.common.ResultInfo;
import com.bigdata.coreweb.constant.ResultStatus;
import com.bigdata.coreweb.entity.Flowpeople;
import com.bigdata.coreweb.entity.User;
import com.bigdata.coreweb.exception.ContentException;
import com.bigdata.coreweb.model.FlowPeopleParam;
import com.bigdata.coreweb.model.LoginInfo;
import com.bigdata.coreweb.service.IFlowpeopleService;
import com.bigdata.coreweb.service.IUserService;
import com.bigdata.coreweb.util.CopyUtils;
import com.bigdata.coreweb.util.DateTimeUtil;
import com.bigdata.coreweb.util.RedisUtil;
import com.bigdata.coreweb.util.ResultInfoUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;

/**
 * <p>
 * 流动人员登记表 前端控制器
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
@RestController
@RequestMapping("/flowpeople")
public class FlowpeopleController {

    @Autowired
    private IFlowpeopleService flowpeopleService;

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * h5获取用户捎点信息
     *
     * @param token
     * @return
     * @throws ContentException
     */
    @GetMapping("/userCheckAddress")
    public ResultInfo userinfo(@RequestHeader String token) throws ContentException {
//        flowpeople.setId(UUIDUtil.uuid());
//        Object token1 = redisUtil.get("token");
        LoginInfo code = getCode(token);
        String userId = code.getUserId();
//        String districtCode = code.getDistrictCode();
        User user = userService.getById(code.getUserId());
        user.setPassword(null);
        user.setSalt(null);
        return ResultInfoUtil.success(user.getAddress());
    }

    /**
     * 给用户设置捎点地址信息
     *
     * @param token
     * @return
     * @throws ContentException
     */
    @PostMapping("/userBindCheckAddress")
    public ResultInfo userBindCheckAddress(@RequestBody Flowpeople flowpeople, @RequestHeader String token) throws ContentException {
//        flowpeople.setId(UUIDUtil.uuid());
//        Object token1 = redisUtil.get("token");
        LoginInfo code = getCode(token);
        User user = userService.getById(code.getUserId());
        if (StrUtil.isNotEmpty(flowpeople.getCheckAddress())) {
            user.setAddress(flowpeople.getCheckAddress());
            userService.updateById(user);
        }
        return ResultInfoUtil.success(user);
    }

    /**
     * 添加数据
     *
     * @param flowpeople
     * @return
     */
    @PostMapping("/add")
    public ResultInfo add(@RequestBody Flowpeople flowpeople, @RequestHeader String token) throws ContentException {
        LoginInfo user = getCode(token);
//        User user1 = userService.getById(user.getUserId());
//        flowpeople.setCheckAddress(user1.getAddress());
        flowpeople.setAccountId(user.getUserId());
        flowpeople.setOrganizationId(user.getDistrictCode());
        flowpeople.setCreateTime(DateTimeUtil.nowLong());
        flowpeople.setUpdateTime(DateTimeUtil.nowLong());
        flowpeople.setCheckTime(DateTimeUtil.nowLong());
        boolean save = flowpeopleService.save(flowpeople);
        return ResultInfoUtil.success(flowpeople);
    }

    /**
     * h5分页条件查询
     *
     * @param flowPeopleParam
     * @param page
     * @return
     */
    @GetMapping("/list")
    public ResultInfo list(FlowPeopleParam flowPeopleParam, @RequestHeader String token, Page page) throws ContentException {
//        flowpeople.setId(UUIDUtil.uuid());
        LoginInfo user = getCode(token);
        flowPeopleParam.setAccountId(user.getUserId());
        LambdaQueryChainWrapper<Flowpeople> flowpeopleLambdaQueryChainWrapper = flowpeopleService.lambdaQuery();
        flowPeopleParam.buildQuery(flowpeopleLambdaQueryChainWrapper);
        Page<Flowpeople> page1 = flowpeopleLambdaQueryChainWrapper.page(page);
        return ResultInfoUtil.success(page1);
    }

    /**
     * 后台分页条件查询
     *
     * @param flowPeopleParam
     * @param page
     * @return
     */
    @GetMapping("/listback")
    public ResultInfo listback(FlowPeopleParam flowPeopleParam, Page page) {
//        flowpeople.setId(UUIDUtil.uuid());
        LambdaQueryChainWrapper<Flowpeople> flowpeopleLambdaQueryChainWrapper = flowpeopleService.lambdaQuery();
        flowPeopleParam.buildQuery(flowpeopleLambdaQueryChainWrapper);
        Page<Flowpeople> page1 = flowpeopleLambdaQueryChainWrapper.page(page);
        return ResultInfoUtil.success(page1);
    }

    /**
     * 查询单个接口
     *
     * @param flowPeopleParam
     * @param page
     * @return
     */
    @GetMapping("/findById")
    public ResultInfo findById(String id) {
        Flowpeople byId = flowpeopleService.getById(id);
        return ResultInfoUtil.success(byId);
    }

    /**
     * 修改记录
     *
     * @param flowPeopleParam
     * @param page
     * @return
     */
    @PostMapping("/update")
    public ResultInfo update(@RequestBody Flowpeople flowpeople, @RequestHeader String token) throws ContentException {
        LoginInfo user = getCode(token);
        flowpeople.setAccountId(user.getUserId());
        flowpeople.setOrganizationId(user.getDistrictCode());
        User user1 = userService.getById(user.getUserId());
        flowpeople.setCheckAddress(user1.getAddress());
        Flowpeople before = flowpeopleService.getById(flowpeople.getId());
        before.setCheckTime(DateTimeUtil.nowLong());
        before.setUpdateTime(DateTimeUtil.nowLong());
        CopyUtils.copyProperties(flowpeople, before);
        flowpeopleService.updateById(before);
        return ResultInfoUtil.success(before);
    }

    /**
     * 批量删除
     *
     * @param flowPeopleParam
     * @param page
     * @return
     */
    @PostMapping("/delete")
    public ResultInfo delete(@RequestBody String[] ids) {
        flowpeopleService.removeByIds(Arrays.asList(ids));
        return ResultInfoUtil.success();
    }

    private LoginInfo getCode(String token) throws ContentException {
        Object obj = redisUtil.get(token);
        if (obj == null) {
            throw new ContentException(ResultStatus.TOKEN_IS_VVALID);
        }
        LoginInfo user = (LoginInfo) obj;
        return user;
    }


}
