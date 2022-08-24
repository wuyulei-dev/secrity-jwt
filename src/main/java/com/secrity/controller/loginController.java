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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class loginController {

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
    
    
    @PostMapping("/getStr")
    public void getStr() {
        System.out.println("sdfdsfd");
    }
}
