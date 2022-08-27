package io.github.mcchampions.DodoOpenJava.Utils;

import com.alibaba.fastjson.JSONObject;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * 有关于 JSON 的相关实用性方法
 */
public class JsonUtil {
    /**
     * 转化字符串为json
     *
     * @param string 字符串
     * @return JSONObject
     */
    public static JSONObject toJson(String string) {
        return com.alibaba.fastjson.JSON.parseObject(string);
    }

    /**
     * 转化Bson为json
     *
     * @param bson Bson
     * @return JSONObject
     */
    public static JSONObject bsonToJson(Bson bson) {
        return com.alibaba.fastjson.JSON.parseObject(bson.toBsonDocument().toJson());
    }

    /**
     * 转化Document为json
     *
     * @param document Document
     * @return JSONObject
     */
    public static JSONObject documentToJson(Document document) {
        return com.alibaba.fastjson.JSON.parseObject(document.toJson());
    }

    /**
     * json转化为字符串
     *
     * @param obj obj
     * @return json
     */
    public static String toString(JSONObject obj) {
        return obj.toString();
    }
}
