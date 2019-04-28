package com.linoer.app.controller;

import com.linoer.app.pojo.MyTest;
import com.linoer.app.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService service;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public MyTest getTestById(@RequestParam("id") String id){
        System.out.println("lmh test get in");
        return service.getById(Integer.parseInt(id));
    }
}
