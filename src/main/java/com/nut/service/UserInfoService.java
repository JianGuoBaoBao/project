package com.nut.service;

import com.nut.entity.UserInfo;

public interface UserInfoService {
    int insert(UserInfo record);

    int deleteByMobile(String mobile);
}
