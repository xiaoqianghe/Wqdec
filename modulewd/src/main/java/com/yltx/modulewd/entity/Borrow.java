package com.yltx.modulewd.entity;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/17.
 */

public class Borrow {
    String memberId;
    String borrowLimit;
    String borrowTerm;
    String firstRepaymentTime;
    String repaymentNum;
    String paymentPassword;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBorrowLimit() {
        return borrowLimit;
    }

    public void setBorrowLimit(String borrowLimit) {
        this.borrowLimit = borrowLimit;
    }

    public String getBorrowTerm() {
        return borrowTerm;
    }

    public void setBorrowTerm(String borrowTerm) {
        this.borrowTerm = borrowTerm;
    }

    public String getFirstRepaymentTime() {
        return firstRepaymentTime;
    }

    public void setFirstRepaymentTime(String firstRepaymentTime) {
        this.firstRepaymentTime = firstRepaymentTime;
    }

    public String getRepaymentNum() {
        return repaymentNum;
    }

    public void setRepaymentNum(String repaymentNum) {
        this.repaymentNum = repaymentNum;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }
}
