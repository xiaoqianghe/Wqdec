package com.yltx.modulewd.entity;

/**
 * Author：Wq
 * Date：2017/10/20 14:07
 * Description：//todo
 */

public class LoanDetailRsBean {
    /**
     * code : success
     * message : 调用成功
     * data : {"borrowId":"1352","borrowNo":"BO201710200003","memberId":"4","borrowLimit":"100.0","borrowTerm":"1个月","borrowFee":"1.0","overdueFine":null,"totalLimit":null,"surplusLimit":null,"status":"4","borrowDate":"2017-10-20 13:53:17","reviewDate":"2017-10-20 13:53:17","repaymentTime":null,"reviewRemark":"系统自动审核","createDate":"2017-10-20 13:53:17","createBy":"何伟强","updateDate":null,"updateBy":null,"repaymentNum":"1","repaymentFinishDate":null,"totalStatus":"2","memberNo":null,"mbUserName":null,"boType":null,"startBorrowDate":null,"endBorrowDate":null,"startReviewDate":null,"endReviewDate":null,"startRepaymentTime":null,"endRepaymentTime":null,"identityNumber":null,"loanNo":null,"limitUsed":null,"byStages":"0","repayMethods":null,"answerTotalAmount":null,"alreadyTotalAmount":null,"repaymentSchedule":null,"overdue":null,"loLoanDate":null,"paymentPassword":null,"firstRepaymentTime":null,"arriveTime":"2017-10-20 16:02:54","toReBoNum":null,"surplusNum":null,"toRepayment":null,"borrowYearMonth":null,"totalCount":null}
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
         * borrowId : 1352
         * borrowNo : BO201710200003
         * memberId : 4
         * borrowLimit : 100.0
         * borrowTerm : 1个月
         * borrowFee : 1.0
         * overdueFine : null
         * totalLimit : null
         * surplusLimit : null
         * status : 4
         * borrowDate : 2017-10-20 13:53:17
         * reviewDate : 2017-10-20 13:53:17
         * repaymentTime : null
         * reviewRemark : 系统自动审核
         * createDate : 2017-10-20 13:53:17
         * createBy : 何伟强
         * updateDate : null
         * updateBy : null
         * repaymentNum : 1
         * repaymentFinishDate : null
         * totalStatus : 2
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
         * limitUsed : null
         * byStages : 0
         * repayMethods : null
         * answerTotalAmount : null
         * alreadyTotalAmount : null
         * repaymentSchedule : null
         * overdue : null
         * loLoanDate : null
         * paymentPassword : null
         * firstRepaymentTime : null
         * arriveTime : 2017-10-20 16:02:54
         * toReBoNum : null
         * surplusNum : null
         * toRepayment : null
         * borrowYearMonth : null
         * totalCount : null
         */

        private String borrowId;
        private String borrowNo;
        private String memberId;
        private String borrowLimit;
        private String borrowTerm;
        private String borrowFee;
        private String overdueFine;
        private Object totalLimit;
        private Object surplusLimit;
        private String status;
        private String borrowDate;
        private String reviewDate;
        private String repaymentTime;
        private String reviewRemark;
        private String createDate;
        private String createBy;
        private Object updateDate;
        private Object updateBy;
        private String repaymentNum;
        private Object repaymentFinishDate;
        private String totalStatus;
        private Object memberNo;
        private Object mbUserName;
        private Object boType;
        private Object startBorrowDate;
        private Object endBorrowDate;
        private Object startReviewDate;
        private Object endReviewDate;
        private Object startRepaymentTime;
        private Object endRepaymentTime;
        private Object identityNumber;
        private Object loanNo;
        private Object limitUsed;
        private String byStages;
        private Object repayMethods;
        private Object answerTotalAmount;
        private Object alreadyTotalAmount;
        private Object repaymentSchedule;
        private Object overdue;
        private Object loLoanDate;
        private Object paymentPassword;
        private Object firstRepaymentTime;
        private String arriveTime;
        private Object toReBoNum;
        private Object surplusNum;
        private Object toRepayment;
        private Object borrowYearMonth;
        private Object totalCount;


        private String totalStatusName;

        private String loanServiceCharge;

        private String bankName;
        private String bankCardNoEndNumber;

        private String borrowDateStr;

        private String repaymentDateStr;

        private String repaymentDay;



        private String overdueNum;       //逾期期数 2,3 格式
        private String repayNum;         //已还款期数
        private String paidTotalAmount;  //已还款总额
        private String surplusTotalAmount;//剩余还款总额
        private String overdueDays;      //逾期天数
        private String needToPay;         //待还款金额




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

        public Object getTotalLimit() {
            return totalLimit;
        }

        public void setTotalLimit(Object totalLimit) {
            this.totalLimit = totalLimit;
        }

        public Object getSurplusLimit() {
            return surplusLimit;
        }

