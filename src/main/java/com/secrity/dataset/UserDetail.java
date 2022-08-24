/*
 * @(#)UserDetail.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2022年8月22日 wwp-pc          初版
 *
 */
package com.secrity.dataset;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.secrity.entity.SysUser;

public class UserDetail implements UserDetails{

    //包装实体类,来获取数据库查询到的用户信息
    private SysUser sysUser;

    public UserDetail(SysUser sysUser) {
        this.sysUser=sysUser;
    }

    //权限集合
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    //获取用户密码
    @Override
    public String getPassword() {
        return sysUser.getPasswd();
    }

    //获取用户名
    @Override
    public String getUsername() {
        return sysUser.getLoginName();
    }

    //是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //是否被锁
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    //是否可用
    @Override
    public boolean isEnabled() {
        return false;
    }

}
