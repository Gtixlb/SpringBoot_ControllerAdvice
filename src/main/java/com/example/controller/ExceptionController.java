package com.example.controller;

import com.example.exception.BusinessException;
import com.example.service.PersonService;
import com.example.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version: 1.00.00
 * @description:
 * @copyright: Copyright (c) 2018 立林科技 All Rights Reserved
 * @company: 厦门立林科技有限公司
 * @author: chenhaidi
 * @date: 2019-10-22 13:43
 */
@RestController
public class ExceptionController {
    @Autowired
    private PersonService personService;

    @RequestMapping("/exception")
    public void exceptionMethod(){
        int j = 11/0;
    }

    @RequestMapping("/business")
    public void businessMethod(){
        throw new BusinessException("0","自定义异常！！");
    }

    @RequestMapping("/null")
    public void nullPointerMethod(){
        throw new NullPointerException("我是空指针！！");
    }

    @RequestMapping("/eat")
    public BaseResponse eatMethod(){
        return personService.eat("");
    }

    @RequestMapping("/sleep")
    public BaseResponse sleepMethod(String time){
        return personService.sleep(time);
    }


}
