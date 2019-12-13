package com.aaa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("login-check")
    public String loginCheck(){
        System.out.println("login-check");
        return "home";
    }

    @RequestMapping("login-success")
    public String loginSuccess(){
        System.out.println("login-success");
        return "ok";
    }

    @RequestMapping("login-error")
    public String loginError(){
        System.out.println("login-error");
        return "error";
    }
}
