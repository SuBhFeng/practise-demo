package com.patsnap.swaggerdemo.controller;

import com.patsnap.swaggerdemo.domain.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/swagger")
public class SwaggerController {

    @ApiOperation("hello接口")
    @GetMapping("/hello")
    public User hello(){
        User user = new User();
        user.setName("sufeng");
        user.setAge(11);
        return user;
    }
}
