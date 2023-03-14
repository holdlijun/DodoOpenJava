package io.github.mcchampions.DodoOpenJava.Api.V2;

import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

public class SetPersonalMessageSendApi {
    public static String url, param;
    public static JSONObject personalMessageSendCard(String authorization, String islandSourceId, String dodoSourceId, Integer messageType, String msg) throws IOException {
        JSONObject content = new JSONObject();
        content.put("content",msg);
        JSONObject paramJson = new JSONObject();
        paramJson.put("islandSourceId",islandSourceId);
        paramJson.put("dodoSourceId",dodoSourceId);
        paramJson.put("messageType",messageType);
        paramJson.put("messageBody",content);

        url = "https://botopen.imdodo.com/api/v2/personal/message/send";

        return new JSONObject(NetUtil.sendRequest(paramJson.toString(), url, authorization));
    }

    public static JSONObject personalMessageSendMessage(String authorization, String islandSourceId, String dodoSourceId, Integer messageType, String message) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/personal/message/send";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"messageType\": \"" + messageType + "\"," +
                "    \"messageBody\": \"" + message + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
}
