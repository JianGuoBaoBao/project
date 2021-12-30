package com.nut.service.impl;

import com.nut.dao.UserInfoMapper;
import com.nut.entity.UserInfo;
import com.nut.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public int insert(UserInfo record) {

        return userInfoMapper.insert(record);
    }

    @Override
    public int deleteByMobile(String mobile) {
        return userInfoMapper.deleteByMobile(mobile);
    }
}
