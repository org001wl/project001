package com.bigdata.coreweb.exception;

import com.bigdata.coreweb.constant.ResultStatus;

public class ContentException extends Exception {
    private ResultStatus resultStatus;

    public ContentException(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }
}
