package org.tang.myjob.controller.portle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tang.myjob.controller.utils.BaseController;
import org.tang.myjob.dto.message.MessageDTO;
import org.tang.myjob.dto.product.ProductDTO;
import org.tang.myjob.dto.system.UserDTO;
import org.tang.myjob.service.LoginService;
import org.tang.myjob.service.exception.BusinessException;
import org.tang.myjob.service.exception.BusinessRuntimeException;
import org.tang.myjob.service.exception.ExceptionType;
import org.tang.myjob.service.portle.IndexService;
import org.tang.myjob.utils.json.JacksonUtil;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/3/23.
 */

@Controller("IndexController")
@RequestMapping("index")
public class IndexController extends BaseController {

    private static Logger logger = Logger.getLogger(IndexController.class.getName());

    @Autowired
    private IndexService indexService;

    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/loadIndexTopNews", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> loadIndexTopNews() throws Exception {

        Map<String ,Object> m = new HashMap<String ,Object>();

        MessageDTO dto = null;
        try {
            dto = indexService.getMessage();
            m.put("msg","success");
        }
        catch (Exception br){
            logger.error(ExceptionType.product_msg, br);
            throw new Exception(br);
        }
        m.put("result",dto);
        return m;

    }


    @RequestMapping(value = "/loadIndexProducts", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> loadIndexProducts() throws Exception {

        Map<String ,Object> m = new HashMap<String ,Object>();

        List<ProductDTO> dtoList = null;
        ProductDTO param = new ProductDTO();
        param.setType(1);
        try {
            dtoList = indexService.getProduct(param);
        } catch (Exception e) {
            logger.error(ExceptionType.product_msg,e);
            throw new Exception(ExceptionType.product_msg,e);
        }
        m.put("result",dtoList);
        return m;

    }

    @RequestMapping(value = "/loadIndexTopProduct", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> loadIndexTopProduct() throws Exception {

        Map<String ,Object> m = new HashMap<String ,Object>();

        List<ProductDTO> dtoList = null;
        ProductDTO param = new ProductDTO();
        param.setType(0);
        try {
            dtoList = indexService.getProduct(param);
        } catch (Exception e) {
            logger.error(ExceptionType.product_msg,e);
            throw new Exception(ExceptionType.product_msg,e);
        }
        m.put("result",dtoList);
        return m;

    }



    @RequestMapping(value = "/login", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> login(UserDTO dto) throws Exception {

        Map<String ,Object> m = new HashMap<String ,Object>();

        boolean flag = false;

        try {
            flag = loginService.queryUserLoginIsExist(dto);

            if(flag){
                dto.setUserPwd(null);
                m.put("user",dto);
                m.put("msg","success");
            }
        } catch (Exception e) {
            logger.error(ExceptionType.login_msg,e);
        }
        return m;

    }




}
