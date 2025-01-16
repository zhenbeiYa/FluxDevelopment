package org.example.enmu;

public enum BusinessEnum {
    ESTIMATIONERROR("please reestimate treasure", 200);
    //错误信息
    private String message;
    //错误码
    private Integer code;

    BusinessEnum(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
