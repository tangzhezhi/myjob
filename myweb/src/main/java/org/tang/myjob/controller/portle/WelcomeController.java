package org.tang.myjob.controller.portle;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tang.myjob.controller.session.ShiroSessionDao;
import org.tang.myjob.controller.utils.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2015/5/20.
 */

@Controller
public class WelcomeController extends BaseController {

    @Autowired
    private ShiroSessionDao shiroSessionDao;

    @RequestMapping(value={"/index"})
    public ModelAndView index(HttpSession session,ModelAndView model, HttpServletRequest request, HttpServletResponse response){
        Session shiroSession =   shiroSessionDao.readSession(session.getId());
        System.out.println("session:id"+session.getId());
        System.out.println("session:ip"+shiroSession.getHost());
        System.out.println("session:userId"+shiroSession.getAttribute("userId"));
        System.out.println("session:userName"+shiroSession.getAttribute("userName"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");
        return modelAndView;
    }
}
