package org.tang.myjob.controller.portle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tang.myjob.dto.product.OrderDTO;
import org.tang.myjob.service.exception.ExceptionType;
import org.tang.myjob.service.portle.PersonService;
import org.tang.myjob.service.redis.RedisUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/18.
 */
@Controller
public class PersonController {

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
    public Map<String, Object> getPersonPicture(String userid) throws Exception {

        Map<String ,Object> m = new HashMap<String ,Object>();

        List<OrderDTO> dto = null;
        try {
            dto = personService.getPersonOrder(userid);
            m.put("msg","success");
        }
        catch (Exception br){
            logger.error(ExceptionType.product_msg, br);
            throw new Exception(br);
        }
        m.put("result",dto);
        return m;

    }

    @MessageMapping("/order")
    @SendTo("/topic/order")
    public Greeting greeting(HelloMessage message) throws Exception {
        return new Greeting("Hello, " + message.getName() + "!");
    }
}