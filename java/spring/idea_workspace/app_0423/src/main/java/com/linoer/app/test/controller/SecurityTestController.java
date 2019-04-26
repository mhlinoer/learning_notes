package com.linoer.app.test.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/security")
//@Secured("ROLE_ADMIN")
public class SecurityTestController {

    @RequestMapping("/test/{info}")
    public String test1(@PathVariable String info, HttpServletRequest request){
        request.setAttribute("info", info);
        return "my_template";
    }
}
