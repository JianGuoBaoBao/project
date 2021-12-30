package com.nut.web;


import com.nut.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "测试value",tags ="测试tags")
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/del")
    @ApiOperation("根据id查询用户的接口")
    public int delUserByMobile(String mobile){

        return userInfoService.deleteByMobile(mobile);
    }
}
