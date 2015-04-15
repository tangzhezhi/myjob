package org.tang.myjob.controller.websocket.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.tang.myjob.controller.websocket.Constants;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 功能描述:
 * 作者: LDL
 * 创建时间: 2014/8/18 15:40
 */

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    private static Logger logger = LoggerFactory.getLogger(HandshakeInterceptor.class);


    /**
     * websocket握手协议拦截器，通过对ServerHttpRequest的拦截，获得HttpSession，得到用户的相关信息，放入attributes中，
     * attributes就是WebSocketHandler的attributes
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object
                > attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                //使用userName区分WebSocketHandler，以便定向发送消息
                String userName = (String) session.getAttribute(Constants.SESSION_USERID);
                attributes.put(Constants.WEBSOCKET_USERNAME,userName);
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        logger.error("websocket握手出现异常:"+exception);
    }
}
