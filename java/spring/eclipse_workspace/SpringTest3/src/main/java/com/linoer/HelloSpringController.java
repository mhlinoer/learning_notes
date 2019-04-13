package com.linoer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.linoer.model.User;

@Controller
@RequestMapping(value="/app")
public class HelloSpringController {
	String message = "Welcome to Spring MVC!";
	
	@RequestMapping(value="/hello", method={RequestMethod.POST, RequestMethod.GET}, consumes="application/json", produces="application/json")
	public ModelAndView showMessage(@RequestParam(value = "name", required = false, defaultValue = "Spring") String name) {
		 
        ModelAndView mv = new ModelAndView("hellospring");
        //指定视图
        //向视图中添加所要展示或使用的内容，将在页面中使用
        mv.addObject("message", message);
        mv.addObject("name", name);
        return mv;
    }

	@RequestMapping(value="/login")
	public String login(HttpServletRequest request) {
		return "this is login";
	}
	
	@ModelAttribute
	public void userModel(String username, String password, Model model){
		User user = new User(username, password);
		model.addAttribute("user", user);
	}
	
}
