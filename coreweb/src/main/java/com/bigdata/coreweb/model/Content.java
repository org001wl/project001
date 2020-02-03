package com.bigdata.coreweb.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;


@Data
public class Content implements Serializable {
    private String keyValue;
    private HashMap dictionaryContent;
}
