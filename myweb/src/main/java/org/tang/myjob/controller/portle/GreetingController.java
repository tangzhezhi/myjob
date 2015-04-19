package org.tang.myjob.controller.portle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tang.myjob.dto.system.UserDTO;

import java.util.Date;

/**
 * Created by Administrator on 2015/4/18.
 */
@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate template;


    @Autowired
    public GreetingController(SimpMessagingTemplate t) {
        template = t;
    }

    @MessageMapping("/greeting")
    public String handle(String greeting) {
        System.out.println("greeting::::::::"+greeting);
        return "[" + new Date().getTime() + ": " + greeting;
    }


    @RequestMapping(value="/greetings", method = {RequestMethod.POST , RequestMethod.GET})
//    @SendTo("/topic/greetings")
    public void greet(String greeting) {
        String text = ":::::::::::::::::::" + greeting;
        System.out.println("text::"+text);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("1");
        userDTO.setUserName("tangzhezi");
        this.template.convertAndSend("/topic/greetings", userDTO);
//        return userDTO;
    }

}