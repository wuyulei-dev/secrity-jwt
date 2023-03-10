/*
 * @(#)UserService.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年3月10日 wwp-pc          初版
 *
 */
package com.secrity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.secrity.entity.SysUser;
import com.secrity.mapper.UserMapper;

@Service
public class UserService {

    
    @Autowired
    private UserMapper userMapper;
    
    public SysUser getUserWithId(String userId) {
        LambdaQueryWrapper<SysUser> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserId, userId);
        SysUser sysUser = userMapper.selectOne(queryWrapper);
        return sysUser;
    }
}
