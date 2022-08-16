package com.nut.service.impl;

import com.nut.dao.UserInfoMapper;
import com.nut.entity.UserInfo;
import com.nut.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public void insert(UserInfo record) {

        userInfoMapper.insert(record);
    }

    @Override
    public List<UserInfo> getUserInfoByBaseMobile(String baseMobile) {
        return userInfoMapper.getUserInfoByBaseMobile(baseMobile);
    }

    @Override
    public int deleteByMobile(String mobile) {
        return userInfoMapper.deleteByMobile(mobile);
    }
}
