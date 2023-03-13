/*
 * @(#)GlobalExceptionHandler.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年3月10日 wwp-pc          初版
 *
 */
package com.secrity.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.secrity.dataset.Result;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public  Result error(Exception e) {
        return Result.eror().message("发生异常了"+e.toString());
    }
    
    //采用注解权限会被全局异常先捕获
    @ExceptionHandler(value=AccessDeniedException.class)
    public void accessDeniedException(AccessDeniedException accessDeniedException) {
        throw accessDeniedException;
    }
}
