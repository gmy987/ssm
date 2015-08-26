package com.demo.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gmy on 15/8/10.
 * springmvc拦截器针对HandlerMapping进行拦截
 * 如果在某个HandlerMapping中配置拦截,经过该HandlerMapping映射成功的handler最终使用该拦截器
 * 若有多个拦截器,preHandle顺序执行,postHandle和afterComplete按拦截器配置的逆向顺序执行
 * 拦截器1放行,2不放行,结果:
 *          HandlerInterceptor1..preHandle
 *          HandlerInterceptor2..preHandle
 *          HandlerInterceptor1..afterCompletion
 * 若拦截器1不放行,则其他所有都不执行
 * 总结: 日志处理中,一定放在拦截器1,且preHandle一定要放行
 *       登陆认证,放在日志后面,其他前面,权限校验,放在登陆认证之后
 */
public class HandlerInterceptor1 implements HandlerInterceptor {

    //进入handler方法之前执行
    //用于身份认证和身份授权
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //返回false表示拦截住,不再往下放行,return true表示继续执行
        System.out.println("HandlerInterceptor1..preHandle");
        return true;
    }

    //进入handler方法之后,在返回ModelAndView之前执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //应用场景从ModelAndView出发:将公用的模型数据,在这里传到视图,也可以在此统一指定视图
        //比如,菜单的导航,每个视图菜单的内容都是一样的,可以再此统一指定
        System.out.println("HandlerInterceptor1..postHandle");
    }

    //执行handler之后执行此方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //统一的异常处理
        //统一的日志处理
        System.out.println("HandlerInterceptor1..afterCompletion");
    }
}
