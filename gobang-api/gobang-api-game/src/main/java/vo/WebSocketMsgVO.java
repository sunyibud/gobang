package vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 自定义的websocket消息规范，用于传输给前端
 * @author Sunyi
 * @date 2023/4/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketMsgVO implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 自定义消息类型
     * 1:系统消息
     * 2:收到游戏邀请
     * 3:收到对方拒绝你的邀请
     * 4:收到对方同意你的邀请
     * 5:收到对方悔棋请求
     * 6:收到对方拒绝你的悔棋
     * 7:收到对方同意你的悔棋
     * 8:同步对方的下棋落子
     * 9:收到世界消息推送
     */
    private int type;

    /**
     * 消息的发送方用户id
     */
    private long sendUserId;

    /**
     * 消息的接收方用户id
     */
    private long toUserId;

    /**
     * 消息的内容
     */
    private String content;
}
