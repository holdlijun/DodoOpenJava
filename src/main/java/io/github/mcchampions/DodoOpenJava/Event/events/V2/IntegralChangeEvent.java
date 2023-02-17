package io.github.mcchampions.DodoOpenJava.Event.events.V2;

import io.github.mcchampions.DodoOpenJava.Event.Event;
import io.github.mcchampions.DodoOpenJava.Event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class IntegralChangeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

    public String islandSourceId;
    public String dodoSourceId;
    private Integer operateType;
    private String strOperateType;
    /**
     * 场景类型，1：签到，2：邀请，3：转账，4：购买商品，5：管理积分，6：退群
     */
    private Long integral;
    public JSONObject jsonObject;
    public String jsonString;
    public Integer timestamp;
    public String eventId;

    public IntegralChangeEvent(JSONObject json) {
        this.jsonObject = json;
        this.jsonString = json.toString();
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");
        this.operateType = json.getJSONObject("data").getJSONObject("eventBody").getInt("operateType");
        this.strOperateType = IntLeaveTypeToLeaveType(json.getJSONObject("data").getJSONObject("eventBody").getInt("operateType"));
        this.integral = json.getJSONObject("data").getJSONObject("eventBody").getLong("integral");

    }
    /**
     * 转换 为Int数据类型的 退出类型关键字 为 String 类型
     * @param Type 类型
     * @return 类型
     */
    public String IntLeaveTypeToLeaveType(Integer Type) {
        return switch (Type) {
            case 1 -> "签到";
            case 2 -> "邀请";
            case 3 -> "转账";
            case 4 -> "购买商品";
            case 5 -> "管理积分";
            case 6 -> "退群";
            default -> "未知";
        };
    }

    public String getIslandSourceId() {
        return islandSourceId;
    }

    public void setIslandSourceId(String islandSourceId) {
        this.islandSourceId = islandSourceId;
    }

    public String getDodoSourceId() {
        return dodoSourceId;
    }

    public void setDodoSourceId(String dodoSourceId) {
        this.dodoSourceId = dodoSourceId;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public String getStrOperateType() {
        return strOperateType;
    }

    public void setStrOperateType(String strOperateType) {
        this.strOperateType = strOperateType;
    }

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "IntegralChangeEvent{" +
                "islandSourceId='" + islandSourceId + '\'' +
                ", dodoSourceId='" + dodoSourceId + '\'' +
                ", operateType=" + operateType +
                ", strOperateType='" + strOperateType + '\'' +
                ", integral=" + integral +
                ", jsonObject=" + jsonObject +
                ", jsonString='" + jsonString + '\'' +
                ", timestamp=" + timestamp +
                ", eventId='" + eventId + '\'' +
                '}';
    }
}
