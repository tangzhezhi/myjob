package org.tang.myjob.controller.portle;

import com.gs.collections.impl.factory.Maps;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
import org.tang.myjob.dto.message.MessageDTO;
import org.tang.myjob.dto.product.OrderDTO;
import org.tang.myjob.service.exception.ExceptionType;
import org.tang.myjob.service.portle.PersonService;
import org.tang.myjob.service.redis.RedisUtil;
import org.tang.myjob.utils.File.PreviewPdfFile;
import org.tang.myjob.utils.constant.FileConstant;
import org.tang.myjob.utils.page.PageDataTable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

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

    @Autowired
    private ThreadPoolTaskExecutor myThreadPool;

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


//    @RequestMapping(value = "person/uploadFile", method = {RequestMethod.POST , RequestMethod.GET})
//    @ResponseBody
//    public Map<String, Object> uploadFile(HttpServletRequest request,MultipartFile file)
//
//    {
//        String filePath = FileConstant.uploadTempFile;// 上传文件临时路径
//        String realFilePath = request.getSession().getServletContext().getRealPath(filePath);
//        Map<String, Object> dataMap =new HashMap();
//        String fileName = file.getOriginalFilename();
//        try
//        {
//            File fileDir = new File(realFilePath);
//            if(!fileDir.exists()){
//                fileDir.mkdir();
//            }
//
//            String truePath = realFilePath+File.separator+fileName;
//
//            File uploadedFile = new File(realFilePath+File.separator+fileName);
//
//            file.transferTo(uploadedFile);
//
//            String fileSuffix = fileName.split("\\.")[1];
//
//            String filePreffix = fileName.split("\\.")[0];
//
//            if(PreviewPdfFile.judgeIsOffice(fileSuffix)){
//                try {
//                    PreviewPdfFile.windowsSystemOffice2PDF(truePath,realFilePath+File.separator+filePreffix+".pdf");
//                } catch (Exception e) {
//                    dataMap.put("result", "上传时office文件转换pdf错误");
//                    e.printStackTrace();
//                }
//            }
//
//        } catch (Exception e)
//
//        {
//            dataMap.put("result", "上传时发生错误");
//            e.printStackTrace();
//        }
//        dataMap.put("result", "success");
//        return dataMap;
//
//    }


    @RequestMapping(value="person/uploadFile",  method = {RequestMethod.POST , RequestMethod.GET})
    public @ResponseBody Callable<Map<String, Object>> responseBody(final HttpSession session, final HttpServletRequest request, final MultipartFile file) {
        System.out.println("线程名称：" + Thread.currentThread().getName());
        System.out.println(System.currentTimeMillis());
        return new Callable<Map<String, Object>>() {
            public Map<String, Object> call() throws Exception {
                // Do some work..
                String filePath = FileConstant.uploadTempFile;// 上传文件临时路径
                String userid = (String) session.getAttribute(SESSION_USERID);
                String realFilePath = request.getSession().getServletContext().getRealPath(filePath);
                Map<String, Object> dataMap =new HashMap();
                String fileName = file.getOriginalFilename();
                myThreadPool.execute(new FileUploadThread(userid,file,realFilePath,null));
                dataMap.put("result", "success");
                return dataMap;
            }
        };
    }


    public void upload2Pdf(String userid,MultipartFile file, String src, String dest){
        try
        {
            String fileName = file.getOriginalFilename();
            File fileDir = new File(src);
            if(!fileDir.exists()){
                fileDir.mkdir();
            }

            String truePath = src+File.separator+fileName;

            if(null == dest){
                File uploadedFile = new File(src+File.separator+fileName);
                file.transferTo(uploadedFile);
            }
            else{
                File uploadedFile = new File(dest+File.separator+fileName);
                file.transferTo(uploadedFile);
            }



            String fileSuffix = "";

            String filePreffix = "";

            if(fileName.contains(".")){
                fileSuffix = fileName.split("\\.")[fileName.split("\\.").length-1];
                filePreffix = fileName.split("\\.")[0];

                try {
                    if(PreviewPdfFile.judgeIsOffice(fileSuffix)){
                        try {
                            if(null == dest){
                                PreviewPdfFile.windowsSystemOffice2PDF(truePath, src + File.separator + filePreffix + ".pdf");
                            }
                            else{
                                PreviewPdfFile.windowsSystemOffice2PDF(truePath, dest + File.separator + filePreffix + ".pdf");
                            }

                            uploadFileMsg(userid,"文件解析无误");

                        } catch (Exception e) {
                            uploadFileMsg(userid, "文件解析出现异常:" + e);
                            logger.error("文件解析出现异常:",e);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e)

        {
            e.printStackTrace();
        }
    }

    public void uploadFileMsg(String userid,String msg) throws Exception {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTitle("文件上传结果");
        messageDTO.setContent(msg);
        messageDTO.setCreateDate(new Date());
        template.convertAndSend("/topic/uploadFileMsg/"+userid,messageDTO);

    }

    @MessageMapping("/order")
    @SendTo("/topic/order")
    public Greeting greeting(HelloMessage message) throws Exception {
        return new Greeting("Hello, " + message.getName() + "!");
    }


    private class FileUploadThread implements Runnable {
        private String src;
        private String dest;
        private MultipartFile file;
        private String userid;
        private FileUploadThread(String userid,MultipartFile file,String src, String dest) {
            super();
            this.src = src;
            this.dest = dest;
            this.file = file;
            this.userid = userid;
        }
        @Override
        public void run() {
            upload2Pdf(userid,file, src, dest);
        }
    }


}