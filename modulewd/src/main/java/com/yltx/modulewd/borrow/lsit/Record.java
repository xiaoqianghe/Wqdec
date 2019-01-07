package com.yltx.modulewd.borrow.lsit;

/**
 * 功能描述:
 * Created by ixzus on 2017/9/28.
 */

public class Record {
    String num;
    String uuid;
    String id;
    String titleMoney;
    String subMoney;
    String time;
    String stage;
    String status;
    String overdue;
    boolean isCheck;


    public Record(String num,String uuid, String id, String titleMoney, String subMoney, String time, String stage, String status) {
        this.num = num;
        this.uuid = uuid;
        this.id = id;
        this.titleMoney = titleMoney;
        this.subMoney = subMoney;
        this.time = time;
        this.stage = stage;
        this.status = status;
    }

    public Record(String id, String titleMoney, String subMoney, String time, String stage, String status,String overdue) {
        this.id = id;
        this.titleMoney = titleMoney;
        this.subMoney = subMoney;
        this.time = time;
        this.stage = stage;
        this.status = status;
        this.overdue = overdue;
    }

    public String getOverdue() {
        return overdue;
    }

    public void setOverdue(String overdue) {
        this.overdue = overdue;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleMoney() {
        return titleMoney;
    }

    public void setTitleMoney(String titleMoney) {
        this.titleMoney = titleMoney;
    }

    public String getSubMoney() {
        return subMoney;
    }

    public void setSubMoney(String subMoney) {
        this.subMoney = subMoney;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
