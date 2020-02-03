package com.bigdata.coreweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigdata.coreweb.common.ResultInfo;
import com.bigdata.coreweb.common.SystemException;
import com.bigdata.coreweb.constant.ResultStatus;
import com.bigdata.coreweb.entity.User;
import com.bigdata.coreweb.service.IUserService;
import com.bigdata.coreweb.util.MD5Util;
import com.bigdata.coreweb.util.ResultInfoUtil;
import com.bigdata.coreweb.util.StringUtil;
import com.bigdata.coreweb.vo.UserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @ApiOperation("用户添加")
    @PostMapping("/add")
    public ResultInfo add(User user) throws SystemException {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", user.getName());
        List<User> userList = userService.list(queryWrapper);
        if (userList.size() > 0) {
            throw new SystemException(ResultStatus.USERNAME_REPEAT);
        } else {
            String salt = UUID.randomUUID().toString();
            String md5Password = MD5Util.getMd5(MD5Util.linkSalt(user.getPassword(), salt));
            user.setPassword(md5Password);
            user.setSalt(salt);
            user.setCreateTime(System.currentTimeMillis());
            user.setUpdateTime(System.currentTimeMillis());
            userService.save(user);
        }
        return ResultInfoUtil.success();
    }

    @ApiOperation("用户修改")
    @PostMapping("/update")
    public ResultInfo update(User user) throws SystemException {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", user.getName());
        queryWrapper.ne("id", user.getId());
        List<User> userList = userService.list(queryWrapper);
        if (userList.size() > 0) {
            throw new SystemException(ResultStatus.USERNAME_REPEAT);
        } else {
            String salt = UUID.randomUUID().toString();
            String md5Password = MD5Util.getMd5(MD5Util.linkSalt(user.getPassword(), salt));
            user.setPassword(md5Password);
            user.setSalt(salt);
            user.setUpdateTime(System.currentTimeMillis());
            userService.updateById(user);
        }
        return ResultInfoUtil.success();
    }

    @ApiOperation("用户删除")
    @DeleteMapping("/delete")
    public ResultInfo delete(String[] ids) throws SystemException {
        for (String id : ids) {
            userService.removeById(id);
        }
        return ResultInfoUtil.success();
    }
    /**
     * 按区划分页查询人员
     */
    @ApiOperation("查询顶级区划左边")
    @GetMapping("/findPage")
    public ResultInfo findPage( UserVo user) throws SystemException {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("district_code",user.getDistrictCode());
        if(!StringUtil.isNullOrEmpty(user.getName())) {
            queryWrapper.like("name", user.getName());
        }
        Page page = new Page();
        if (user.getCurrent()!=null){
            page.setCurrent(user.getCurrent());
        }
        if (user.getSize()!=null){
            page.setSize(user.getSize());
        }
        Page page1=userService.page(page,queryWrapper);
        return ResultInfoUtil.success(page1);
    }
}
