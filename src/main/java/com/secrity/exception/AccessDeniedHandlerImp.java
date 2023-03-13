/*
 * @(#)AccessDeniedHandlerImp.java
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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.secrity.dataset.Result;
import cn.hutool.json.JSONUtil;


//自定义权限校验失败处理 需配置给SpringSecurity：只处理过滤器链中的异常
public class AccessDeniedHandlerImp implements AccessDeniedHandler{

    @Override
    public void handle(
        HttpServletRequest request,
        HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result eror = Result.eror();
        eror.setMessage("您没有权限，拒绝访问！");
        String jsonStr = JSONUtil.toJsonStr(eror);
        
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonStr);
    }

}
