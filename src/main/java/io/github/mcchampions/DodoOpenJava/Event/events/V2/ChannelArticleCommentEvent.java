package io.github.mcchampions.DodoOpenJava.Event.events.V2;

import io.github.mcchampions.DodoOpenJava.Event.Event;
import io.github.mcchampions.DodoOpenJava.Event.HandlerList;
import io.github.mcchampions.DodoOpenJava.Utils.BaseUtil;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * 帖子评论回复事件
 * @author qscbm187531
 */
public class ChannelArticleCommentEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    @Override
    @Nonnull
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public Integer timestamp;

    public String eventId;

    public String islandSourceId;

    public String dodoSourceId;

    public String channelId;

    public JSONObject jsonObject;

    public String jsonString;

    public JSONObject personal;

    public String userNickName;

    public String userAvatarUrl;

    public Integer userIntSex;

    public String userSex;

    public JSONObject member;

    public String memberNickName;

    public String memberJoinTime;

    public String articleId;

    public String content;

    public List<String> imageList;

    public String commentId;

    public String replyId;

    public ChannelArticleCommentEvent(JSONObject json) {

        this.replyId = json.getJSONObject("data").getJSONObject("eventBody").has("replyId") ?
                json.getJSONObject("data").getJSONObject("eventBody").getString("replyId") : null;
        this.commentId = json.getJSONObject("data").getJSONObject("eventBody").getString("commentId");
        this.articleId = json.getJSONObject("data").getJSONObject("eventBody").getString("articleId");
        this.content = json.getJSONObject("data").getJSONObject("eventBody").has("content")
                ? json.getJSONObject("data").getJSONObject("eventBody").getString("content") :null;
        this.imageList = json.getJSONObject("data").getJSONObject("eventBody").has("imageList") ?
                BaseUtil.toStringList(json.getJSONObject("data").getJSONObject("eventBody").getJSONArray("imageList").toList()) : new ArrayList<>();
        this.jsonObject = json;
        this.channelId = json.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.member = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member");
        this.memberJoinTime = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("joinTime");
        this.memberNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("member").getString("nickName");
        this.personal = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal");
        this.userNickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.userAvatarUrl = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl");
        this.userSex = IntSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
        this.userIntSex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");
    }

    /**
     * 转换 为Int数据类型的 性别关键字 为 String 类型
     * @param IntSex 性别
     * @return 性别
     */
    public String IntSexToSex(Integer IntSex) {
        return switch (IntSex) {
            case 0 -> "女";
            case 1 -> "男";
            default -> "保密";
        };
    }

    /**
     * 获取时间戳
     * @return 时间戳
     */
    public Integer getTimestamp() {
        return this.timestamp;
    }

    /**
     * 获取事件ID
     * @return 事件ID
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * 获取群号
     * @return 群号
     */
    public String getIslandSourceId() {
        return this.islandSourceId;
    }

    /**
     * 获取频道ID
     * @return 频道ID
     */
    public String getChannelId() {
        return this.channelId;
    }

    /**
     * 获取DodoSourceId
     * @return DodoSourceId
     */
    public String getDodoSourceId() {
        return this.dodoSourceId;
    }


    /**
     * 获取成员Object
     * @return 获取成员的 JsonObject
     */
    public JSONObject getPersonal() {
        return this.personal;
    }


    /**
     * 获取发送者名字
     * @return 名字
     */
    public String getUserNickName() {
        return this.userNickName;
    }

    /**
     * 获取发送者头像URL
     * @return 头像url
     */
    public String getUserAvatarUrl() {
        return this.userAvatarUrl;
    }

    /**
     * 获取性别（Int类型）
     * @return 性别
     */
    public Integer getUserIntSex() {
        return this.userIntSex;
    }

    /**
     * 获取性别（String类型）
     * @return 性别
     */
    public String getUserSex() {
        return this.userSex;
    }


    /**
     * 获取成员Object
     * @return 成员 JsonObject
     */
    public JSONObject getMember() {
        return this.member;
    }

    /**
     * 获取成员显示名
     * @return 名字
     */
    public String getMemberNickName() {
        return this.memberNickName;
    }

    /**
     * 获取成员加入时间
     * @return 加入时间
     */
    public String getMemberJoinTime() {
        return this.memberJoinTime;
    }

    /**
     * 获取卡片消息JSON字符串
     */
    public String getJsonString() {
        return this.jsonString;
    }

    /**
     * 获取卡片消息JSON对象
     */
    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    /**
     * 获取内容
     * @return 内容
     */
    public String getContent() {
        return this.content;
    }


    /**
     * 获取出现的图片URL地址
     * @return 集合
     */
    public List<String> getImageList() {
        return this.imageList;
    }

    /**
     * 获取帖子ID
     * @return ID
     */
    public String getArticleId() {
        return this.articleId;
    }

    /**
     * 获取评论iD
     * @return ID
     */
    public String getCommentId() {
        return this.commentId;
    }

    /**
     * 帖子评论的回复ID，为空时：为评论事件，不为空时：为评论回复事件，可以自己做个判断
     * @return ID
     */
    public String getReplyId() {
        return this.replyId;
    }
}
