package org.tang.myjob.controller.Interceptor;

/**
 * Created by Administrator on 2015/3/25.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.PrintWriter;
import java.util.Set;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    public static final String SESSION_USERID = "tangUSERID";
    public static final String SESSION_AUTHS = "tangAUTHS";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            Auth authPassport = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);

            //没有声明需要权限,或者声明不验证权限
            if(authPassport == null || authPassport.validate() == false)
                return true;
            else{
                //在这里实现自己的权限验证逻辑
                if (request.getSession().getAttribute(SESSION_USERID) != null) {//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.setContentType("application/json;charset=utf-8");
                    //返回到登录界面
                    response.sendRedirect("../welcome.html");
//                    PrintWriter out=response.getWriter();
//                    out.write("{\"type\":\"nosignin\",\"msg\":\"请您先登录!\"}");
//                    out.flush();
//                    out.close();
                    return false;
                }
                else//如果验证失败
                {

                    if (!"".equals(authPassport.value())) {
                        Set<String> auths = (Set<String>) request.getSession().getAttribute(SESSION_AUTHS);
                        if(auths!=null){
                            if (!auths.contains(authPassport.value())) {// 提示用户没权限
                                response.setStatus(HttpStatus.FORBIDDEN.value());
                                response.setContentType("application/json;charset=utf-8");
                                PrintWriter out=response.getWriter();
                                out.write("{\"type\":\"noauth\",\"msg\":\"您没有"+authPassport.value() + "权限!\"}");
                                out.flush();
                                out.close();
                                return false;
                            }
                        }
                        else{
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.setContentType("application/json;charset=utf-8");
                            PrintWriter out=response.getWriter();
                            out.write("{\"type\":\"noauth\",\"msg\":\"您没有"+authPassport.value() + "权限!\"}");
                            out.flush();
                            out.close();
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        else
            return true;
    }
}
