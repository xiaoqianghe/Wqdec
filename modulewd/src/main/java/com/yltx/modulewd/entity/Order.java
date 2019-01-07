package com.yltx.modulewd.entity;

import java.util.List;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/13.
 */

public class Order {
    private String epaymentIds;//还款idlist，还款时必填
    private String memberId;//会员ID，必填
    private String orderType;//订单类型，必填。deposit-充值，repayment-还款
    private String payType;//支付类型，必填wxPay,aliPay creditPay信用卡支付 depositPay储蓄卡支付

    private String remark;//备注，不必填
    private String infoId;//会员银行卡ID 绑定银行卡支付时，字段不能为空

    private String paymentPassword;//支付密码
    private String cvn2Code;//CVN2//安全码
    private List<String> repaymentIds;//还款ID LIST
    private List<String> borrowIds;//借款ID LIST

    public String getEpaymentIds() {
        return epaymentIds;
    }

    public void setEpaymentIds(String epaymentIds) {
        this.epaymentIds = epaymentIds;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

    public String getCvn2Code() {
        return cvn2Code;
    }

    public void setCvn2Code(String cvn2Code) {
        this.cvn2Code = cvn2Code;
    }

    public List<String> getRepaymentIds() {
        return repaymentIds;
    }

    public void setRepaymentIds(List<String> repaymentIds) {
        this.repaymentIds = repaymentIds;
    }

    public List<String> getBorrowIds() {
        return borrowIds;
    }

    public void setBorrowIds(List<String> borrowIds) {
        this.borrowIds = borrowIds;
    }
}
