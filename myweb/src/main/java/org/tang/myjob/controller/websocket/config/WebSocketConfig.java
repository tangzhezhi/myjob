package org.tang.myjob.controller.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.tang.myjob.controller.websocket.hndler.SystemWebSocketHandler;
import org.tang.myjob.controller.websocket.interceptor.WebSocketHandshakeInterceptor;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements
        WebSocketConfigurer {

    public WebSocketConfig() {
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(systemWebSocketHandler(), "/websck").addInterceptors(new WebSocketHandshakeInterceptor());

        System.out.println("registed!");
        registry.addHandler(systemWebSocketHandler(), "/sockjs/websck/info").addInterceptors(new WebSocketHandshakeInterceptor())
                .withSockJS();

    }

    @Bean
    public WebSocketHandler systemWebSocketHandler() {
        //return new InfoSocketEndPoint();
        return new SystemWebSocketHandler();
    }

}