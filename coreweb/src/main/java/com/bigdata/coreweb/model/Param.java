package com.bigdata.coreweb.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Param implements Serializable {
    private String[] ids;
    private String type;
    private Integer flag;
}