        public void setSurplusLimit(Object surplusLimit) {
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

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public String getRepaymentNum() {
            return repaymentNum;
        }

        public void setRepaymentNum(String repaymentNum) {
            this.repaymentNum = repaymentNum;
        }

        public Object getRepaymentFinishDate() {
            return repaymentFinishDate;
        }

        public void setRepaymentFinishDate(Object repaymentFinishDate) {
            this.repaymentFinishDate = repaymentFinishDate;
        }

        public String getTotalStatus() {
            return totalStatus;
        }

        public void setTotalStatus(String totalStatus) {
            this.totalStatus = totalStatus;
        }

        public Object getMemberNo() {
            return memberNo;
        }

        public void setMemberNo(Object memberNo) {
            this.memberNo = memberNo;
        }

        public Object getMbUserName() {
            return mbUserName;
        }

        public void setMbUserName(Object mbUserName) {
            this.mbUserName = mbUserName;
        }

        public Object getBoType() {
            return boType;
        }

        public void setBoType(Object boType) {
            this.boType = boType;
        }

        public Object getStartBorrowDate() {
            return startBorrowDate;
        }

        public void setStartBorrowDate(Object startBorrowDate) {
            this.startBorrowDate = startBorrowDate;
        }

        public Object getEndBorrowDate() {
            return endBorrowDate;
        }

        public void setEndBorrowDate(Object endBorrowDate) {
            this.endBorrowDate = endBorrowDate;
        }

        public Object getStartReviewDate() {
            return startReviewDate;
        }

        public void setStartReviewDate(Object startReviewDate) {
            this.startReviewDate = startReviewDate;
        }

        public Object getEndReviewDate() {
            return endReviewDate;
        }

        public void setEndReviewDate(Object endReviewDate) {
            this.endReviewDate = endReviewDate;
        }

        public Object getStartRepaymentTime() {
            return startRepaymentTime;
        }

        public void setStartRepaymentTime(Object startRepaymentTime) {
            this.startRepaymentTime = startRepaymentTime;
        }

        public Object getEndRepaymentTime() {
            return endRepaymentTime;
        }

        public void setEndRepaymentTime(Object endRepaymentTime) {
            this.endRepaymentTime = endRepaymentTime;
        }

        public Object getIdentityNumber() {
            return identityNumber;
        }

        public void setIdentityNumber(Object identityNumber) {
            this.identityNumber = identityNumber;
        }

        public Object getLoanNo() {
            return loanNo;
        }

        public void setLoanNo(Object loanNo) {
            this.loanNo = loanNo;
        }

        public Object getLimitUsed() {
            return limitUsed;
        }

        public void setLimitUsed(Object limitUsed) {
            this.limitUsed = limitUsed;
        }

        public String getByStages() {
            return byStages;
        }

        public void setByStages(String byStages) {
            this.byStages = byStages;
        }

        public Object getRepayMethods() {
            return repayMethods;
        }

        public void setRepayMethods(Object repayMethods) {
            this.repayMethods = repayMethods;
        }

        public Object getAnswerTotalAmount() {
            return answerTotalAmount;
        }

        public void setAnswerTotalAmount(Object answerTotalAmount) {
            this.answerTotalAmount = answerTotalAmount;
        }

        public Object getAlreadyTotalAmount() {
            return alreadyTotalAmount;
        }

        public void setAlreadyTotalAmount(Object alreadyTotalAmount) {
            this.alreadyTotalAmount = alreadyTotalAmount;
        }

        public Object getRepaymentSchedule() {
            return repaymentSchedule;
        }

        public void setRepaymentSchedule(Object repaymentSchedule) {
            this.repaymentSchedule = repaymentSchedule;
        }

        public Object getOverdue() {
            return overdue;
        }

        public void setOverdue(Object overdue) {
            this.overdue = overdue;
        }

        public Object getLoLoanDate() {
            return loLoanDate;
        }

        public void setLoLoanDate(Object loLoanDate) {
            this.loLoanDate = loLoanDate;
        }

        public Object getPaymentPassword() {
            return paymentPassword;
        }

        public void setPaymentPassword(Object paymentPassword) {
            this.paymentPassword = paymentPassword;
        }

        public Object getFirstRepaymentTime() {
            return firstRepaymentTime;
        }

        public void setFirstRepaymentTime(Object firstRepaymentTime) {
            this.firstRepaymentTime = firstRepaymentTime;
        }

        public String getArriveTime() {
            return arriveTime;
        }

        public void setArriveTime(String arriveTime) {
            this.arriveTime = arriveTime;
        }

        public Object getToReBoNum() {
            return toReBoNum;
        }

        public void setToReBoNum(Object toReBoNum) {
            this.toReBoNum = toReBoNum;
        }

        public Object getSurplusNum() {
            return surplusNum;
        }

        public void setSurplusNum(Object surplusNum) {
            this.surplusNum = surplusNum;
        }

        public Object getToRepayment() {
            return toRepayment;
        }

        public void setToRepayment(Object toRepayment) {
            this.toRepayment = toRepayment;
        }

        public Object getBorrowYearMonth() {
            return borrowYearMonth;
        }

        public void setBorrowYearMonth(Object borrowYearMonth) {
            this.borrowYearMonth = borrowYearMonth;
        }

        public Object getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Object totalCount) {
            this.totalCount = totalCount;
        }

        public String getTotalStatusName() {
            return totalStatusName;
        }

        public void setTotalStatusName(String totalStatusName) {
            this.totalStatusName = totalStatusName;
        }

        public String getLoanServiceCharge() {
            return loanServiceCharge;
        }

        public void setLoanServiceCharge(String loanServiceCharge) {
            this.loanServiceCharge = loanServiceCharge;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankCardNoEndNumber() {
            return bankCardNoEndNumber;
        }

        public void setBankCardNoEndNumber(String bankCardNoEndNumber) {
            this.bankCardNoEndNumber = bankCardNoEndNumber;
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
    }
}
