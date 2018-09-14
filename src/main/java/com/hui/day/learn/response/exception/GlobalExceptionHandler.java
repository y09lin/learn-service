/*
 * Copyright (C) 2016 eKing Technology, Inc. All Rights Reserved.
 */
package com.hui.day.learn.response.exception;

import com.hui.day.learn.response.RestResponse;
import com.hui.day.learn.response.codes.Default0Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author zengfan
 */
@RestControllerAdvice
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 系统自定义全局异常
     */
    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public RestResponse exception(HttpServletRequest request, GlobalException e) {
        RestResponse exception;
        if (e.getExtraInfo().isEmpty()) {
            exception = RestResponse.exception(e.getCodeEnum(),null);
        } else {
            exception = RestResponse.exception(e.getCodeEnum(), e.getExtraInfo());
        }

        log.error("GlobalException: {}", exception, e);
        return exception;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestResponse exception(HttpServletRequest request, Exception e) {
        RestResponse exception = RestResponse.exception(Rest2Code.REST_ILLEGAL_PARAMS,null);
        log.error("GlobalException: {}", e);
        return exception;
    }

    /**
     * controller 参数转化时, 主要从这里捕获错误信息
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public RestResponse exception(HttpServletRequest req, HttpMessageNotReadableException e) {
        RestResponse exception = RestResponse.exception(Rest2Code.REST_ILLEGAL_PARAMS,null);
        log.error("HttpMessageNotReadableException: {}", exception, e);
        return exception;
    }

    /**
     * 这个兜底
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public RestResponse exception(HttpServletRequest req, RuntimeException e) {
        RestResponse exception = RestResponse.exception(Default0Code.INTERNAL_SERVER_ERROR,null);
        log.error("RuntimeException: {}", exception, e);
        return exception;
    }
}
