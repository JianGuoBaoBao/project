package com.nut.dao;

import com.nut.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    int deleteByMobile(String mobile);

    List<UserInfo> getUserInfoByBaseMobile(String baseMobile);
}

