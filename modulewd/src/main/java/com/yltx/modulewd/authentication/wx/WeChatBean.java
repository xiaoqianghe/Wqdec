package com.yltx.modulewd.authentication.wx;

/**
 * 功能描述:
 * Created by ixzus on 2017/7/26.
 */

public class WeChatBean {

    /**
     * sign : DE0DDB620160268597586A33932B0DE2
     * timeStamp : 1501063636
     * packageStr : Sign=WXPay
     * partnerId : 1486234152
     * appid : wx10cffa2b72763370
     * nonceStr : xicv689mdtfuzq47ifex5bfa36jndjq2
     * prepayId : wx20170726180716a806fe03dc0310021679
     */

    private String sign;
    private String timeStamp;
    private String packageStr;
    private String partnerId;
    private String appid;
    private String nonceStr;
    private String prepayId;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
}
