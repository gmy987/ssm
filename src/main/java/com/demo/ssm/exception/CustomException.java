package com.demo.ssm.exception;

/**
 * Created by gmy on 15/8/7.
 * 自定义异常类
 * 针对预期的异常,需要在程序中抛出此类的异常
 *
 */
public class CustomException extends Exception {
    //异常信息
    private String message;

    public CustomException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
