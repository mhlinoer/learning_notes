package com.linoer.app.test.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
@Secured("ROLE_ADMIN")
public class SecurityTestController {

    @RequestMapping("/test1")
    public String test1(){
        return "my_template";
    }
}
