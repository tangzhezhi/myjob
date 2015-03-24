package org.tang.myjob.controller.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/3/24.
 */
@Controller
public class ErrorController {

    @RequestMapping(value="/errorpage/500", produces="application/json")
    @ResponseBody
    public Map<String, Object> handle(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", request.getAttribute("javax.servlet.error.status_code"));
        map.put("reason", request.getAttribute("javax.servlet.error.message"));
        map.put("exception", request.getAttribute("javax.servlet.error.exception"));
        map.put("exception_type", request.getAttribute("javax.servlet.error.exception_type"));
        map.put("request_uri", request.getAttribute("javax.servlet.error.request_uri"));
        map.put("servlet_name", request.getAttribute("javax.servlet.error.servlet_name"));

        return map;
    }

}
