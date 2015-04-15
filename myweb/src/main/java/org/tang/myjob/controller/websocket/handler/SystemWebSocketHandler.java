/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tang.myjob.controller.websocket.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.tang.myjob.controller.websocket.Constants;
import org.tang.myjob.service.redis.RedisUtil;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author dayu
 */
@Component
public class SystemWebSocketHandler implements WebSocketHandler {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SystemWebSocketHandler.class.getName());

    @Autowired
    private RedisUtil redisUtil;

    private static final ArrayList<WebSocketSession> users;

    static {
        users = new ArrayList();
    }

//    @Autowired
//    private WebSocketService webSocketService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("connect to the websocket success......");
        users.add(session);
        String userName = (String) session.getAttributes().get(Constants.WEBSOCKET_USERNAME);
        if(userName!= null){
            //查询未读消息
//            int count = webSocketService.getUnReadNews((String) session.getAttributes().get(Constants.WEBSOCKET_USERNAME));
            session.sendMessage(new TextMessage("connect websocket success"));
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if(message instanceof  TextMessage){
            logger.info("收到文本消息::"+message.getPayload());
            this.redisPublishTextMessageOfUser(Constants.message_prefix+session.getAttributes().get(Constants.WEBSOCKET_USERNAME),(String)message.getPayload());
        }
        else{
            logger.info("收到其他类型消息::"+message.getPayload());
        }
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
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.debug("websocket connection closed......");
        users.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    /**
     * 发布用户消息
     * @param channel
     */
    private void redisPublishTextMessageOfUser(String channel,String message){
        try {
            Jedis jedis = redisUtil.getConnection();
            jedis.publish(channel,message);
            redisUtil.closeConnection(jedis);
        } catch (Exception e) {
            logger.error("redis发布消息时异常:"+e);
        }
    }
    
}
