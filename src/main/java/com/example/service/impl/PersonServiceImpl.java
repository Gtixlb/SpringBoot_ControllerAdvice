package com.example.service.impl;

import com.example.service.PersonService;
import com.example.utils.BaseResponse;
import com.example.utils.BaseResponseUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @version: 1.00.00
 * @description:
 * @copyright: Copyright (c) 2018 立林科技 All Rights Reserved
 * @company: 厦门立林科技有限公司
 * @author: chenhaidi
 * @date: 2019-10-22 15:17
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public BaseResponse eat(String eat) {
        if(StringUtils.isBlank(eat)){
            throw new NullPointerException();
        }else {
           return BaseResponseUtil.success(eat);
        }
    }

    @Override
    public BaseResponse sleep(String time) {
        if(StringUtils.isBlank(time)){
            return BaseResponseUtil.fail("无效参数");
        }else {
            return BaseResponseUtil.success(time+"长");
        }
    }
}
