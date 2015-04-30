package org.tang.myjob.controller.portle;

import com.gs.collections.impl.factory.Maps;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.tang.myjob.controller.utils.BaseController;
import org.tang.myjob.controller.utils.UploadImageUtil;
import org.tang.myjob.dto.product.OrderDTO;
import org.tang.myjob.service.exception.ExceptionType;
import org.tang.myjob.service.portle.PersonService;
import org.tang.myjob.service.redis.RedisUtil;
import org.tang.myjob.utils.constant.FileConstant;
import org.tang.myjob.utils.page.PageDataTable;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

        PageDataTable page = new PageDataTable();

        try {
            page = personService.getPersonOrderPage(params);
        }
        catch (Exception br){
            logger.error(ExceptionType.product_msg, br);
            throw new Exception(br);
        }

        return page;

    }

    @RequestMapping(value = "person/add", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Map add(OrderDTO params) throws Exception {
        Map m = new HashMap();
        int flag = -1;
        try {
            flag  = personService.add(params);
            if(flag > 0){
                m.put("msg","success");
            }
            else{
                m.put("msg","false");
            }

        }
        catch (Exception br){
            logger.error(ExceptionType.msg, br);
            throw new Exception(br);
        }
        return m;
    }


    @RequestMapping(value = "person/uploadFile", method = {RequestMethod.POST , RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> uploadFile(HttpServletRequest request,MultipartFile file)

    {
        String filePath = FileConstant.imageFile;// 配置图片路径
        String realFilePath = request.getSession().getServletContext().getRealPath(filePath);
        Map<String, Object> dataMap =new HashMap();
        String fileName = file.getOriginalFilename();
        try
        {
            InputStream fileInputStream = file.getInputStream();


            file.transferTo(new File(realFilePath+File.separator+fileName));

//            File filedir = new File(realFilePath);
//            if(!filedir.exists()){
//                filedir.mkdir();
//            }
//            OutputStream outputStream = new FileOutputStream(realFilePath+File.separator+fileName);
//
//            int bytesWritten = 0;
//            int byteCount = 0;
//
//            byte[] bytes = new byte[1024];
//
//            while ((byteCount = fileInputStream.read(bytes)) != -1)
//            {
//                outputStream.write(bytes, bytesWritten, byteCount);
//                bytesWritten += byteCount;
//            }
//            fileInputStream.close();
//            outputStream.close();

        } catch (Exception e)

        {
            dataMap.put("result", "部署流程时发生错误");
            e.printStackTrace();
        }
        dataMap.put("result", "success");
        return dataMap;

    }



    @MessageMapping("/order")
    @SendTo("/topic/order")
    public Greeting greeting(HelloMessage message) throws Exception {
        return new Greeting("Hello, " + message.getName() + "!");
    }
}