package org.tang.myjob.controller.login;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tang.myjob.dto.system.UserDTO;
import org.tang.myjob.service.LoginService;
import org.tang.myjob.utils.json.JacksonUtil;
import org.tang.myjob.utils.secret.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
	private  Logger  logger  = Logger.getLogger("LoginController");

	@Autowired
	public LoginService loginService;

	@RequestMapping(value = "/login", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody  
    public Object  ajaxLogin(HttpSession session,ModelAndView model, String first,HttpServletRequest request, HttpServletResponse response) {

		if(StringUtils.hasText(first)){
			try {
				Map<String ,Object> m = new HashMap<String ,Object>();
				String user = Base64.getFromBase64(first);
				UserDTO dto =  JacksonUtil.readValue(user, UserDTO.class);

				Subject subject= SecurityUtils.getSubject();
				if (subject.isAuthenticated()) {
					m.put("user",dto);
					m.put("msg", "success");
                    return m;
                }

				boolean rememberMe = ServletRequestUtils.getBooleanParameter(request, "rememberMe", false);
				UsernamePasswordToken token = new UsernamePasswordToken(dto.getUsername(), dto.getPassword(), rememberMe);
				subject.login(token); // 登录
				dto.setPassword(null);
				m.put("user",dto);
				m.put("msg","success");
				return m;
			} catch (AuthenticationException e) {
				e.printStackTrace();
			}
		}
		return null;
    }


	@RequestMapping(value = "/login")
	public void login(HttpSession session,ModelAndView model, UserDTO user,HttpServletRequest request, HttpServletResponse response) {

			try {
				Subject subject= SecurityUtils.getSubject();
				if (subject.isAuthenticated()) {
					return ;
				}

				boolean rememberMe = ServletRequestUtils.getBooleanParameter(request, "rememberMe", false);
				UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), rememberMe);
				subject.login(token); // 登录
				user.setPassword(null);
			} catch (AuthenticationException e) {
				e.printStackTrace();
			}
	}



	@RequestMapping(value = "/logout", method = {RequestMethod.POST , RequestMethod.GET})
	public void logout(HttpSession session,  UserDTO dto) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
			if (logger.isDebugEnabled()) {
				logger.debug("用户" + dto.getUsername() + "退出登录");
			}
		}
	}


}
