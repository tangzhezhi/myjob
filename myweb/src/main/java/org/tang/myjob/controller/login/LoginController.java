package org.tang.myjob.controller.login;

import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.tang.myjob.dto.system.UserDTO;
import org.tang.myjob.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller("loginController")
@RequestMapping("login")  
public class LoginController {
	private  Logger  logger  = Logger.getLogger("LoginController");

	@Autowired
	public LoginService loginService;

	@RequestMapping(value = "/userLogin", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody  
    public ModelAndView  userLogin(HttpSession session,ModelAndView model,UserDTO dto) {
		Boolean u = loginService.queryUserLoginIsExist(dto);
		if(u){
			model.setViewName("redirect:/index.html");
		}
		else{
			model.setViewName("redirect:/login.html");
		}
		 return model;  //跳
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
