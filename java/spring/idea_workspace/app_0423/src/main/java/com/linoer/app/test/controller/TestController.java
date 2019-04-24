package com.linoer.app.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/json")
    public String testJson(){
        return "json_format";
    }
    @RequestMapping("/index")
    public String showIndex(){
        return "my_template";
    }

}
