package com.bigdata.coreweb.common;
import lombok.Data;

@Data
public class ResultInfo<T> {
    private T data;
    private int code;
    private String message;

    public ResultInfo(int status) {
        this.code = status;
    }

    public ResultInfo(int status, T data) {
        this.code = status;
        this.data = data;
    }

    public ResultInfo(int status, String message) {
        this.code = status;
        this.message = message;
    }
}
