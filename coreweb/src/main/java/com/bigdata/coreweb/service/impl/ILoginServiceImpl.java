package com.bigdata.coreweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigdata.coreweb.common.SystemException;
import com.bigdata.coreweb.constant.ResultStatus;
import com.bigdata.coreweb.entity.District;
import com.bigdata.coreweb.entity.User;
import com.bigdata.coreweb.mapper.DistrictMapper;
import com.bigdata.coreweb.model.LoginInfo;
import com.bigdata.coreweb.service.IDistrictService;
import com.bigdata.coreweb.service.ILoginService;
import com.bigdata.coreweb.service.IUserService;
import com.bigdata.coreweb.util.MD5Util;
import com.bigdata.coreweb.util.RedisUtil;
import com.bigdata.coreweb.util.StringUtil;
import com.bigdata.coreweb.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
public class ILoginServiceImpl  implements ILoginService {
    @Autowired
    private IUserService userService;
    @Autowired
    private IDistrictService districtService;

    @Autowired
    private RedisUtil redisUtil;
    @Override
    public LoginInfo login(LoginUser loginUser) throws SystemException {
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",loginUser.getName());
        District district =null;
         User user=userService.getOne(queryWrapper);
        LoginInfo loginInfo=new LoginInfo();
         if(user!=null){
             if (MD5Util.testPasswordOk(loginUser.getPassword(), user.getSalt(), user.getPassword())) {
                 String token = UUID.randomUUID().toString();
                 if (!StringUtil.isNullOrEmpty(user.getDistrictCode())){
                     QueryWrapper queryWrapper1=new QueryWrapper<>();
                     queryWrapper1.eq("code",user.getDistrictCode());
                     district= districtService.getOne(queryWrapper1);
                 }
                 loginInfo.setUserId(user.getId());
                 loginInfo.setName(user.getName());
                 loginInfo.setAddress(user.getAddress());
                 loginInfo.setTelNumber(user.getTelNumber());
                 loginInfo.setDistrictCode(user.getDistrictCode());
                 loginInfo.setDistrictName(district.getName());
                 loginInfo.setToken(token);
                 redisUtil.set(token, loginInfo, 3000);
                 }else {
                throw  new SystemException(ResultStatus.PASSWORD_ERROR);
             }

         }else {
             throw new SystemException(ResultStatus.USER_NOT_EXIST);
         }
        return loginInfo;
    }

    @Override
    public void loginOut(String token) throws SystemException {
        redisUtil.del(token);
    }
}
