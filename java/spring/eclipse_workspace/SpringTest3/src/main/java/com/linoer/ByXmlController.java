package com.linoer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ByXmlController implements Controller {

	public static final Log logger = LogFactory.getLog(ByXmlController.class);
	
	/**
	 * 
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("handler request");
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "hello 111 world");
		mv.setViewName("/WEB-INF/content/welcome.jsp");
		return mv;
	}
	
}
