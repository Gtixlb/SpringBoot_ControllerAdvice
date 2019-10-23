package com.example.constant;

/**
 * @version: 1.00.00
 * @description:请求应答code定义
 * @copyright: Copyright (c) 2018 立林科技 All Rights Reserved
 * @company: 厦门立林科技有限公司
 * @author: chenhaidi
 * @date: 2019-10-22 11:03
 */
public interface ResultCodeConstant {

    /**
     *请求失败
     */
    int FAIL = 0;

    /**
     * 请求成功
     */
    int SUCCESS = 1;

    /**
     * 非法请求
     */
    int ILLEGAL = 1000;

    /**
     * 请求参数错误
     */
    int PARAERROR = 1001;

}
