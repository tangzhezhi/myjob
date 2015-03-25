package org.tang.myjob.controller.business.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tang.myjob.controller.Interceptor.Auth;
import org.tang.myjob.controller.utils.BaseController;

import java.util.Map;

/**
 * Created by Administrator on 2015/3/25.
 */

@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {
    @Auth
    @RequestMapping(value={"/index"})
    public ModelAndView index(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "系统菜单");
        modelAndView.setViewName("/menu/menu");
        return modelAndView;
    }

    @RequestMapping(value={"/test"})
    public ModelAndView test(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "test");
        modelAndView.setViewName("/menu/test");
        return modelAndView;
    }

    @Auth("add")
    @RequestMapping(value={"/add"},method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public int add(){
        return 0;
    }

}
