package com.asg.socketio.config;

import com.asg.socketio.listener.EventListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息推送配置类
 * @author miaozhiwei
 * @date 2021/03/13 18:07
 */
@Component
@Slf4j
public class MessagePushConfig implements InitializingBean {

    @Resource
    private EventListener eventListener;
    @Value("${push.server.host}")
    private String host;

    @Value("${push.server.port}")
    private int serverPort;

    @Autowired
    private SocketIOServer socketIOServer;

    @Override
    public void afterPropertiesSet() {
        socketIOServer.start();
        log.info("启动正常");
    }

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setPort(serverPort);

        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        config.setSocketConfig(socketConfig);
        config.setHostname(host);
        config.setOrigin("*");
        SocketIOServer server = new SocketIOServer(config);
        server.addListeners(eventListener);
        return server;
    }
}