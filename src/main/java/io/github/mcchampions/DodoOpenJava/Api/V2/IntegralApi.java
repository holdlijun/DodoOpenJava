package io.github.mcchampions.DodoOpenJava.Api.V2;

import io.github.mcchampions.DodoOpenJava.Utils.NetUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * ������API
 * @author qscbm187531
 */
public class IntegralApi {
    public static String url, param;



    /**
     * ��ѯ��Ա����
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
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
     * �����Ա����
     *
     * @param authorization authorization
     * @param islandSourceId Ⱥ��
     * @return ����JSON����
     * @throws IOException ��������ʧ�ܺ��׳�
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
