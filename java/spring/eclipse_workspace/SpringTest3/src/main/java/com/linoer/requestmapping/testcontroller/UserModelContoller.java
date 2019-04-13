package com.linoer.requestmapping.testcontroller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.linoer.requestmapping.test.UserModel;

@Controller
@RequestMapping("/requestmappingtest")
public class UserModelContoller {
	private static List<UserModel> userList;
	private static final Log logger = LogFactory.getLog(UserModelContoller.class);
	public UserModelContoller() {super(); userList = new ArrayList<UserModel>();}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm() {
		logger.info("register GET");
		return "registerForm";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerForm(
			@RequestParam("loginname") String loginname,
			@RequestParam("password") String password,
			@RequestParam("username") String username) {
		logger.info("register POST");
		UserModel userModel = new UserModel();
		userModel.setLoginname(loginname);
		userModel.setPassword(password);
		userModel.setUsername(username);
		userList.add(userModel);
		return "loginForm";
	}
	
	@RequestMapping("/login")
	public String login(
			@RequestParam("loginname") String loginname,
			@RequestParam("password") String password,
			Model model
			) {
		logger.info("登录名：" + loginname + "  密码" + password);
		for(UserModel user : userList) {
			if(user.getLoginname().equals(loginname) && user.getPassword().equals(password)) {
				model.addAttribute("user", user);
				return "welcome";
			}
		}
		return "loginForm";
	}
	
	@RequestMapping(value = "/pt/{userId}")
	public String pathVariableTest(@PathVariable Integer userId) {
		logger.info("pathVariableTest : " + userId);
		return "welcome";
	}
}
