package io.github.mcchampions.DodoOpenJava.Event.events.V2;

import org.json.JSONObject;
import io.github.mcchampions.DodoOpenJava.Event.Event;
import io.github.mcchampions.DodoOpenJava.Event.HandlerList;

import javax.annotation.Nonnull;

/**
 * ��Ա�����¼�
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
            case 0 -> "Ů";
            case 1 -> "��";
            default -> "����";
        };
    }
    /**
     * ��ȡʱ���
     * @return ����ʱ���
     */
    public Integer getTimestamp() {
        return this.timestamp;
    }

    /**
     * ��ȡ�¼�ID
     * @return �¼�ID
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * ��ȡȺ��
     * @return Ⱥ��
     */
    public String getIslandSourceId() {
        return this.islandSourceId;
    }

    /**
     * ��ȡDodoSourceId
     * @return DodoSourceId
     */
    public String getDodoSourceId() {
        return this.dodoSourceId;
    }

    /**
     * ��ȡ�䶯ʱ��
     * @return �䶯ʱ��
     */
    public String getModifyTime() {
        return this.modifyTime;
    }

    /**
     * ��ȡJSONObject
     * @return JSONObject
     */
    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    /**
     * ��ȡJsonString
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
