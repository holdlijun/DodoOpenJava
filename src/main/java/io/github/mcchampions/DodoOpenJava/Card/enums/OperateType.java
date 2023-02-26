package io.github.mcchampions.DodoOpenJava.Card.enums;

public enum OperateType {
    ADD (1,"发放积分"),
    SUB (2,"扣除积分");

    OperateType(){

    };

    OperateType(Integer type,String typeName){
        this.type = type;
        this.typeName = type;
    };
    private Integer type;

    public Integer getTypeName() {
        return typeName;
    }

    public void setTypeName(Integer typeName) {
        this.typeName = typeName;
    }

    private Integer typeName;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
