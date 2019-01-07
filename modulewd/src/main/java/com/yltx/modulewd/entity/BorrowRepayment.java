package com.yltx.modulewd.entity;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/20.
 */

public class BorrowRepayment {


    /**
     * code : success
     * message : 调用成功
     * size : null
     * data : {"borrowId":null,"borrowNo":null,"memberId":null,"borrowLimit":null,"borrowTerm":null,"borrowFee":null,"overdueFine":null,"totalLimit":null,"surplusLimit":null,"status":null,"borrowDate":null,"reviewDate":null,"repaymentTime":null,"reviewRemark":null,"createDate":null,"createBy":null,"updateDate":null,"updateBy":null,"repaymentNum":null,"repaymentFinishDate":null,"totalStatus":null,"memberNo":null,"mbUserName":null,"boType":null,"startBorrowDate":null,"endBorrowDate":null,"startReviewDate":null,"endReviewDate":null,"startRepaymentTime":null,"endRepaymentTime":null,"identityNumber":null,"loanNo":null,"limitUsed":"123.0","byStages":"0","repayMethods":null,"answerTotalAmount":null,"alreadyTotalAmount":null,"repaymentSchedule":null,"overdue":null,"loLoanDate":null,"paymentPassword":null,"firstRepaymentTime":null,"arriveTime":null,"borrowDateStr":null,"repaymentDateStr":null,"repaymentDay":null,"loanServiceCharge":null,"bankCardNo":null,"bankCardNoEndNumber":null,"bankName":null,"totalStatusName":null,"overdueNum":null,"repayNum":null,"paidTotalAmount":null,"surplusTotalAmount":null,"overdueDays":null,"needToPay":null,"finishPayType":null,"overdueList":null,"toReBoNum":"1","surplusNum":null,"toRepayment":null,"borrowYearMonth":null,"totalCount":null,"expireMonthDay":null,"totalAmount":"123.0"}
     */

    private String code;
    private String message;
    private String size;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * borrowId : null
         * borrowNo : null
         * memberId : null
         * borrowLimit : null
         * borrowTerm : null
         * borrowFee : null
         * overdueFine : null
         * totalLimit : null
         * surplusLimit : null
         * status : null
         * borrowDate : null
         * reviewDate : null
         * repaymentTime : null
         * reviewRemark : null
         * createDate : null
         * createBy : null
         * updateDate : null
         * updateBy : null
         * repaymentNum : null
         * repaymentFinishDate : null
         * totalStatus : null
         * memberNo : null
         * mbUserName : null
         * boType : null
         * startBorrowDate : null
         * endBorrowDate : null
         * startReviewDate : null
         * endReviewDate : null
         * startRepaymentTime : null
         * endRepaymentTime : null
         * identityNumber : null
         * loanNo : null
         * limitUsed : 123.0
         * byStages : 0
         * repayMethods : null
         * answerTotalAmount : null
         * alreadyTotalAmount : null
         * repaymentSchedule : null
         * overdue : null
         * loLoanDate : null
         * paymentPassword : null
         * firstRepaymentTime : null
         * arriveTime : null
         * borrowDateStr : null
         * repaymentDateStr : null
         * repaymentDay : null
         * loanServiceCharge : null
         * bankCardNo : null
         * bankCardNoEndNumber : null
         * bankName : null
         * totalStatusName : null
         * overdueNum : null
         * repayNum : null
         * paidTotalAmount : null
         * surplusTotalAmount : null
         * overdueDays : null
         * needToPay : null
         * finishPayType : null
         * overdueList : null
         * toReBoNum : 1
         * surplusNum : null
         * toRepayment : null
         * borrowYearMonth : null
         * totalCount : null
         * expireMonthDay : null
         * totalAmount : 123.0
         */

        private String borrowId;
        private String borrowNo;
        private String memberId;
        private String borrowLimit;
        private String borrowTerm;
        private String borrowFee;
        private String overdueFine;
        private String totalLimit;
        private String surplusLimit;
        private String status;
        private String borrowDate;
        private String reviewDate;
        private String repaymentTime;
        private String reviewRemark;
        private String createDate;
        private String createBy;
        private String updateDate;
        private String updateBy;
        private String repaymentNum;
        private String repaymentFinishDate;
        private String totalStatus;
        private String memberNo;
        private String mbUserName;
        private String boType;
        private String startBorrowDate;
        private String endBorrowDate;
        private String startReviewDate;
        private String endReviewDate;
        private String startRepaymentTime;
        private String endRepaymentTime;
        private String identityNumber;
        private String loanNo;
        private String limitUsed;
        private String byStages;
        private String repayMethods;
        private String answerTotalAmount;
        private String alreadyTotalAmount;
        private String repaymentSchedule;
        private String overdue;
        private String loLoanDate;
        private String paymentPassword;
        private String firstRepaymentTime;
        private String arriveTime;
        private String borrowDateStr;
        private String repaymentDateStr;
        private String repaymentDay;
        private String loanServiceCharge;
        private String bankCardNo;
        private String bankCardNoEndNumber;
        private String bankName;
        private String totalStatusName;
        private String overdueNum;
        private String repayNum;
        private String paidTotalAmount;
        private String surplusTotalAmount;
        private String overdueDays;
        private String needToPay;
        private String finishPayType;
        private String overdueList;
        private String toReBoNum;
        private String surplusNum;
        private String toRepayment;
        private String borrowYearMonth;
        private String totalCount;
        private String expireMonthDay;
        private String totalAmount;

