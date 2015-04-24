package org.tang.myjob.controller.portle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tang.myjob.controller.utils.BaseController;
import org.tang.myjob.dto.product.OrderDTO;
import org.tang.myjob.service.exception.ExceptionType;
import org.tang.myjob.service.portle.PersonService;
import org.tang.myjob.service.redis.RedisUtil;
import org.tang.myjob.utils.page.Page;
import org.tang.myjob.utils.page.PageDataTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/18.
 */
@Controller("PersonController")
public class PersonController  extends BaseController {

    private static Logger logger = Logger.getLogger(PersonController.class.getName());

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    public PersonController(SimpMessagingTemplate t) {
        template = t;
    }

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "person/getPersonPicture", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Object getPersonPicture(OrderDTO params) throws Exception {

        Map<String ,Object> m = new HashMap<String ,Object>();
        PageDataTable<OrderDTO> page = new PageDataTable();
        try {
            page = personService.getPersonOrderPage(params);
            m.put("msg","success");
        }
        catch (Exception br){
            logger.error(ExceptionType.product_msg, br);
            throw new Exception(br);
        }
//        m.put("sEcho", "");
//        m.put("iTotalRecords", page.getiTotalRecords());
//        m.put("iTotalDisplayRecords",page.getiTotalDisplayRecords());
//        m.put("iDisplayStart", page.getiDisplayStart());
//        m.put("iDisplayLength", page.getiDisplayLength());
//        m.put("aaData",page.getAaData());

        return page;
//        return m;

    }

    @MessageMapping("/order")
    @SendTo("/topic/order")
    public Greeting greeting(HelloMessage message) throws Exception {
        return new Greeting("Hello, " + message.getName() + "!");
    }
}