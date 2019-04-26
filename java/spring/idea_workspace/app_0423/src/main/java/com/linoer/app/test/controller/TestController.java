package com.linoer.app.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/json/{test}")
    public String testJson(@PathVariable String test,HttpServletRequest request){
        request.setAttribute("test", test);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "tom");
//        create(test, new JSONObject(map));
        return "json_format";
    }
    @RequestMapping("/index")
    public String showIndex(){
        return "my_template";
    }
}
