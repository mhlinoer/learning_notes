package com.linoer.springtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app")
public class HelloController {

    @RequestMapping("/hello")
//    @ResponseBody
    public String sayHello(){
//        return "hello, springboot";
        return "resultPage";
    }

    @RequestMapping("/hello2")
    public String sayHello2(Model model){
        model.addAttribute("message", "hello from the controller");
        return "resultPage";
    }

    @RequestMapping("/hello3")
    public String sayHello3(@RequestParam("name") String name, Model model){
        model.addAttribute("message", name);
        return "resultPage";
    }
}
