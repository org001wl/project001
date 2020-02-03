package com.bigdata.coreweb.controller;

import com.bigdata.coreweb.common.ResultInfo;
import com.bigdata.coreweb.common.SystemException;
import com.bigdata.coreweb.constant.ResultStatus;
import com.bigdata.coreweb.util.ResultInfoUtil;
import com.bigdata.coreweb.model.LoginInfo;
import com.bigdata.coreweb.service.ILoginService;
import com.bigdata.coreweb.vo.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "登录退出", description = "登录退出api")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ILoginService loginService;




    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("/in")
    public ResultInfo login(@RequestBody LoginUser user)throws SystemException {
        LoginInfo loginInfo = loginService.login(user);
        if (loginInfo != null) {
            return ResultInfoUtil.success(loginInfo);
        }
        return ResultInfoUtil.code(ResultStatus.EXCEPTION);
    }

   /**
     * 退出登录
     *
     * @param
     * @return
     */
    @ApiOperation("用户退出")
    @GetMapping("/out")
    public ResultInfo logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        loginService.loginOut(token);
        return ResultInfoUtil.success();
    }
}
