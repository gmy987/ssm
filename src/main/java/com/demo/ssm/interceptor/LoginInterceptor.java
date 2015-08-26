package com.demo.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by gmy on 15/8/10.
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求的url,判断此url是否是公开地址,即是否需要登录才能访问
        StringBuffer requestURL = request.getRequestURL();
        //判断url是否是公开地址,实际项目需要将公开地址配置在配置文件中
        if (requestURL.indexOf("login.action") >= 0) {
            //如果进行登录提交,放行
            return true;
        }
        //判断session
        HttpSession session = request.getSession();
        //从session中取出用户身份信息
        String  username = (String) session.getAttribute("username");
        if (username != null) {
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
