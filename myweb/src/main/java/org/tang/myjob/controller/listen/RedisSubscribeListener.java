package org.tang.myjob.controller.listen;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.TextMessage;
import org.tang.myjob.controller.websocket.handler.SystemWebSocketHandler;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by Administrator on 2015/4/15.
 */
public class RedisSubscribeListener extends JedisPubSub {
    private static Logger logger = Logger.getLogger(RedisSubscribeListener.class.getName());

    @Bean
    public SystemWebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }

    // 取得订阅的消息后的处理
    @Override
    public void onMessage(String channel, String message) {
        logger.info("channel::"+channel+":::message:"+message);
        systemWebSocketHandler().sendMessageToUser(channel, new TextMessage(message));
    }

    // 初始化订阅时候的处理
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        // System.out.println(channel + "=" + subscribedChannels);
    }

    // 取消订阅时候的处理
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        // System.out.println(channel + "=" + subscribedChannels);
    }

    // 初始化按表达式的方式订阅时候的处理
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        // System.out.println(pattern + "=" + subscribedChannels);
    }

    // 取消按表达式的方式订阅时候的处理
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        // System.out.println(pattern + "=" + subscribedChannels);
    }

    // 取得按表达式的方式订阅的消息后的处理
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println(pattern + "=" + channel + "=" + message);
    }
}
