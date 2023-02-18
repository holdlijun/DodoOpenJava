package io.github.mcchampions.DodoOpenJava.CustomException;

public class EventException extends Exception{

    public EventException(){

    }
    private String massage;
    private Integer code;
    public EventException(Integer code, String massage){
        super(massage);
        this.massage = massage;
        this.code = code;
    }
    public EventException( String massage){
        super(massage);
        this.massage = massage;
    };


    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    @Override
    public String getMessage() {
        return this.massage;
    }

}
