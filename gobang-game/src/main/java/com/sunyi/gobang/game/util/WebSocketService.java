package com.sunyi.gobang.game.util;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import vo.WebSocketMsgVO;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Sunyi
 * @date 2023/4/10
 */
@Slf4j
@ServerEndpoint("/ws/{userId}")
@Component //配合ServerEndpoint才能被Spring扫描到并注册为WebSocket服务端点
public class WebSocketService
{
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static final ConcurrentHashMap<Long, WebSocketService> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 当前连接用户userId
     */
    private Long userId;

    /**
     * websocket客户端建立连接
     * @param session session
     * @param userId 连接参数中的用户id
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Long userId)
    {
        this.session = session;
        this.userId = userId;
        WebSocketService.webSocketMapPush(userId, this);
        log.info("用户" + userId + "(id)连接成功,当前在线人数为:" + getOnlineCount());
        sendMessage(new WebSocketMsgVO(1, 0, userId, "websocket连接成功"));
    }

    /**
     * websocket客户端断开连接
     */
    @OnClose
    public void onClose()
    {
        WebSocketService.webSocketMapRemove(userId);
        log.info("用户" + userId + "(id)退出,当前在线人数为:" + getOnlineCount());
    }

    /**
     * websocket客户端通过ws.send()向服务端发送消息时，此方法用于解析该消息
     * @param message 消息
     * @return 服务器是否接收了该消息
     */
    @OnMessage
    public boolean onMessage(String message)
    {
        log.info("用户" + userId + "(id)向服务端发送消息,内容:" + message);
        //消息保存到数据库、redis
        if (StringUtils.isNotBlank(message))
            return true;
        log.info("onMessage接受到的消息json字符内容为空");
        return false;
    }

    /**
     * 服务端主动向客户端当前session连接用户发送消息
     * @param message 自定义websocket消息类型
     */
    public void sendMessage(WebSocketMsgVO message)
    {
        this.session.getAsyncRemote().sendText(JSONUtil.toJsonStr(message));
    }

    /**
     * 服务端通过该方法给websocket某一个在线用户推送消息
     * @param toUserId 消息接受用户的id
     * @param msg 自定义的websocket消息规范
     * @return 消息发送是否成功
     */
    public boolean sendMessageToUserByUserId(Long toUserId, WebSocketMsgVO msg)
    {
        msg.setToUserId(toUserId);
        if(webSocketMap.containsKey(toUserId))
        {
            webSocketMap.get(toUserId).sendMessage(msg);
            return true;
        }
        return false;
    }

    /**
     * 服务端通过该方法给websocket所有在线用户群发消息
     * @return 消息发送成功
     */
    public boolean sendMessageToAllUser(WebSocketMsgVO msg)
    {
        if(msg.getType()!=9)
        {
            log.info("该消息不是群发消息类型，无法发送");
            return false;
        }
        log.info("用户" + this.userId + "群发消息:" + "内容:" + msg);
        WebSocketService.webSocketMap.forEach((userId, ws)->{
            ws.sendMessage(msg);
        });
        return true;
    }

    /**
     * 确保线程安全的操作静态变量
     */
    private static synchronized int getOnlineCount()
    {
        return WebSocketService.onlineCount;
    }
    private static synchronized void webSocketMapRemove(long userId)
    {
        WebSocketService.webSocketMap.remove(userId);
        WebSocketService.onlineCount--;
    }
    private static synchronized void webSocketMapPush(long userId, WebSocketService ws)
    {
        WebSocketService.webSocketMap.put(userId, ws);
        WebSocketService.onlineCount++;
    }
    private static synchronized boolean webSocketMapContains(long userId)
    {
        return webSocketMap.containsKey(userId);
    }

}
