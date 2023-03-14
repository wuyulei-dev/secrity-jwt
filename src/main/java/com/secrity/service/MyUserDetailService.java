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

import java.util.Arrays;
import java.util.List;
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

//用户请求登录接口时会调用
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
        
        //如果查询不到数据就抛出异常，由全局异常处理
        if(Objects.isNull(sysUser)) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        //封装UserDetails对象并返回.这里不用封装权限信息，因为每次请求filter中会封装权限信息
        List<String> permissions = Arrays.asList("aa","asdfasdf");
        UserDetail userDetail = new UserDetail(sysUser,permissions);
        return userDetail;
    }

}
