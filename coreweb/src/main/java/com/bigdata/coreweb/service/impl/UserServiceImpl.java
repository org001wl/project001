package com.bigdata.coreweb.service.impl;

import com.bigdata.coreweb.entity.User;
import com.bigdata.coreweb.mapper.UserMapper;
import com.bigdata.coreweb.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author root
 * @since 2020-02-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
