package com.linoer.springtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linoer.springtest.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/json")
public class JsonFormattestController {

    @RequestMapping("/index")
    public String jsonTest(){

        return "format_index";
    }

    @RequestMapping("indexRequestBody")
    public void indexRequestBody(@RequestBody Student student, HttpServletResponse response) throws Exception{
//        return "format_index";
        ObjectMapper objectMapper = new ObjectMapper();
//        student.setClassId(9999);
//        student.setClassName("testName");
        student.setTeacherId(9900);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(objectMapper.writeValueAsString(student));
    }
}
