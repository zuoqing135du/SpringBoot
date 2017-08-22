package com.zuoqing.demo.exception;

import com.zuoqing.demo.enums.ResultEnum;

/**
 * 需要继承RuntimeException，不能继承Exception  因为spring 只对 RuntimeExcetion 进行事物回滚
 */
public class GirlException extends RuntimeException {

    private Integer code;

    public GirlException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
