package com.yltx.modulewd.entity;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/13.
 */

public class OrderResult {


    /**
     * code : success
     * message : 调用成功
     * data : {"orderId":null,"orderNo":null,"payNo":"48741395-7d68-404c-ac6f-5d73c961cc10","status":null,"payType":null,"infoId":null,"memberId":null,"orderType":null,"repaymentId":null,"totalAmount":"0.01","payTime":null,"outTradeNo":null,"remark":null,"isDel":null,"createDate":null,"createBy":null,"updateBy":null,"updateDate":null,"identityNumber":null,"aliPaySign":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017071407749559&biz_content=%7B%22body%22%3A%22%E5%B0%8F%E8%9C%9C%E7%99%BD%E5%8D%A1%E4%BC%9A%E5%91%98%E5%B9%B4%E8%B4%B9%E5%A5%97%E9%A4%90%E8%B4%B9%22%2C%22out_trade_no%22%3A%2248741395-7d68-404c-ac6f-5d73c961cc10%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%B0%8F%E8%9C%9C%E7%99%BD%E5%8D%A1%E4%BC%9A%E5%91%98%E5%B9%B4%E8%B4%B9%E5%A5%97%E9%A4%90%E8%B4%B9%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F14.21.42.99%3A15555%2Floan%2Fwx%2Findex%21notifyByAli.action&sign=hsKN0kNNu2dDSu8g%2Fb9qGHHWck77jbx0x0NxyJtqlueGGM4wiSiGZge5oHNLRmRECQb8%2FAULgq0HbEOWR1ORrr%2FHLQW41a9wqEMlRA1Wc%2FhrpQo%2BMrTOSrzo0c9JI%2FKc7AKMA6YhzHuzoUToaP5iBplc4Nc8lcceC1RIRKiNf3GAyI4qSqZQykZKgevZNMveAwXfMyIRXNRsjCnP7oc%2B6ucDZZ7DsJsVyDsn1SnsGZjM0epcj%2FCuKNjKoHDGDDdhVS56Q%2Fk2kqxfZYBiIM1Tc5HxHWr9RuKL%2BJmHxIOXMXWH5tzk8nkrPnDX1CdpL%2F74H0rsjFa2VfTrwFlr2y9chw%3D%3D&sign_type=RSA2&timestamp=2017-10-13+16%3A15%3A25&version=1.0","weixinRs":null}
     */

    private String code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orderId : null
         * orderNo : null
         * payNo : 48741395-7d68-404c-ac6f-5d73c961cc10
         * status : null
         * payType : null
         * infoId : null
         * memberId : null
         * orderType : null
         * repaymentId : null
         * totalAmount : 0.01
         * payTime : null
         * outTradeNo : null
         * remark : null
         * isDel : null
         * createDate : null
         * createBy : null
         * updateBy : null
         * updateDate : null
         * identityNumber : null
         * aliPaySign : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017071407749559&biz_content=%7B%22body%22%3A%22%E5%B0%8F%E8%9C%9C%E7%99%BD%E5%8D%A1%E4%BC%9A%E5%91%98%E5%B9%B4%E8%B4%B9%E5%A5%97%E9%A4%90%E8%B4%B9%22%2C%22out_trade_no%22%3A%2248741395-7d68-404c-ac6f-5d73c961cc10%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%B0%8F%E8%9C%9C%E7%99%BD%E5%8D%A1%E4%BC%9A%E5%91%98%E5%B9%B4%E8%B4%B9%E5%A5%97%E9%A4%90%E8%B4%B9%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F14.21.42.99%3A15555%2Floan%2Fwx%2Findex%21notifyByAli.action&sign=hsKN0kNNu2dDSu8g%2Fb9qGHHWck77jbx0x0NxyJtqlueGGM4wiSiGZge5oHNLRmRECQb8%2FAULgq0HbEOWR1ORrr%2FHLQW41a9wqEMlRA1Wc%2FhrpQo%2BMrTOSrzo0c9JI%2FKc7AKMA6YhzHuzoUToaP5iBplc4Nc8lcceC1RIRKiNf3GAyI4qSqZQykZKgevZNMveAwXfMyIRXNRsjCnP7oc%2B6ucDZZ7DsJsVyDsn1SnsGZjM0epcj%2FCuKNjKoHDGDDdhVS56Q%2Fk2kqxfZYBiIM1Tc5HxHWr9RuKL%2BJmHxIOXMXWH5tzk8nkrPnDX1CdpL%2F74H0rsjFa2VfTrwFlr2y9chw%3D%3D&sign_type=RSA2&timestamp=2017-10-13+16%3A15%3A25&version=1.0
         * weixinRs : null
         */

        private String orderId;
        private String orderNo;
        private String payNo;
        private String status;
        private String payType;
        private String infoId;
        private String memberId;
        private String orderType;
        private String repaymentId;
        private String totalAmount;
        private String payTime;
        private String outTradeNo;
        private String remark;
        private String isDel;
        private String createDate;
        private String createBy;
        private String updateBy;
        private String updateDate;
        private String identityNumber;
        private String aliPaySign;
        private String weixinRs;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getPayNo() {
            return payNo;
        }

        public void setPayNo(String payNo) {
            this.payNo = payNo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getInfoId() {
            return infoId;
        }

        public void setInfoId(String infoId) {
            this.infoId = infoId;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getRepaymentId() {
            return repaymentId;
        }

        public void setRepaymentId(String repaymentId) {
            this.repaymentId = repaymentId;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getIsDel() {
            return isDel;
        }

        public void setIsDel(String isDel) {
            this.isDel = isDel;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getIdentityNumber() {
            return identityNumber;
        }

        public void setIdentityNumber(String identityNumber) {
            this.identityNumber = identityNumber;
        }

        public String getAliPaySign() {
            return aliPaySign;
        }

        public void setAliPaySign(String aliPaySign) {
            this.aliPaySign = aliPaySign;
        }

        public String getWeixinRs() {
            return weixinRs;
        }

        public void setWeixinRs(String weixinRs) {
            this.weixinRs = weixinRs;
        }
    }
}
