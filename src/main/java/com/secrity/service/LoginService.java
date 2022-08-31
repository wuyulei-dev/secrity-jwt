/*
 * @(#)LoginService.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2022年8月31日 wwp-pc          初版
 *
 */
package com.secrity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.secrity.dataset.Result;
import com.secrity.dataset.UserDetail;
import com.secrity.util.JwtUtils;

@Service
public class LoginService {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    /**
     * 登录认证
     * @param userName
     * @param pass
     * @return
     */
    public Result login(String userName,String pass) {
        //调用secrity认证流程
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, pass);
        Authentication authenticate = authenticationManager.authenticate(authentication);
        //使用userId生成token并返回
        UserDetail user = (UserDetail) authenticate.getPrincipal();
        String token = JwtUtils.getToken(user.getSysUser().getUserId());
        //用户信息存入redis  userid:user
        return  Result.success().data(token);
    }
}
