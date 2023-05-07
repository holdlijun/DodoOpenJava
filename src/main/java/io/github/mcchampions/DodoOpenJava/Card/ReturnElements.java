package io.github.mcchampions.DodoOpenJava.Card;

import org.json.JSONObject;

public class ReturnElements {
    JSONObject jsonButton = new JSONObject();
    /**
     * 是否不存在
     * @return true/false
     */
    public Boolean isEmpty() {
        return jsonButton.isEmpty();
    }

    /**
     * 转换为JSON对象
     * @return true
     */
    public JSONObject toJsonObject() {
        return jsonButton;
    }

    public Boolean initElements(){
        if (jsonButton.isEmpty()){
            jsonButton = new JSONObject("""
                    {
                                      "type": "input",
                                      "key": "选项自定义id1",
                                      "title": "正确的官网链接。如：https://nftbwc.com",
                                      "rows": 1,
                                      "placeholder": "https://.....",
                                      "minChar": 1,
                                      "maxChar": 4000
                                    }
                    """);
            return true;

        }else {
            return Boolean.FALSE;
        }
    }

    public Boolean editEle(String key,String title,Integer row,String placeholder,Integer minChar,Integer maxChar){
        if (jsonButton.isEmpty()) initElements();
        jsonButton.put("key",key);jsonButton.put("title",title);
        jsonButton.put("rows",row);jsonButton.put("placeholder",placeholder);
        jsonButton.put("minChar",minChar);jsonButton.put("maxChar",maxChar);
        return Boolean.TRUE;
    }
}
