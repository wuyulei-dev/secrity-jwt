/*
 * @(#)TokenFilter.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2022年9月6日 wwp-pc          初版
 *
 */
package com.secrity.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.secrity.entity.SysUser;
import com.secrity.util.JwtUtils;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;

//OncePerRequestFilter: 确保每一次请求只通过一次filter
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("authenticateAction");
        if (StrUtil.isEmpty(token)) {
            //没有token则放行。1：如果是登录请求则能执行登录接口     2：如是未认证请求则会交给最后一个过滤器处理，返回登录页面
            filterChain.doFilter(request, response);
        }
        
        //解析token
        Claims claims = JwtUtils.parseToken(token);
        if(ObjectUtil.isNull(claims)) {
            //解析失败，直接抛出异常，返回登录页面
            throw new RuntimeException("token非法");
        }
        
        //从redis中获取用户信息
        String userId = (String) claims.get("userId");
        SysUser user=null;
        if(ObjectUtil.isNull(user)) {
            //如果获取失败则抛出异常，返回登录页面
            throw new RuntimeException("用户未登录");
        }
        
        //获取成功，将用户信息存入SecurityContextHolder，供其他filter和方法使用
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //放行
        filterChain.doFilter(request, response);
    }

}
