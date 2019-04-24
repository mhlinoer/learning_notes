package com.linoer.app.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String userLogin(){
        return "demo_sign";
    }

    @RequestMapping("/login-error")
    public String loginError(){
        return "login_error";
    }

    @RequestMapping("/")
    public String myIndex(){
        return "my_template";
    }

}
