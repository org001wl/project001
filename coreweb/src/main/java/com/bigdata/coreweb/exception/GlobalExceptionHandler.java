package com.bigdata.coreweb.exception;


import com.bigdata.coreweb.common.ResultInfo;
import com.bigdata.coreweb.common.SystemException;
import com.bigdata.coreweb.constant.ResultStatus;
import com.bigdata.coreweb.util.ResultInfoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 拦截ServiceException异常
     * @param systemException
     * @return
     */
    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public ResultInfo handleServiceException(SystemException systemException) {
        return ResultInfoUtil.code(systemException.getResultStatus());
    }

    /**
     * 拦截Exception异常
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultInfo handleException(Exception exception) {
        LOGGER.error("system exception: {}", exception);
        return ResultInfoUtil.code(ResultStatus.EXCEPTION);
    }
}
