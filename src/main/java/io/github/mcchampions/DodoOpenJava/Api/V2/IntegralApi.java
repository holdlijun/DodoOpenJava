package io.github.mcchampions.DodoOpenJava.Api.V2;

import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 机器人API
 * @author qscbm187531
 */
public class IntegralApi {
    public static String url, param;



    /**
     * 查询成员积分
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject getIntegralInfo(String authorization, String islandSourceId,String dodoSourceId) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/integral/info";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }
    /**
     * 管理成员积分
     *
     * @param authorization authorization
     * @param islandSourceId 群号
     * @return 返回JSON对象
     * @throws IOException 发送请求失败后抛出
     */
    public static JSONObject setIntegralEdit(String authorization, String islandSourceId,String dodoSourceId,Integer operateType,Long integral) throws IOException {
        url = "https://botopen.imdodo.com/api/v2/integral/edit";
        param = "{" +
                "    \"islandSourceId\": \"" + islandSourceId + "\"," +
                "    \"dodoSourceId\": \"" + dodoSourceId + "\"," +
                "    \"operateType\": \"" + operateType + "\"," +
                "    \"integral\": \"" + integral + "\"" +
                "}";
        return new JSONObject(NetUtil.sendRequest(param, url, authorization));
    }

}
