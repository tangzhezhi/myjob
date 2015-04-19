package org.tang.myjob.controller.websocket.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.tang.myjob.controller.websocket.Constants;
import org.tang.myjob.service.redis.RedisUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/4/18.
 */
public class MyHandler extends TextWebSocketHandler {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MyHandler.class.getName());

    @Autowired
    private RedisUtil redisUtil;

    private static final ArrayList<WebSocketSession> users;

    static {
        users = new ArrayList();
    }



    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println("connected:");

        logger.debug("connect to the websocket success......");
        users.add(session);
        String userName = (String) session.getAttributes().get(Constants.WEBSOCKET_USERNAME);
        if(userName!= null){
            //查询未读消息
            session.sendMessage(new TextMessage("connect websocket success"));
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println("closed:");
        logger.debug("websocket connection closed......");
        users.remove(session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        logger.debug("websocket connection closed......");
        users.remove(session);
    }

    @Override
    public  void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        TextMessage msg = new TextMessage("Hello, " + message.getPayload() + "!");
        session.sendMessage(msg);
        System.out.println("message:"+message.getPayload());
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get(Constants.WEBSOCKET_USERNAME).equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
