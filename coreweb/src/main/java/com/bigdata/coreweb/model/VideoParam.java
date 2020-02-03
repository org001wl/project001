package com.bigdata.coreweb.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class VideoParam implements Serializable {
    private String[] moduleIds;
    private String code;
    private String name;
    private Integer status;
    private String[] ids;
}
