package com.yltx.modulewd.entity;

/**
 * Author：Wq
 * Date：2017/10/17 17:58
 * Description：//todo
 */

public class PostPwd {


    private String memberId;
    private String code;
    private String oldPassWord;
    private String newPassWord;
    private String newPassWord2;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOldPassWord() {
        return oldPassWord;
    }

    public void setOldPassWord(String oldPassWord) {
        this.oldPassWord = oldPassWord;
    }

    public String getNewPassWord() {
        return newPassWord;
    }

    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }

    public String getNewPassWord2() {
        return newPassWord2;
    }

    public void setNewPassWord2(String newPassWord2) {
        this.newPassWord2 = newPassWord2;
    }
}
