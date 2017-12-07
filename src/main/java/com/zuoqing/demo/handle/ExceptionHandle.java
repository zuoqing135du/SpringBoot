package com.zuoqing.demo.handle;


import com.zuoqing.demo.entity.Result;
import com.zuoqing.demo.exception.GirlException;
import com.zuoqing.demo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */

@ControllerAdvice
public class ExceptionHandle {

    //Spring 自带的日志工具
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof GirlException){
            GirlException girlException = (GirlException) e;
            return ResultUtil.error(girlException.getCode(), girlException.getMessage());
        }
        logger.error("【系统异常】 {}",e);
        return ResultUtil.error(-1, e.toString());
    }
}


















