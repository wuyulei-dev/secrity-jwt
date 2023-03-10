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
import java.util.Collection;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.secrity.entity.SysUser;
import com.secrity.service.UserService;
import com.secrity.util.JwtUtils;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
/**
 * token 验证过滤器，需加入secrity过滤器链，需指定过滤器的位置，在FilterSecurityInterceptor前面
 * 
 * @author wwp-pc
 *
 */
//实现filer接口做过滤器：不同的servlet版本中会存在问题，一个请求过来，这个过滤器被执行多次
//OncePerRequestFilter: 确保每一次请求只通过一次filter
// 自定义过滤器不能交给spring IOC管理，否则一切url(包括忽略的url）都会被拦截
//@Component
public class TokenFilter extends OncePerRequestFilter {

    //无法自动注入获取。在secrity配置类中注入容器中的service
    private UserService userService;
    
    public TokenFilter() {
        //调用父类构造器
        super();
    }
    
    public TokenFilter(UserService userService) {
        this.userService=userService;
    }
    
    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("authenticateAction");
        if (StrUtil.isEmpty(token)) {
            //登录接口、其他请求都走过滤器链，如果是需认证资源，未认证会阻止访问
            filterChain.doFilter(request, response);
            
            //过滤器放行后会执行其他过滤器或目标接口，执行完后，会一路返回，继续执行该filter内其他方法，
            //return: 为了不执行下面解析token的方法如果不返回。如果不返回，就会继续执行下面方法
            return;
        }
        
        //解析token
        Claims claims = JwtUtils.parseToken(token);
        if(ObjectUtil.isNull(claims)) {
            //解析失败，直接抛出异常，返回登录页面？怎么处理
            throw new RuntimeException("token非法");
        }
        
        //获取用户信息
        String userId = (String) claims.get("userId");
        SysUser user = userService.getUserWithId(userId);
        if(ObjectUtil.isNull(user)) {
            //没有该用户，则抛出异常，返回登录页面？
            throw new RuntimeException("用户未登录");
        }
        
        //TODO 获取权限信息，封装到token中
        Collection<? extends GrantedAuthority> authorities=null;
        
        //认证成功，将用户信息存入SecurityContextHolder，供后面的filter从SecurityContextHolder中获取用户信息，看是否是已认证
        //必须用三个参数的构造方法，创建的是一个已认证状态的token
        //注意：因为该SecurityContextHolder不是从session中获取的，所以不同请求的SecurityContextHolder可能不是同一个，因为不同请求对应的线程不同
        //线程中的SecurityContextHolder也就不同
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //放行
        filterChain.doFilter(request, response);
    }

}
