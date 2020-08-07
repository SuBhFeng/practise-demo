package com.patsnap.swaggerdemo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("User实体类")
public class User {
    @ApiModelProperty("用户名")
    private String name;
    @ApiModelProperty("年龄")
    private int age;

}
