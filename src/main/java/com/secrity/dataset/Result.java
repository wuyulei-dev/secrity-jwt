/*
 * @(#)Result.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2022年8月31日 wwp-pc          初版
 *
 */
package com.secrity.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Result {

    private Boolean success;
    private Integer code;
    private Object data;
    private String message;

    public static Result success() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(200);
        return result;
    }

    public static Result eror() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(501);
        return result;
    }

    public Result data(
        Object data) {
        this.setData(data);
        return this;
    }

    public Result message(
        String message) {
        this.setMessage(message);
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(
        Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(
        int i) {
        this.code = i;
    }

    public Object getData() {
        return data;
    }

    public void setData(
        Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(
        String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    
}
