package org.tang.myjob.controller.portle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tang.myjob.dto.message.MessageDTO;
import org.tang.myjob.dto.system.UserDTO;
import org.tang.myjob.service.LoginService;
import org.tang.myjob.service.portle.IndexService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/3/23.
 */

@Controller
@RequestMapping("portle/Index")
public class IndexController {

    private static Logger logger = Logger.getLogger(IndexController.class.getName());

    @Autowired
    public IndexService indexService;


    @RequestMapping(value = "/loadIndexJumbotronContent", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> loadIndexJumbotronContent() {

        Map<String ,Object> m = new HashMap<String ,Object>();

        MessageDTO dto = indexService.getMessage();

        m.put("data",dto);
        return m;  //跳
    }


}
