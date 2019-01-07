package com.yltx.modulewd.entity;

import java.util.List;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/20.
 */

public class BorrowList {

    /**
     * code : success
     * message : 调用成功
     * data : [{"borrowYearMonth":"2017-10","borrows":[{"borrowId":"1400","borrowNo":null,"memberId":null,"borrowLimit":"50.0","borrowTerm":null,"borrowFee":null,"overdueFine":"0.0","totalLimit":null,"surplusLimit":null,"status":null,"borrowDate":"2017-10-20 14:10:07","reviewDate":null,"repaymentTime":null,"reviewRemark":null,"createDate":null,"createBy":null,"updateDate":null,"updateBy":null,"repaymentNum":null,"repaymentFinishDate":null,"totalStatus":"2","memberNo":null,"mbUserName":null,"boType":null,"startBorrowDate":null,"endBorrowDate":null,"startReviewDate":null,"endReviewDate":null,"startRepaymentTime":null,"endRepaymentTime":null,"identityNumber":null,"loanNo":null,"limitUsed":null,"byStages":"0","repayMethods":null,"answerTotalAmount":null,"alreadyTotalAmount":null,"repaymentSchedule":null,"overdue":null,"loLoanDate":null,"paymentPassword":null,"firstRepaymentTime":null,"arriveTime":null,"toReBoNum":null,"surplusNum":"0","toRepayment":"50","borrowYearMonth":"2017-10","totalCount":"5"},{"borrowId":"1352","borrowNo":null,"memberId":null,"borrowLimit":"100.0","borrowTerm":null,"borrowFee":null,"overdueFine":"0.0","totalLimit":null,"surplusLimit":null,"status":null,"borrowDate":"2017-10-20 13:53:17","reviewDate":null,"repaymentTime":null,"reviewRemark":null,"createDate":null,"createBy":null,"updateDate":null,"updateBy":null,"repaymentNum":null,"repaymentFinishDate":null,"totalStatus":"2","memberNo":null,"mbUserName":null,"boType":null,"startBorrowDate":null,"endBorrowDate":null,"startReviewDate":null,"endReviewDate":null,"startRepaymentTime":null,"endRepaymentTime":null,"identityNumber":null,"loanNo":null,"limitUsed":null,"byStages":"0","repayMethods":null,"answerTotalAmount":null,"alreadyTotalAmount":null,"repaymentSchedule":null,"overdue":null,"loLoanDate":null,"paymentPassword":null,"firstRepaymentTime":null,"arriveTime":null,"toReBoNum":null,"surplusNum":"0","toRepayment":"100","borrowYearMonth":"2017-10","totalCount":"5"},{"borrowId":"1351","borrowNo":null,"memberId":null,"borrowLimit":"100.0","borrowTerm":null,"borrowFee":null,"overdueFine":"0.0","totalLimit":null,"surplusLimit":null,"status":null,"borrowDate":"2017-10-20 11:44:25","reviewDate":null,"repaymentTime":null,"reviewRemark":null,"createDate":null,"createBy":null,"updateDate":null,"updateBy":null,"repaymentNum":null,"repaymentFinishDate":null,"totalStatus":"2","memberNo":null,"mbUserName":null,"boType":null,"startBorrowDate":null,"endBorrowDate":null,"startReviewDate":null,"endReviewDate":null,"startRepaymentTime":null,"endRepaymentTime":null,"identityNumber":null,"loanNo":null,"limitUsed":null,"byStages":"0","repayMethods":null,"answerTotalAmount":null,"alreadyTotalAmount":null,"repaymentSchedule":null,"overdue":null,"loLoanDate":null,"paymentPassword":null,"firstRepaymentTime":null,"arriveTime":null,"toReBoNum":null,"surplusNum":"0","toRepayment":"100","borrowYearMonth":"2017-10","totalCount":"5"},{"borrowId":"1350","borrowNo":null,"memberId":null,"borrowLimit":"1000.0","borrowTerm":null,"borrowFee":null,"overdueFine":"0.0","totalLimit":null,"surplusLimit":null,"status":null,"borrowDate":"2017-10-20 11:42:23","reviewDate":null,"repaymentTime":null,"reviewRemark":null,"createDate":null,"createBy":null,"updateDate":null,"updateBy":null,"repaymentNum":null,"repaymentFinishDate":null,"totalStatus":"2","memberNo":null,"mbUserName":null,"boType":null,"startBorrowDate":null,"endBorrowDate":null,"startReviewDate":null,"endReviewDate":null,"startRepaymentTime":null,"endRepaymentTime":null,"identityNumber":null,"loanNo":null,"limitUsed":null,"byStages":"0","repayMethods":null,"answerTotalAmount":null,"alreadyTotalAmount":null,"repaymentSchedule":null,"overdue":null,"loLoanDate":null,"paymentPassword":null,"firstRepaymentTime":null,"arriveTime":null,"toReBoNum":null,"surplusNum":"0","toRepayment":"1000","borrowYearMonth":"2017-10","totalCount":"5"},{"borrowId":"1300","borrowNo":null,"memberId":null,"borrowLimit":"125.0","borrowTerm":null,"borrowFee":null,"overdueFine":"0.0","totalLimit":null,"surplusLimit":null,"status":null,"borrowDate":"2017-10-19 17:17:04","reviewDate":null,"repaymentTime":null,"reviewRemark":null,"createDate":null,"createBy":null,"updateDate":null,"updateBy":null,"repaymentNum":null,"repaymentFinishDate":null,"totalStatus":"2","memberNo":null,"mbUserName":null,"boType":null,"startBorrowDate":null,"endBorrowDate":null,"startReviewDate":null,"endReviewDate":null,"startRepaymentTime":null,"endRepaymentTime":null,"identityNumber":null,"loanNo":null,"limitUsed":null,"byStages":"0","repayMethods":null,"answerTotalAmount":null,"alreadyTotalAmount":null,"repaymentSchedule":null,"overdue":null,"loLoanDate":null,"paymentPassword":null,"firstRepaymentTime":null,"arriveTime":null,"toReBoNum":null,"surplusNum":"0","toRepayment":"125","borrowYearMonth":"2017-10","totalCount":"5"}]}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * borrowYearMonth : 2017-10
         * borrows : [{"borrowId":"1400","borrowNo":null,"memberId":null,"borrowLimit":"50.0","borrowTerm":null,"borrowFee":null,"overdueFine":"0.0","totalLimit":null,"surplusLimit":null,"status":null,"borrowDate":"2017-10-20 14:10:07","reviewDate":null,"repaymentTime":null,"reviewRemark":null,"createDate":null,"createBy":null,"updateDate":null,"updateBy":null,"repaymentNum":null,"repaymentFinishDate":null,"totalStatus":"2","memberNo":null,"mbUserName":null,"boType":null,"startBorrowDate":null,"endBorrowDate":null,"startReviewDate":null,"endReviewDate":null,"startRepaymentTime":null,"endRepaymentTime":null,"identityNumber":null,"loanNo":null,"limitUsed":null,"byStages":"0","repayMethods":null,"answerTotalAmount":null,"alreadyTotalAmount":null,"repaymentSchedule":null,"overdue":null,"loLoanDate":null,"paymentPassword":null,"firstRepaymentTime":null,"arriveTime":null,"toReBoNum":null,"surplusNum":"0","toRepayment":"50","borrowYearMonth":"2017-10","totalCount":"5"},{"borrowId":"1352","borrowNo":null,"memberId":null,"borrowLimit":"100.0","borrowTerm":null,"borrowFee":null,"overdueFine":"0.0","totalLimit":null,"surplusLimit":null,"status":null,"borrowDate":"2017-10-20 13:53:17","reviewDate":null,"repaymentTime":null,"reviewRemark":null,"createDate":null,"createBy":null,"updateDate":null,"updateBy":null,"repaymentNum":null,"repaymentFinishDate":null,"totalStatus":"2","memberNo":null,"mbUserName":null,"boType":null,"startBorrowDate":null,"endBorrowDate":null,"startReviewDate":null,"endReviewDate":null,"startRepaymentTime":null,"endRepaymentTime":null,"identityNumber":null,"loanNo":null,"limitUsed":null,"byStages":"0","repayMethods":null,"answerTotalAmount":null,"alreadyTotalAmount":null,"repaymentSchedule":null,"overdue":null,"loLoanDate":null,"paymentPassword":null,"firstRepaymentTime":null,"arriveTime":null,"toReBoNum":null,"surplusNum":"0","toRepayment":"100","borrowYearMonth":"2017-10","totalCount":"5"},{"borrowId":"1351","borrowNo":null,"memberId":null,"borrowLimit":"100.0","borrowTerm":null,"borrowFee":null,"overdueFine":"0.0","totalLimit":null,"surplusLimit":null,"status":null,"borrowDate":"2017-10-20 11:44:25","reviewDate":null,"repaymentTime":null,"reviewRemark":null,"createDate":null,"createBy":null,"updateDate":null,"updateBy":null,"repaymentNum":null,"repaymentFinishDate":null,"totalStatus":"2","memberNo":null,"mbUserName":null,"boType":null,"startBorrowDate":null,"endBorrowDate":null,"startReviewDate":null,"endReviewDate":null,"startRepaymentTime":null,"endRepaymentTime":null,"identityNumber":null,"loanNo":null,"limitUsed":null,"byStages":"0","repayMethods":null,"answerTotalAmount":null,"alreadyTotalAmount":null,"repaymentSchedule":null,"overdue":null,"loLoanDate":null,"paymentPassword":null,"firstRepaymentTime":null,"arriveTime":null,"toReBoNum":null,"surplusNum":"0","toRepayment":"100","borrowYearMonth":"2017-10","totalCount":"5"},{"borrowId":"1350","borrowNo":null,"memberId":null,"borrowLimit":"1000.0","borrowTerm":null,"borrowFee":null,"overdueFine":"0.0","totalLimit":null,"surplusLimit":null,"status":null,"borrowDate":"2017-10-20 11:42:23","reviewDate":null,"repaymentTime":null,"reviewRemark":null,"createDate":null,"createBy":null,"updateDate":null,"updateBy":null,"repaymentNum":null,"repaymentFinishDate":null,"totalStatus":"2","memberNo":null,"mbUserName":null,"boType":null,"startBorrowDate":null,"endBorrowDate":null,"startReviewDate":null,"endReviewDate":null,"startRepaymentTime":null,"endRepaymentTime":null,"identityNumber":null,"loanNo":null,"limitUsed":null,"byStages":"0","repayMethods":null,"answerTotalAmount":null,"alreadyTotalAmount":null,"repaymentSchedule":null,"overdue":null,"loLoanDate":null,"paymentPassword":null,"firstRepaymentTime":null,"arriveTime":null,"toReBoNum":null,"surplusNum":"0","toRepayment":"1000","borrowYearMonth":"2017-10","totalCount":"5"},{"borrowId":"1300","borrowNo":null,"memberId":null,"borrowLimit":"125.0","borrowTerm":null,"borrowFee":null,"overdueFine":"0.0","totalLimit":null,"surplusLimit":null,"status":null,"borrowDate":"2017-10-19 17:17:04","reviewDate":null,"repaymentTime":null,"reviewRemark":null,"createDate":null,"createBy":null,"updateDate":null,"updateBy":null,"repaymentNum":null,"repaymentFinishDate":null,"totalStatus":"2","memberNo":null,"mbUserName":null,"boType":null,"startBorrowDate":null,"endBorrowDate":null,"startReviewDate":null,"endReviewDate":null,"startRepaymentTime":null,"endRepaymentTime":null,"identityNumber":null,"loanNo":null,"limitUsed":null,"byStages":"0","repayMethods":null,"answerTotalAmount":null,"alreadyTotalAmount":null,"repaymentSchedule":null,"overdue":null,"loLoanDate":null,"paymentPassword":null,"firstRepaymentTime":null,"arriveTime":null,"toReBoNum":null,"surplusNum":"0","toRepayment":"125","borrowYearMonth":"2017-10","totalCount":"5"}]
         */

