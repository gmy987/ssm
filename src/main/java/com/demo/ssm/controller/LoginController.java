package com.demo.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by gmy on 15/8/10.
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    @RequestMapping("/login")
    public String login(String username,String password,HttpSession httpSession) throws Exception {
        //校验....
        //...

        httpSession.setAttribute("username",username);
        return "redirect:/items/queryItems";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession httpSession) throws Exception {
        httpSession.invalidate();
        return "redirect:/items/queryItems";
    }
}
