package com.bigdata.coreweb.model;

import com.bigdata.coreweb.entity.District;
import com.bigdata.coreweb.entity.User;
import lombok.Data;

@Data
public class LoginInfo {
    private  String userId;
    private String name;

    private String address;


    private String telNumber;
    /**
     * 区域code
     */
    private String districtCode;
    /*
    区域名称
     */
    private String districtName;
    private String token;
}
