package com.bigdata.coreweb.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginUser {

    @NotBlank(message = "名称非空")
    private String name;
    @NotBlank(message = "密码非空")
    private String password;
}
