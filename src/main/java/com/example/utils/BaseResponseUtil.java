package com.example.utils;

import com.example.constant.CodeConstant;
import com.example.constant.ResultCodeConstant;
import com.example.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;


/**
 * @version: 1.00.00
 * @description: 统一返回结构格式工具类（主service层，次controller层）
 * @copyright: Copyright (c) 2018 立林科技 All Rights Reserved
 * @company: 厦门立林科技有限公司
 * @author: chenhaidi
 * @date: 2019-10-22 10:55
 */
public class BaseResponseUtil {
    private static final Logger logger = LoggerFactory.getLogger(BaseResponseUtil.class);

    static HashMap<String, Integer> businessCode = new HashMap<>();
    static {
        businessCode.put(String.valueOf(ResultCodeConstant.FAIL), CodeConstant.PARAERROR);
        businessCode.put(String.valueOf(ResultCodeConstant.PARAERROR), CodeConstant.PARAERROR);
        businessCode.put(String.valueOf(ResultCodeConstant.ILLEGAL), CodeConstant.ILLEGAL);
    }

    public static BaseResponse success(){
        BaseResponse response = new BaseResponse();
        response.setCode(CodeConstant.SUCCESS);
        response.setMsg("成功");
        return response;
    }

    public static BaseResponse success(Object object){
        BaseResponse response = new BaseResponse();
        response.setCode(CodeConstant.SUCCESS);
        response.setMsg("成功");
        response.setData(object);
        return response;
    }

    public static BaseResponse fail(){
        BaseResponse response = new BaseResponse();
        response.setCode(CodeConstant.FAIL);
        response.setMsg("失败");
        return response;
    }

    public static BaseResponse fail(int code,String msg){
        BaseResponse response = new BaseResponse();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }

    public static BaseResponse fail(Object object){
        BaseResponse response = new BaseResponse();
        response.setCode(CodeConstant.FAIL);
        response.setMsg("失败");
        response.setData(object);
        return response;
    }

    public static BaseResponse requestError(){
        BaseResponse response = new BaseResponse();
        response.setCode(CodeConstant.PARAERROR);
        response.setMsg("请求参数错误");
        return response;
    }

    /**
     * 自定义异常（controller层），并转换错误码
     * @param be
     * @return
     */
    public static BaseResponse handleBusinessException(BusinessException be){
        int code = 0;
        try{
            if (businessCode.containsKey(be.getCode())){
                /**
                 * 转码：第三方的错误码和自身定义的错误码相转换
                 */
                code = businessCode.get(be.getCode());
            }else {
                code = Integer.parseInt(be.getCode());
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return BaseResponseUtil.fail(code, be.getMsg());
    }

}
