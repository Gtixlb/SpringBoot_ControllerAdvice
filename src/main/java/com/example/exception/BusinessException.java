package com.example.exception;

/**
 * @version: 1.00.00
 * @description: 已定义业务异常处理
 * @copyright: Copyright (c) 2018 立林科技 All Rights Reserved
 * @company: 厦门立林科技有限公司
 * @author: chenhaidi
 * @date: 2019-10-21 17:29
 */
public class BusinessException extends RuntimeException {
    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String msg;

    public BusinessException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
