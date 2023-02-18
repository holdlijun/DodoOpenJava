package io.github.mcchampions.DodoOpenJava.Event.events.V2;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Event.Event;
import io.github.mcchampions.DodoOpenJava.Event.HandlerList;

import javax.annotation.Nonnull;

/**
 * 成员加入事件
 * @author qscbm187531
 */
public class MemberJoinEvent extends Event {
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

    public String modifyTime;

    private String nickName;
    private String avatarUrl;
    private Integer sex;
    public String senderSex;

    public JSONObject jsonObject;

    public String jsonString;

    public MemberJoinEvent(JSONObject json) {
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.modifyTime = json.getJSONObject("data").getJSONObject("eventBody").getString("modifyTime");

        this.nickName = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("nickName");
        this.avatarUrl = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").has("avatarUrl") ?
                json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getString("avatarUrl") : null;
        this.sex = json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex");
        this.senderSex = IntSexToSex(json.getJSONObject("data").getJSONObject("eventBody").getJSONObject("personal").getInt("sex"));
    }

    public String IntSexToSex(Integer IntSex) {
        return switch (IntSex) {
            case 0 -> "女";
            case 1 -> "男";
            default -> "保密";
        };
    }
    /**
     * 获取时间戳
     * @return 返回时间戳
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
     * 获取DodoSourceId
     * @return DodoSourceId
     */
    public String getDodoSourceId() {
        return this.dodoSourceId;
    }

    /**
     * 获取变动时间
     * @return 变动时间
     */
    public String getModifyTime() {
        return this.modifyTime;
    }

    /**
     * 获取JSONObject
     * @return JSONObject
     */
    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    /**
     * 获取JsonString
     * @return String
     */
    public String getJsonString() {
        return this.jsonString;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "MemberJoinEvent{" +
                "timestamp=" + timestamp +
                ", eventId='" + eventId + '\'' +
                ", islandSourceId='" + islandSourceId + '\'' +
                ", dodoSourceId='" + dodoSourceId + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", sex=" + sex +
                ", senderSex='" + senderSex + '\'' +
                '}';
    }
}
