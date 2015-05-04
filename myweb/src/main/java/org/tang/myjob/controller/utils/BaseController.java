package org.tang.myjob.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2015/3/25.
 */
public abstract  class BaseController {
    public static final String SESSION_USERID = "tangUSERID";
    public static final String SESSION_AUTHS = "tangAUTHS";
    //KEY值根据SessionID生成
    public static final String SID_PREFIX = "online:sid:";
    public static final String UID_PREFIX = "online:uid:";
//    @RequestMapping("upload")
//    public String upload(Model model,HttpServletRequest request)
//    {
//        //转型为MultipartHttpRequest(重点的所在)
//        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
//        //  获得第1张图片（根据前台的name名称得到上传的文件）
//        MultipartFile imgFile1  =  multipartRequest.getFile("imgFile");
//
//        UploadImageUtil uploadutil = new UploadImageUtil();
//        String fileName = imgFile1.getOriginalFilename();
//        try {
//            uploadutil.uploadImage1(request, imgFile1, imgFile1.getContentType(), fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "redirect:query";
//    }


    @ExceptionHandler
    public String exception(HttpServletRequest request, HttpServletResponse response, Exception e) {

        //如果是json格式的ajax请求
        if (request.getHeader("accept").indexOf("application/json") > -1
                || (request.getHeader("X-Requested-With")!= null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)) {
            response.setStatus(500);
            response.setContentType("application/json;charset=utf-8");
            try {
                PrintWriter writer = response.getWriter();
                writer.write(e.getMessage());
                writer.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }
        else{//如果是普通请求
            request.setAttribute("exceptionMessage", e.getMessage());
            // 根据不同的异常类型可以返回不同界面
            if(e instanceof Exception){
                return "errorpage/500.html";
            }
            else{
                return "errorpage/404.html";
            }
        }
    }
}
