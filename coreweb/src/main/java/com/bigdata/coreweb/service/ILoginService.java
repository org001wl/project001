package com.bigdata.coreweb.service;

import com.bigdata.coreweb.common.SystemException;
import com.bigdata.coreweb.model.LoginInfo;
import com.bigdata.coreweb.vo.LoginUser;

public interface ILoginService {
    LoginInfo login(LoginUser user) throws SystemException;

    void loginOut(String token) throws SystemException;
}
