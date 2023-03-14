/*
 * @(#)AuthenticationEntryPointImp.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年3月12日 wwp-pc          初版
 *
 */
package com.secrity.exception;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.secrity.dataset.Result;
import cn.hutool.json.JSONUtil;


//自定义认证失败处理 需配置给SpringSecurity：：只处理过滤器链中的异常
@Component
public class AuthenticationEntryPointImp implements AuthenticationEntryPoint{

    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
//        在SpringSecurity中，如果我们在认证或者授权的过程中出现了异常会被ExceptionTranslationFilter捕获到。
//        在ExceptionTranslationFilter中会去判断是认证失败还是授权失败出现的异常。
//        ​如果是认证过程中出现的异常会被封装成AuthenticationException然后调用AuthenticationEntryPoint对象的方法去进行异常处理。
        Result eror = Result.eror();
        eror.setMessage("您还未登录！无法访问");
        String jsonStr = JSONUtil.toJsonStr(eror);
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonStr);
    }

}
