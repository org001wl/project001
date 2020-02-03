package com.bigdata.coreweb.common;

import com.bigdata.coreweb.constant.ResultStatus;
import lombok.Data;

@Data
public class SystemException extends RuntimeException{

    private  ResultStatus resultStatus;
    public SystemException(String msg) {
        super(msg);
    }
    public SystemException(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }
}
