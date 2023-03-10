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

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.secrity.SecrityProjectApplication;
import com.secrity.dataset.Result;
import com.secrity.dataset.UserDetail;
import com.secrity.entity.SysUser;
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
        
      //前后端不分离
        //log.debug("进入login方法");
      // 1 创建 UsernamePasswordAuthenticationToken 这里的 loginAccount 就是 username
      UsernamePasswordAuthenticationToken usernameAuthentication = new UsernamePasswordAuthenticationToken(userName, pass);
      // 2 认证
      Authentication authentication = this.authenticationManager.authenticate(usernameAuthentication);
      //判断认证是否通过  通过：authenticate!=null 不通过：authenticate=null
      if(Objects.isNull(authentication)) {
          throw   new RuntimeException("认证失败！");
      }
      // 3 保存认证信息：我认为不用保存，permitAll()虽然走过滤器链，但过滤器不会对登录接口验证，也不会从ContextHolder获取用户信息
      //SecurityContextHolder.getContext().setAuthentication(authentication);
//      // 4 生成自定义token
      
      UserDetail user = (UserDetail) authentication.getPrincipal();
      String token = JwtUtils.getToken(user.getSysUser().getUserId());
//      // 5 放入缓存 把 UserDetails 对象放入缓存中，方便后面过滤器使用。
//      caffeineCache.put(CacheName.USER, userDetail.getUsername(), userDetail);
        
        
        /*
         * 前后端分离
                     * 认证流程
         * 
         * 1:AuthenticationManager调用authenticate()对authentication进行验证
         * 2：最终会调用到自定义的UserDetailsService返回userdetail
         * 3：调用PasswordEncoder比对authentication中的密码与userdetail中的密码是否一样
         */       
        
//        //调用AuthenticationManager的authenticate() 进行认证
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, pass);
//        Authentication authenticate = authenticationManager.authenticate(authentication);
//        //判断认证是否通过  通过：authenticate!=null 不通过：authenticate=null
//        if(Objects.isNull(authenticate)) {
//            throw   new RuntimeException("认证失败！");
//        }
//        //认证成功，使用userId生成token并返回
//        UserDetail user = (UserDetail) authenticate.getPrincipal();
//        String token = JwtUtils.getToken(user.getSysUser().getUserId());
//        //用户信息存入redis  userid:user
//         验证token时，验证成功，再将用户新存入SecurityContextHolder
        return  Result.success().data(token);
    }

    public Result logout() {
        //:根据token能否从redis获取用户信息来决定token是否有效
        //1：用户访问退出接口时携带token，经过过滤器链，将用户信息存入hoder中，将hoder中的用户从redis删除
        //2：用户携带该token再次访问无法从redis中获取到用户，filter抛出异常，访问被拒绝，该token失效
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser = (SysUser) authentication.getPrincipal();
        return null;
    }
}
