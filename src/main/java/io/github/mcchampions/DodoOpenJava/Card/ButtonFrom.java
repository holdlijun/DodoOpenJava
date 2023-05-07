package io.github.mcchampions.DodoOpenJava.Card;

import io.github.mcchampions.DodoOpenJava.Card.enums.Color;
import org.json.JSONArray;
import org.json.JSONObject;

public class ButtonFrom {
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

    public Boolean initFrom(){
        if (jsonButton.isEmpty()){
            jsonButton = new JSONObject("""
                    {
                            "type": "button-group",
                            "elements": [
                              {
                                "type": "button",
                                "interactCustomId": "交互自定义id4",
                                "click": { "value": "", "action": "form" },
                                "color": "grey",
                                "name": "回传表单",
                                "form": {
                                  "title": "表单标题"
                                }
                              }
                            ]
                          }
                          """);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 修改自定义交互id
     * @param interactCustomId
     * @param elementsIdx
     * @return
     */
    public Boolean editInteractCustomId(String interactCustomId,Integer elementsIdx){
        if (jsonButton.isEmpty()) initFrom();
        if(jsonButton.getJSONArray("elements").length() <= elementsIdx+1){
            JSONObject object = jsonButton.getJSONArray("elements").getJSONObject(elementsIdx);
            object.put("interactCustomId",interactCustomId);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 修改按钮名称
     * @param name
     * @param elementsIdx
     * @return
     */
    public Boolean editName(String name,Integer elementsIdx){
        if (jsonButton.isEmpty()) initFrom();
        if(jsonButton.getJSONArray("elements").length() <= elementsIdx+1){
            JSONObject object = jsonButton.getJSONArray("elements").getJSONObject(elementsIdx);
            object.put("name",name);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     *
     * @param value
     * @param elementsIdx
     * @return
     */
    public Boolean editClick(String value,Integer elementsIdx){
        if (jsonButton.isEmpty()) initFrom();
        if(jsonButton.getJSONArray("elements").length() <= elementsIdx+1){
            JSONObject object = jsonButton.getJSONArray("elements").getJSONObject(elementsIdx).getJSONObject("click");
            object.put("value",value);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 修改颜色
     * @param color
     * @param elementsIdx
     * @return
     */
    public Boolean editColor(Color color, Integer elementsIdx){
        if (jsonButton.isEmpty()) initFrom();
        if(jsonButton.getJSONArray("elements").length() <= elementsIdx+1){
            JSONObject object = jsonButton.getJSONArray("elements").getJSONObject(elementsIdx);
            object.put("color",color.getType());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 修改tittle
     * @param fromTitle
     * @param elementsIdx
     * @return
     */
    public Boolean editFromTitle(String fromTitle, Integer elementsIdx){
        if (jsonButton.isEmpty()) initFrom();
        if(jsonButton.getJSONArray("elements").length() <= elementsIdx+1){
            JSONObject object = jsonButton.getJSONArray("elements").getJSONObject(elementsIdx).getJSONObject("form");
            object.put("title",fromTitle);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean addFromElements(ReturnElements ele, Integer elementsIdx){
        if (jsonButton.isEmpty()) initFrom();
        if (!jsonButton.getJSONArray("elements").getJSONObject(elementsIdx).getJSONObject("form").has("elements")){
            JSONArray array = new JSONArray();
            array.put(ele.toJsonObject());
            jsonButton.getJSONArray("elements").getJSONObject(elementsIdx).getJSONObject("form").put("elements",array);
        }else {
            jsonButton.getJSONArray("elements").getJSONObject(elementsIdx).getJSONObject("form").getJSONArray("elements").put(ele.toJsonObject());
        }
        return Boolean.TRUE;
    }

    public static void main(String[] args) {
        Card card = new Card();
        ButtonFrom buttonFrom = new ButtonFrom();
        buttonFrom.editInteractCustomId("平台收集",0);
        buttonFrom.editClick("信息收集",0);
        buttonFrom.editName("信息收集",0);
        ReturnElements re = new ReturnElements();
        re.editEle("h5_link","正确的官网链接。如：https://nftbwc.com",1,"https://.....",1,4000);
        buttonFrom.addFromElements(re,0);
        card.addButtonFrom(buttonFrom);
        System.out.println(card.toString());
    }
}
