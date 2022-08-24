/*
 * @(#)MyUserDetailService.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2022年7月28日 wwp-pc          初版
 *
 */
package com.secrity.service;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.secrity.dataset.UserDetail;
import com.secrity.entity.SysUser;
import com.secrity.mapper.UserMapper;

@Service
public class MyUserDetailService implements UserDetailsService{

    @Autowired
    private UserMapper userMapper;
    
    @Override
    public UserDetails loadUserByUsername(
        String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getLoginName, username);
        SysUser sysUser = userMapper.selectOne(queryWrapper);
        
        //如果查询不到数据就抛出异常，由异常处理过滤器处理
        if(Objects.isNull(sysUser)) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        //封装成UserDetails对象并返回 
        UserDetail userDetail = new UserDetail(sysUser);
        return userDetail;
    }

}
