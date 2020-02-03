package com.bigdata.coreweb.util;

import com.bigdata.coreweb.common.ResultInfo;
import com.bigdata.coreweb.constant.ResultStatus;

/**
 * 结果响应帮助类
 */
public class ResultInfoUtil {

    public static ResultInfo success() {
        return new ResultInfo(ResultStatus.OK.getCode());
    }

    public static ResultInfo success(Object data) {
        return new ResultInfo(ResultStatus.OK.getCode(), data);
    }

    public static ResultInfo code(int code) {
        return new ResultInfo(code);
    }

    public static ResultInfo code(ResultStatus result) {
        return new ResultInfo(result.getCode(), result.getMsg());
    }
}
