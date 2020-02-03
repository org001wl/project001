package com.bigdata.coreweb.constant;

/**
 * 结果响应状态
 */
public enum ResultStatus {
    OK(1000, "成功"),
    TOKEN_IS_VVALID(1001, "token无效"),
    USER_NOT_EXIST(101, "用户名不存在"),
    PASSWORD_ERROR(101, "密码不正确"),
    USERNAME_REPEAT(101, "用户名重复"),
    DISTRICT_NAME_REPEAT(102, "区域名称重复"),
    DISTRICT_CODE_REPEAT(102, "区域名称CODE重复"),
    EXCEPTION(201, "系统内部异常");
    private int code;
    private String msg;
    ResultStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
