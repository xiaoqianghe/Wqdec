package com.yltx.modulewd.entity;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/12.
 */

public class MsgByPhone {

    /**
     * code : success
     * message : 发送成功,请注意查收!
     * data : null
     */

    private String code;
    private String message;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
