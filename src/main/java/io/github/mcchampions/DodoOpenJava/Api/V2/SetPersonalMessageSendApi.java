package io.github.mcchampions.DodoOpenJava.Api.V2;

import io.github.mcchampions.DodoOpenJava.Card.Card;
import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

public class SetPersonalMessageSendApi {
    public static String url, param;
    public static JSONObject personalMessageSendCard(String authorization, String islandSourceId, String dodoSourceId, Integer messageType, Card card) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/personal/message/send";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"messageType\": \"" + messageType + "\"," +
                "    \"messageBody\": \"" + card.toJSONObject().toString() + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
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
