package com.asg.socketio.listener;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author miaozhiwei
 * @date 2021/03/13 18:05
 */
@Component
public class EventListener {

//    @Resource
//    private ClientCache clientCache;

    /**
     * 客户端连接
     *
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        System.out.println("建立连接");

        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        UUID sessionId = client.getSessionId();
//        clientCache.saveClient(userId, sessionId, client);

    }

    /**
     * 客户端断开
     *
     * @param client
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("关闭连接");
        String userId = client.getHandshakeData().getSingleUrlParam("userId");
//        clientCache.deleteSessionClient(userId, client.getSessionId());

    }

    //消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息
    // 暂未使用
    @OnEvent("messageevent")
    public void onEvent(SocketIOClient client, AckRequest request) {
        System.out.println("测试");
    }
}