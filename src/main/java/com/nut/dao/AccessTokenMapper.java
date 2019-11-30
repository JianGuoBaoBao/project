package com.nut.dao;

import com.nut.entity.AccessToken;

public interface AccessTokenMapper {
    int insert(AccessToken record);

    int insertSelective(AccessToken record);
}