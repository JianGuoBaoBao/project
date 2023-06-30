package com.nut;

import com.nut.entity.UserInfo;
import com.nut.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectApplicationTests {

    @Autowired
    UserInfoService userInfoService;

    @Test
    public void userInfoService() {

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
        userInfo.setUserName("宝宝");
        userInfo.setUserBaseMobile("12306");
        userInfo.setUserPwd("123456");

        userInfoService.insert(userInfo);
    }
    @Test
    public void delByMobile(){
        userInfoService.deleteByMobile("12306");
    }

    @Test
    public void getUserInfoByBaseMobile(){
       List<UserInfo> userInfo = userInfoService.getUserInfoByBaseMobile("12306");
        System.out.println(userInfo);
    }
}
