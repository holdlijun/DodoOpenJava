package io.github.mcchampions.DodoOpenJava.Event.events.V2;

import io.github.mcchampions.DodoOpenJava.Event.Event;
import io.github.mcchampions.DodoOpenJava.Event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class MemberInviteEvent extends Event {
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
    public String dodoIslandNickName;
    public String toDodoSourceId;
    //±ª—˚«Î»ÀDoDoID
    public String toDodoIslandNickName;

    public Integer timestamp;
    public String eventId;
    public MemberInviteEvent(JSONObject json) {
        System.out.println(json.toString());
        this.timestamp = json.getJSONObject("data").getInt("timestamp");
        this.eventId = json.getJSONObject("data").getString("eventId");
        this.islandSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId");
        this.dodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId");

        this.toDodoIslandNickName = json.getJSONObject("data").getJSONObject("eventBody").getString("toDodoIslandNickName");
        this.toDodoSourceId = json.getJSONObject("data").getJSONObject("eventBody").getString("toDodoSourceId");
        this.dodoIslandNickName = json.getJSONObject("data").getJSONObject("eventBody").getString("dodoIslandNickName");
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

    public String getDodoIslandNickName() {
        return dodoIslandNickName;
    }

    public void setDodoIslandNickName(String dodoIslandNickName) {
        this.dodoIslandNickName = dodoIslandNickName;
    }

    public String getToDodoSourceId() {
        return toDodoSourceId;
    }

    public void setToDodoSourceId(String toDodoSourceId) {
        this.toDodoSourceId = toDodoSourceId;
    }

    public String getToDodoIslandNickName() {
        return toDodoIslandNickName;
    }

    public void setToDodoIslandNickName(String toDodoIslandNickName) {
        this.toDodoIslandNickName = toDodoIslandNickName;
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
        return "MemberInviteEvent{" +
                "islandSourceId='" + islandSourceId + '\'' +
                ", dodoSourceId='" + dodoSourceId + '\'' +
                ", dodoIslandNickName='" + dodoIslandNickName + '\'' +
                ", toDodoSourceId='" + toDodoSourceId + '\'' +
                ", toDodoIslandNickName='" + toDodoIslandNickName + '\'' +
                ", timestamp=" + timestamp +
                ", eventId='" + eventId + '\'' +
                '}';
    }
}
