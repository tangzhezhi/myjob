package org.tang.myjob.controller.login;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.tang.myjob.dto.system.UserDTO;


@Controller("loginController")  
@RequestMapping("login")  
public class LoginController {
	private  Logger  logger  = Logger.getLogger("LoginController");
	
	@RequestMapping(value = "/userLogin", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody  
    public ModelAndView  userLogin(HttpSession session,ModelAndView model,UserDTO dto) {
		 model.setViewName("redirect:/index.html");
		 return model;  //跳转
    }
	
	
	@RequestMapping(value = "/userLoginOut", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public ModelAndView  userLoginOut(HttpSession session,
    		ModelAndView model, UserDTO dto) {
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		model.setViewName("redirect:/login.html");
		return model;  //跳转  ;  
    }  
	
    
}
