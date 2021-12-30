package com.nut.web;

import com.nut.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping("/user")
public class UserInfoControlelrTwo {

    @PostMapping("/")
    @ApiOperation("添加用户的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四"),
            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "深圳", required = true)
    })
    public UserInfo addUser(String username, @RequestParam(required = true) String address) {
        return new UserInfo();
    }

    @GetMapping("/")
    @ApiOperation("根据id查询用户的接口")
    @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "99", required = true)
    public UserInfo getUserById(@PathVariable String id) {
        UserInfo user = new UserInfo();
        user.setUserId(id);
        return user;
    }
    @PutMapping("/{id}")
    @ApiOperation("根据id更新用户的接口")
    public UserInfo updateUserById(@RequestBody UserInfo user) {
        return user;
    }

}
