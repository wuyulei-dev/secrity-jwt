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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import com.secrity.exception.AccessDeniedHandlerImp;
import com.secrity.exception.AuthenticationEntryPointImp;
import com.secrity.filter.TokenFilter;
import com.secrity.mapper.MenuMapper;
import com.secrity.service.UserService;

//开启基于注解的权限控制
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    //给TokenFilter注入容器中的service
    @Autowired
    private UserService userService;
    
    @Autowired
    private MenuMapper menuMapper;
    
    @Autowired
    private AuthenticationEntryPointImp authenticationEntryPointImp;
    
    @Autowired
    private AccessDeniedHandlerImp accessDeniedHandlerImp;
    /**
     * 不需要权限过滤资源定义
     */
    public static final String[] IGNORING_RESOURCES = {
             "/static/**" //静态资源
    };
    
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

    //@Bean：将AuthenticationManager 放入spring容器
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(
        WebSecurity web) throws Exception {
        //静态资源配置，不经secrity过滤器，不被拦截
        web.ignoring().antMatchers("/js/**");
    }
    
   

    @Override
    protected void configure(
        HttpSecurity http) throws Exception {
        /**
         * 1 anyRequest():表示匹配所有的请求。
                                                                设置全部内容都需要进行认证。.anyRequest().authenticated();
           2 antMatcher()
                            方法定义如下：public C antMatchers(String... antPatterns)
                            参数是不定向参数，每个参数是一个ant 表达式，用于匹配URL规则。
                            规则如下：
                                ? 匹配一个字符
                                * 匹配0 个或多个字符
               ** 匹配0 个或多个目录
         *
                        *内置访问控制方法
         *  1 permitAll()
                permitAll()表示所匹配的URL 任何人(登录、未登录)都允许访问。会走springsecurity的过滤器链
            2 denyAll()
                denyAll()表示所匹配的URL 都不允许被访问。
            3 anonymous()
                anonymous()表示可以匿名访问匹配的URL。用户登录前能访问，登录后不能访问（登录接口，只需登陆一次就可以）
            4 authenticated()
                authenticated()表示所匹配的URL 都需要被认证才能访问。
            5 fullyAuthenticated()
                                        如果用户不是被remember me 的，才可以访问。
            6 rememberMe()
                                        被“remember me”的用户允许访问
                                        
                     *前后端分离：后台：不在SecurityContext设置用户信。，  配置： 不通过Session获取SecurityContext 
         *SecurityContext：存储登录后用户信息     默认从session中获取SecurityContext。只要是同一个会话就能获取到同一个用户信息
                        *                                         前后端分离时，要禁用
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         */
        
//        //前后端不分离
//        http.authorizeRequests()    //开始权限配置
//                 .antMatchers("/main.html").permitAll()  //main.html页面不需要认证
//                 .antMatchers("/errorPage.html").permitAll()  //必须将登录失败页面放行，不然会跳转到login.html页面
//                 .anyRequest().authenticated()  //除以上配置,其他所有访问都需要认证，都需登录后才能访问
//                 .and()
//             //表单登录  fromLogin不配置时，过滤器链中无username..Filter
//             .formLogin() 
//                 .loginPage("/login.html")  //登录页，未登录时跳转页面（路径为去掉应用路径的相对路径）
//                 .loginProcessingUrl("/user/login")  //登录请求接口（路径为去掉应用路径的相对路径）
//                 .successForwardUrl("/sucess")  //认证成功后跳转url，要有对应的controller方法处理
//                 .failureForwardUrl("/errorPage")   //认证失败后跳转url，要有对应的controller方法处理
//                 .permitAll() //放行，不认证 
//             .and()
//             .csrf().disable();
//        
//        //退出登录
//        http.logout()
//                .logoutUrl("/logout")   //注销的请求路径
//                .logoutSuccessUrl("/login.html")  //注销成功后跳转页面
//                .permitAll();     //放行
//        
//        //开启记住我功能
//        http.rememberMe()
//                .tokenRepository(persistentTokenRepository())  //配置TokenRepository
//                .tokenValiditySeconds(60);   //设置有效时长，单位秒
//                //.userDetailsService(userDetailsService)   如果有自定义userService,需进行该配置
        
        //前后端分离
        http
             //不通过session获取SecurityContext
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()    //开始权限配置
                  .antMatchers("/user/jwtLogin").permitAll() //单点登录接口放行（所匹配的URL 任何人都允许访问）
                  .anyRequest().authenticated()  //除以上配置,其他所有访问都需要认证，都需登录后才能访问
                  .and()
             .csrf().disable();
       
        
       
        
        //把token校验过滤器添加到过滤器链中；给filter注入容器中的service，不能将TokenFilter注入到spring容器
        http.addFilterBefore(new TokenFilter(userService,menuMapper), UsernamePasswordAuthenticationFilter.class);
        
        //自定义异常处理
        http.exceptionHandling()
            //自定义认证失败处理
            .authenticationEntryPoint(authenticationEntryPointImp)
            //自定义权限校验失败处理
            .accessDeniedHandler(accessDeniedHandlerImp);
    }
}
