/*
 * @(#)WebSecurityConfig.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2022年6月21日 wwp-pc          初版
 *
 */
package com.secrity.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;
    
    //记住我-数据库配置
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        jdbcTokenRepositoryImpl.setDataSource(dataSource);   //设置数据源
//        jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);  //自动创建表：每次重启服务都会创建表。表已存在的时候删除该配置，不然重启服务时创建表失败报错
        
        return jdbcTokenRepositoryImpl;
    }
    
    //编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(
        WebSecurity web) throws Exception {
        //静态资源配置，不经secrity过滤器，不被拦截
        web.ignoring().antMatchers("/static/**");
    }
    
    @Override
    protected void configure(
        HttpSecurity http) throws Exception {
        http.authorizeRequests()    //开始权限配置
                  .antMatchers("/main.html").permitAll()  //main.html页面不需要认证
                  .anyRequest().authenticated()
                  .and()
             .formLogin() //表单登录
                 .loginPage("/login.html")  //登录页，未登录时跳转页面（路径为去掉应用路径的相对路径）
                 .loginProcessingUrl("/user/login")  //登录请求接口（路径为去掉应用路径的相对路径）
                 .permitAll()    //放行，不认证
                 .and()
             .csrf().disable();
        
        //退出登录
        http.logout()
                .logoutUrl("/logout")   //注销的请求路径
                .logoutSuccessUrl("/login.html")  //注销成功后跳转页面
                .permitAll();     //放行
        
        //开启记住我功能
        http.rememberMe()
                .tokenRepository(persistentTokenRepository())  //配置TokenRepository
                .tokenValiditySeconds(60);   //设置有效时长，单位秒
                //.userDetailsService(userDetailsService)   如果有自定义userService,需进行该配置
    }
    
//    @Override
//    protected void configure(
//        AuthenticationManagerBuilder auth) throws Exception {
//        //配置内存用户，必须配置roles(否则启动失败)
//        auth.inMemoryAuthentication().withUser("user").password("123").roles("admin");
//    }

  
    
}