        private String borrowYearMonth;
        private List<BorrowsBean> borrows;

        public String getBorrowYearMonth() {
            return borrowYearMonth;
        }

        public void setBorrowYearMonth(String borrowYearMonth) {
            this.borrowYearMonth = borrowYearMonth;
        }

        public List<BorrowsBean> getBorrows() {
            return borrows;
        }

        public void setBorrows(List<BorrowsBean> borrows) {
            this.borrows = borrows;
        }

        public static class BorrowsBean {
            /**
             * borrowId : 1400
             * borrowNo : null
             * memberId : null
             * borrowLimit : 50.0
             * borrowTerm : null
             * borrowFee : null
             * overdueFine : 0.0
             * totalLimit : null
             * surplusLimit : null
             * status : null
             * borrowDate : 2017-10-20 14:10:07
             * reviewDate : null
             * repaymentTime : null
             * reviewRemark : null
             * createDate : null
             * createBy : null
             * updateDate : null
             * updateBy : null
             * repaymentNum : null
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
             * arriveTime : null
             * toReBoNum : null
             * surplusNum : 0
             * toRepayment : 50
             * borrowYearMonth : 2017-10
             * totalCount : 5
             */

            private String borrowId;
            private Object borrowNo;
            private Object memberId;
            private String borrowLimit;
            private Object borrowTerm;
            private Object borrowFee;
            private String overdueFine;
            private Object totalLimit;
            private Object surplusLimit;
            private Object status;
            private String borrowDate;
            private Object reviewDate;
            private Object repaymentTime;
            private Object reviewRemark;
            private Object createDate;
            private Object createBy;
            private Object updateDate;
            private Object updateBy;
            private Object repaymentNum;
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
            private Object arriveTime;
            private Object toReBoNum;
            private String surplusNum;
            private String toRepayment;
            private String borrowYearMonth;
            private String totalCount;

