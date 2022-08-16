package com.nut.service;

import com.nut.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    void insert(UserInfo record);

    List<UserInfo> getUserInfoByBaseMobile(String baseMobile);

    int deleteByMobile(String mobile);
}
