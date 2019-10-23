
## 1. 全局异常处理
全局异常处理 @ControllerAdvice + @ExceptionHandler
在spring 3.2中，新增了@ControllerAdvice 注解，可以用于定义@ExceptionHandler、@InitBinder、@ModelAttribute，并应用到所有@RequestMapping中。
>简单的说，进入Controller层的错误才会由@ControllerAdvice处理，拦截器抛出的错误以及访问错误地址的情况@ControllerAdvice处理不了，由SpringBoot默认的异常处理机制处理。

1. 代码
 ```   
    /**
        controller层
    **/
    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessException(BusinessException be){
        logger.error(be.getCode(),be.getMsg());
        BaseResponse response = BaseResponseUtil.handleBusinessException(be);
        return response;
    }
```    
---------- 

    /**
        自定义异常类
    **/  
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
    }    
----------  

    /**
        全局异常类（@RestControllerAdvice)
    **/ 
    @ExceptionHandler(BusinessException.class)
        public BaseResponse businessException(BusinessException be){
            logger.error(be.getCode(),be.getMsg());
            BaseResponse response = BaseResponseUtil.handleBusinessException(be);
            return response;
        }      
----------  
    /**
        统一返回工具类
    **/
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


    作者：嘟爷MD
    链接：https://www.jianshu.com/p/accec85b4039
    来源：简书
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


## 2. 错误码转换
1. 错误码转换（用于和第三方交互时响应的错误码转换再传给前端（或客户端）
2. 代码
```
    /**
        错误码转换类（统一返回工具类中）
    **/
    static HashMap<String, Integer> businessCode = new HashMap<>();
            static {
                businessCode.put(String.valueOf(ResultCodeConstant.FAIL), CodeConstant.PARAERROR);
                businessCode.put(String.valueOf(ResultCodeConstant.PARAERROR), CodeConstant.PARAERROR);
                businessCode.put(String.valueOf(ResultCodeConstant.ILLEGAL), CodeConstant.ILLEGAL);
            }

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
```

## 3. 统一返回工具类（BaseResponseUtil)
1. 用响应工具类（BaseResponse）响应请求的格式，用统一返回工具类返回成功或失败。

2. 代码

```    
    /**
        响应工具类
    **/
    public class BaseResponse<T> implements Serializable {
        private int code;
    
        private String msg;
    
        private T data;
    }
```
---
    
    /**
         统一返回工具类
    **/
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
    }

---

    /**
        Service层
    **/
    @Override
    public BaseResponse sleep(String time) {
        if(StringUtils.isBlank(time)){
            return BaseResponseUtil.fail("无效参数");
        }else {
            return BaseResponseUtil.success(time+"长");
        }
    }


