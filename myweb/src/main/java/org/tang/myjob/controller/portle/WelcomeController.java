package org.tang.myjob.controller.portle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tang.myjob.controller.utils.BaseController;

/**
 * Created by Administrator on 2015/5/20.
 */

@Controller
public class WelcomeController extends BaseController {

    @RequestMapping(value={"/index"})
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/test");
        return modelAndView;
    }
}