        public String getBorrowId() {
            return borrowId;
        }

        public void setBorrowId(String borrowId) {
            this.borrowId = borrowId;
        }

        public String getBorrowNo() {
            return borrowNo;
        }

        public void setBorrowNo(String borrowNo) {
            this.borrowNo = borrowNo;
        }

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

        public String getBorrowFee() {
            return borrowFee;
        }

        public void setBorrowFee(String borrowFee) {
            this.borrowFee = borrowFee;
        }

        public String getOverdueFine() {
            return overdueFine;
        }

        public void setOverdueFine(String overdueFine) {
            this.overdueFine = overdueFine;
        }

        public String getTotalLimit() {
            return totalLimit;
        }

        public void setTotalLimit(String totalLimit) {
            this.totalLimit = totalLimit;
        }

        public String getSurplusLimit() {
            return surplusLimit;
        }

        public void setSurplusLimit(String surplusLimit) {
            this.surplusLimit = surplusLimit;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBorrowDate() {
            return borrowDate;
        }

        public void setBorrowDate(String borrowDate) {
            this.borrowDate = borrowDate;
        }

        public String getReviewDate() {
            return reviewDate;
        }

        public void setReviewDate(String reviewDate) {
            this.reviewDate = reviewDate;
        }

        public String getRepaymentTime() {
            return repaymentTime;
        }

        public void setRepaymentTime(String repaymentTime) {
            this.repaymentTime = repaymentTime;
        }

        public String getReviewRemark() {
            return reviewRemark;
        }

        public void setReviewRemark(String reviewRemark) {
            this.reviewRemark = reviewRemark;
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

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getRepaymentNum() {
            return repaymentNum;
        }

        public void setRepaymentNum(String repaymentNum) {
            this.repaymentNum = repaymentNum;
        }

        public String getRepaymentFinishDate() {
            return repaymentFinishDate;
        }

        public void setRepaymentFinishDate(String repaymentFinishDate) {
            this.repaymentFinishDate = repaymentFinishDate;
        }

        public String getTotalStatus() {
            return totalStatus;
        }

        public void setTotalStatus(String totalStatus) {
            this.totalStatus = totalStatus;
        }

        public String getMemberNo() {
            return memberNo;
        }

        public void setMemberNo(String memberNo) {
            this.memberNo = memberNo;
        }

        public String getMbUserName() {
            return mbUserName;
        }

        public void setMbUserName(String mbUserName) {
            this.mbUserName = mbUserName;
        }

        public String getBoType() {
            return boType;
        }

        public void setBoType(String boType) {
            this.boType = boType;
        }

        public String getStartBorrowDate() {
            return startBorrowDate;
        }

        public void setStartBorrowDate(String startBorrowDate) {
            this.startBorrowDate = startBorrowDate;
        }

        public String getEndBorrowDate() {
            return endBorrowDate;
        }

        public void setEndBorrowDate(String endBorrowDate) {
            this.endBorrowDate = endBorrowDate;
        }

        public String getStartReviewDate() {
            return startReviewDate;
        }

        public void setStartReviewDate(String startReviewDate) {
            this.startReviewDate = startReviewDate;
        }

        public String getEndReviewDate() {
            return endReviewDate;
        }

        public void setEndReviewDate(String endReviewDate) {
            this.endReviewDate = endReviewDate;
        }

        public String getStartRepaymentTime() {
            return startRepaymentTime;
        }

        public void setStartRepaymentTime(String startRepaymentTime) {
            this.startRepaymentTime = startRepaymentTime;
        }

        public String getEndRepaymentTime() {
            return endRepaymentTime;
        }

        public void setEndRepaymentTime(String endRepaymentTime) {
            this.endRepaymentTime = endRepaymentTime;
        }

        public String getIdentityNumber() {
            return identityNumber;
        }

        public void setIdentityNumber(String identityNumber) {
            this.identityNumber = identityNumber;
        }

        public String getLoanNo() {
            return loanNo;
        }

        public void setLoanNo(String loanNo) {
            this.loanNo = loanNo;
        }

        public String getLimitUsed() {
            return limitUsed;
        }

        public void setLimitUsed(String limitUsed) {
            this.limitUsed = limitUsed;
        }

        public String getByStages() {
            return byStages;
        }

        public void setByStages(String byStages) {
            this.byStages = byStages;
        }

        public String getRepayMethods() {
            return repayMethods;
        }

        public void setRepayMethods(String repayMethods) {
            this.repayMethods = repayMethods;
        }

        public String getAnswerTotalAmount() {
            return answerTotalAmount;
        }

        public void setAnswerTotalAmount(String answerTotalAmount) {
            this.answerTotalAmount = answerTotalAmount;
        }

        public String getAlreadyTotalAmount() {
            return alreadyTotalAmount;
        }

        public void setAlreadyTotalAmount(String alreadyTotalAmount) {
            this.alreadyTotalAmount = alreadyTotalAmount;
        }

        public String getRepaymentSchedule() {
            return repaymentSchedule;
        }

        public void setRepaymentSchedule(String repaymentSchedule) {
            this.repaymentSchedule = repaymentSchedule;
        }

        public String getOverdue() {
            return overdue;
        }

        public void setOverdue(String overdue) {
            this.overdue = overdue;
        }

        public String getLoLoanDate() {
            return loLoanDate;
        }

        public void setLoLoanDate(String loLoanDate) {
            this.loLoanDate = loLoanDate;
        }

        public String getPaymentPassword() {
            return paymentPassword;
        }

        public void setPaymentPassword(String paymentPassword) {
            this.paymentPassword = paymentPassword;
        }

        public String getFirstRepaymentTime() {
            return firstRepaymentTime;
        }

        public void setFirstRepaymentTime(String firstRepaymentTime) {
            this.firstRepaymentTime = firstRepaymentTime;
        }

        public String getArriveTime() {
            return arriveTime;
        }

        public void setArriveTime(String arriveTime) {
            this.arriveTime = arriveTime;
        }

        public String getBorrowDateStr() {
            return borrowDateStr;
        }

        public void setBorrowDateStr(String borrowDateStr) {
            this.borrowDateStr = borrowDateStr;
        }

        public String getRepaymentDateStr() {
            return repaymentDateStr;
        }

        public void setRepaymentDateStr(String repaymentDateStr) {
            this.repaymentDateStr = repaymentDateStr;
        }

        public String getRepaymentDay() {
            return repaymentDay;
        }

        public void setRepaymentDay(String repaymentDay) {
            this.repaymentDay = repaymentDay;
        }

        public String getLoanServiceCharge() {
            return loanServiceCharge;
        }

        public void setLoanServiceCharge(String loanServiceCharge) {
            this.loanServiceCharge = loanServiceCharge;
        }

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }

        public String getBankCardNoEndNumber() {
            return bankCardNoEndNumber;
        }

        public void setBankCardNoEndNumber(String bankCardNoEndNumber) {
            this.bankCardNoEndNumber = bankCardNoEndNumber;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getTotalStatusName() {
            return totalStatusName;
        }

        public void setTotalStatusName(String totalStatusName) {
            this.totalStatusName = totalStatusName;
        }

        public String getOverdueNum() {
            return overdueNum;
        }

        public void setOverdueNum(String overdueNum) {
            this.overdueNum = overdueNum;
        }

        public String getRepayNum() {
            return repayNum;
        }

        public void setRepayNum(String repayNum) {
            this.repayNum = repayNum;
        }

        public String getPaidTotalAmount() {
            return paidTotalAmount;
        }

        public void setPaidTotalAmount(String paidTotalAmount) {
            this.paidTotalAmount = paidTotalAmount;
        }

        public String getSurplusTotalAmount() {
            return surplusTotalAmount;
        }

        public void setSurplusTotalAmount(String surplusTotalAmount) {
            this.surplusTotalAmount = surplusTotalAmount;
        }

        public String getOverdueDays() {
            return overdueDays;
        }

        public void setOverdueDays(String overdueDays) {
            this.overdueDays = overdueDays;
        }

        public String getNeedToPay() {
            return needToPay;
        }

        public void setNeedToPay(String needToPay) {
            this.needToPay = needToPay;
        }

        public String getFinishPayType() {
            return finishPayType;
        }

        public void setFinishPayType(String finishPayType) {
            this.finishPayType = finishPayType;
        }

        public String getOverdueList() {
            return overdueList;
        }

        public void setOverdueList(String overdueList) {
            this.overdueList = overdueList;
        }

        public String getToReBoNum() {
            return toReBoNum;
        }

        public void setToReBoNum(String toReBoNum) {
            this.toReBoNum = toReBoNum;
        }

        public String getSurplusNum() {
            return surplusNum;
        }

        public void setSurplusNum(String surplusNum) {
            this.surplusNum = surplusNum;
        }

        public String getToRepayment() {
            return toRepayment;
        }

        public void setToRepayment(String toRepayment) {
            this.toRepayment = toRepayment;
        }

        public String getBorrowYearMonth() {
            return borrowYearMonth;
        }

        public void setBorrowYearMonth(String borrowYearMonth) {
            this.borrowYearMonth = borrowYearMonth;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public String getExpireMonthDay() {
            return expireMonthDay;
        }

        public void setExpireMonthDay(String expireMonthDay) {
            this.expireMonthDay = expireMonthDay;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }
    }
}
