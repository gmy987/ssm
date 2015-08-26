package com.demo.ssm.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gmy on 15/8/7.
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    /**
     *
     * @param request
     * @param response
     * @param handler 处理器适配器要执行的handler对象,只有method
     * @param ex 截获的抛出的异常
     * @return
     * @Description 解析出异常类型,若为系统自定义的异常,直接取出异常信息,
     *              在页面展示,若不是系统自定义的异常,构造一个自定义的类型,信息为未知错误
     *
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        /*String message;
        if (ex instanceof CustomException) {
            message = ((CustomException) ex).getMessage();
        } else {
            message = "未知错误";
        }*/

        CustomException customException = null;
        if (ex instanceof CustomException) {
            customException = (CustomException) ex;
        } else {
            ex.printStackTrace();
            customException = new CustomException("未知错误");
        }
        String message = customException.getMessage();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", message);
        //指向错误页面
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
