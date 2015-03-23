package org.tang.myjob.controller.exception;

import org.apache.log4j.Logger;
import org.springframework.util.ErrorHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.tang.myjob.service.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/3/18.
 */
public class MyErrorHandler implements HandlerExceptionResolver {
    private Logger logger  = Logger.getLogger("MyErrorHandler");

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.error("发生异常:"+e);
        //添加自己的异常处理逻辑，如日志记录等
        // 根据不同的错误跳转到不同的页面
        if (e instanceof BusinessException) {
            return new ModelAndView("errorpage/500");
        } else if (e instanceof Exception) {
            return new ModelAndView("errorpage/404");
        } else {
            return new ModelAndView("errorpage/405");
        }
    }
}
