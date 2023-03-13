/*
 * @(#)loginController.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2022年6月14日 wwp-pc          初版
 *
 */
package com.secrity.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.secrity.dataset.Result;
import com.secrity.service.LoginService;


@Controller
public class loginController {

    @Autowired
    private LoginService loginService;
    
   
    
    @RequestMapping("/sucess")
    public String login(HttpServletResponse response) {
        System.out.println("登录成功");
        //访问HTML文件，它并不支持响应头带有 post 的应答包，所以会报错，解决：加redirect重定向直接请求html页面
        return "redirect:/success.html";   //绝对路径：相对于项目根路径+/main.html -> http://127.0.0.1:8080/sc/main.html
       
    }
    
    @RequestMapping("/errorPage")
    public String errorPage() {
        System.out.println("登录失败");
        //访问HTML文件，它并不支持响应头带有 post 的应答包，所以会报错，解决：加redirect重定向直接请求html页面
        return "redirect:/errorPage.html";   //绝对路径：相对于项目根路径+/main.html -> http://127.0.0.1:8080/sc/main.html
       
    }
    
    @RequestMapping("/index")
    public String index(HttpServletResponse response) {
        System.out.println("sdfdsfd");
        
        
        Cookie c = new Cookie("sessionCookie", "aaa");
        
        Cookie c2 = new Cookie("hisCookie", "his");
        c2.setMaxAge(10);
        response.addCookie(c);
        response.addCookie(c2);
        //当前访问路径：http://127.0.0.1:8080/sc/login/index
        return "/main.html";   //绝对路径：相对于项目根路径+/main.html -> http://127.0.0.1:8080/sc/main.html
       
    }
    
    /**
     * 單點登錄接口，走过滤器链
     * @param userName
     * @param pass
     * @return
     */
    @PostMapping("/user/jwtLogin")
    @ResponseBody
    public Result jwtLogin(String userName,String passWord) {
        Result result = loginService.login(userName, passWord);
        return result;
    }
    
    /**
     * 單點退出接口，走
     * @param userName
     * @param pass
     * @return
     */
    @PostMapping("/user/logout")
    @ResponseBody
    public Result logout() {
        Result result = loginService.logout();
        return result;
    }
    
    @PreAuthorize("hasAuthority('aa')")
    @PostMapping("/user/add")
    @ResponseBody
    public Result addUser(Authentication authentication) {
        System.out.println(authentication);
        return Result.success();
    }
}
