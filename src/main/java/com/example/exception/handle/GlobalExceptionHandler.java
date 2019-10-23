package com.example.exception.handle;

import com.example.constant.CodeConstant;
import com.example.exception.BusinessException;
import com.example.utils.BaseResponse;
import com.example.utils.BaseResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @version: 1.00.00
 * @description: 全局异常处理（针对Controller层上面的方法）
 * @copyright: Copyright (c) 2018 立林科技 All Rights Reserved
 * @company: 厦门立林科技有限公司
 * @author: chenhaidi
 * @date: 2019-10-21 18:43
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 自定义业务异常
     * @param be
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessException(BusinessException be){
        logger.error(be.getCode(),be.getMsg());
        BaseResponse response = BaseResponseUtil.handleBusinessException(be);
        return response;
    }

    /**
     * 异常类
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public BaseResponse innerError(Exception e){
        logger.error("未知错误："+e.getMessage());
        /**
         * 判断Exception是否是BusinessException
         */
        if(e instanceof BusinessException){
            return businessException((BusinessException)e);
        }
        return BaseResponseUtil.fail(CodeConstant.INNER_ERROR,e.getMessage());

    }

    /**
     * 空指针异常
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public BaseResponse nullPointerException(NullPointerException e){
        logger.error("空指针异常："+e.getMessage());
        return BaseResponseUtil.fail(CodeConstant.NULL_ERROR,e.getMessage());
    }



}
