package org.tang.myjob.controller.portle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tang.myjob.controller.utils.BaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2015/5/20.
 */

@Controller
public class WelcomeController extends BaseController {

    @RequestMapping(value={"/index"})
    public ModelAndView index(HttpSession session,ModelAndView model, HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");
        return modelAndView;
    }
}