            public String getBorrowId() {
                return borrowId;
            }

            public void setBorrowId(String borrowId) {
                this.borrowId = borrowId;
            }

            public Object getBorrowNo() {
                return borrowNo;
            }

            public void setBorrowNo(Object borrowNo) {
                this.borrowNo = borrowNo;
            }

            public Object getMemberId() {
                return memberId;
            }

            public void setMemberId(Object memberId) {
                this.memberId = memberId;
            }

            public String getBorrowLimit() {
                return borrowLimit;
            }

            public void setBorrowLimit(String borrowLimit) {
                this.borrowLimit = borrowLimit;
            }

            public Object getBorrowTerm() {
                return borrowTerm;
            }

            public void setBorrowTerm(Object borrowTerm) {
                this.borrowTerm = borrowTerm;
            }

            public Object getBorrowFee() {
                return borrowFee;
            }

            public void setBorrowFee(Object borrowFee) {
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

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public String getBorrowDate() {
                return borrowDate;
            }

            public void setBorrowDate(String borrowDate) {
                this.borrowDate = borrowDate;
            }

            public Object getReviewDate() {
                return reviewDate;
            }

            public void setReviewDate(Object reviewDate) {
                this.reviewDate = reviewDate;
            }

            public Object getRepaymentTime() {
                return repaymentTime;
            }

            public void setRepaymentTime(Object repaymentTime) {
                this.repaymentTime = repaymentTime;
            }

            public Object getReviewRemark() {
                return reviewRemark;
            }

            public void setReviewRemark(Object reviewRemark) {
                this.reviewRemark = reviewRemark;
            }

            public Object getCreateDate() {
                return createDate;
            }

            public void setCreateDate(Object createDate) {
                this.createDate = createDate;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
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

            public Object getRepaymentNum() {
                return repaymentNum;
            }

            public void setRepaymentNum(Object repaymentNum) {
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

            public Object getArriveTime() {
                return arriveTime;
            }

            public void setArriveTime(Object arriveTime) {
                this.arriveTime = arriveTime;
            }

            public Object getToReBoNum() {
                return toReBoNum;
            }

            public void setToReBoNum(Object toReBoNum) {
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
        }
    }
}
