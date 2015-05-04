package org.tang.myjob.controller.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2015/5/4.
 */
@Component
public class TimeOutInterceptor implements CallableProcessingInterceptor {
    public <T> void beforeConcurrentHandling(NativeWebRequest request,
                                             Callable<T> task) throws Exception {
        // TODO Auto-generated method stub

    }

    public <T> void preProcess(NativeWebRequest request, Callable<T> task)
            throws Exception {
        // TODO Auto-generated method stub

    }

    public <T> void postProcess(NativeWebRequest request, Callable<T> task,
                                Object concurrentResult) throws Exception {
        // TODO Auto-generated method stub

    }

    public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task)
            throws Exception {
        HttpServletResponse servletResponse = request.getNativeResponse(HttpServletResponse.class);
        if (!servletResponse.isCommitted()) {
            servletResponse.setContentType("text/plain;charset=utf-8");
            servletResponse.getWriter().write("超时了");
            servletResponse.getWriter().close();
        }
        return null;
    }

    public <T> void afterCompletion(NativeWebRequest request, Callable<T> task)
            throws Exception {
        // TODO Auto-generated method stub

    }
}
