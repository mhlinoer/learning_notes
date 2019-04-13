package com.linoer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.linoer.model.User;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Log logger = LogFactory.getLog(UserController.class);
	
	@ModelAttribute
	public void userModel(String username, String password, Model model) {
		logger.info("user model:" + username);
		User user = new User(username, password);
		model.addAttribute("user", user);
	}
	
	@RequestMapping("/userlogin1")
	public String login1(Model model) {
		logger.info("login");
		User user = (User) model.asMap().get("user");
		logger.info("login before" + user.username);
		user.setUsername("hello1");
		logger.info("login after" + user.username);
		return "result";
	}
	
	@RequestMapping("/userlogin2")
	public String login2(ModelMap modelMap) {
		logger.info("login2");
		User user = (User) modelMap.get("user");
		user.setUsername("hello2");
		return "result";
	}
	
	public ModelAndView login3(ModelAndView mv) {
		logger.info("login3");
		User user = (User) mv.getModel().get("user");
		user.setUsername("hello3");
		mv.setViewName("result3");
		return mv;
	}
	
}
