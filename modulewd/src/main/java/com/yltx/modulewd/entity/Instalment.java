package com.yltx.modulewd.entity;

import java.util.List;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/24.
 */

public class Instalment {

    /**
     * code : success
     * message : 调用成功
     * size : null
     * data : [{"years":"2017","repayments":[{"repaymentId":null,"borrowId":null,"repaymentNo":null,"totalAmount":"600.0","principal":null,"fine":null,"interest":null,"thisRepayment":null,"isOverdue":null,"status":null,"expireDate":null,"repaymentDate":null,"createDate":null,"createBy":null,"updateDate":null,"updateBy":null,"repaymentWay":null,"curNum":null,"boBorrowNo":null,"memberNo":null,"mbUserName":null,"borrowLimit":null,"borrowFee":null,"borrowTerm":null,"alreadyTotalAmount":null,"startExpireDate":null,"endExpireDate":null,"startRepaymentDate":null,"endRepaymentDate":null,"repaymentDay":null,"overdueDays":null,"repaymentNum":null,"borrowDate":null,"statusName":null,"borrowAmount":null,"expiryDateFormat":"2017-11-25","totalCount":"2","expireYear":"2017","expireMonthDay":"11月25日"}]}]
     */

    private String code;
    private String message;
    private String size;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * years : 2017
         * repayments : [{"repaymentId":null,"borrowId":null,"repaymentNo":null,"totalAmount":"600.0","principal":null,"fine":null,"interest":null,"thisRepayment":null,"isOverdue":null,"status":null,"expireDate":null,"repaymentDate":null,"createDate":null,"createBy":null,"updateDate":null,"updateBy":null,"repaymentWay":null,"curNum":null,"boBorrowNo":null,"memberNo":null,"mbUserName":null,"borrowLimit":null,"borrowFee":null,"borrowTerm":null,"alreadyTotalAmount":null,"startExpireDate":null,"endExpireDate":null,"startRepaymentDate":null,"endRepaymentDate":null,"repaymentDay":null,"overdueDays":null,"repaymentNum":null,"borrowDate":null,"statusName":null,"borrowAmount":null,"expiryDateFormat":"2017-11-25","totalCount":"2","expireYear":"2017","expireMonthDay":"11月25日"}]
         */

        private String years;
        private List<RepaymentsBean> repayments;

        public String getYears() {
            return years;
        }

        public void setYears(String years) {
            this.years = years;
        }

        public List<RepaymentsBean> getRepayments() {
            return repayments;
        }

        public void setRepayments(List<RepaymentsBean> repayments) {
            this.repayments = repayments;
        }

        public static class RepaymentsBean {
            /**
             * repaymentId : null
             * borrowId : null
             * repaymentNo : null
             * totalAmount : 600.0
             * principal : null
             * fine : null
             * interest : null
             * thisRepayment : null
             * isOverdue : null
             * status : null
             * expireDate : null
             * repaymentDate : null
             * createDate : null
             * createBy : null
             * updateDate : null
             * updateBy : null
             * repaymentWay : null
             * curNum : null
             * boBorrowNo : null
             * memberNo : null
             * mbUserName : null
             * borrowLimit : null
             * borrowFee : null
             * borrowTerm : null
             * alreadyTotalAmount : null
             * startExpireDate : null
             * endExpireDate : null
             * startRepaymentDate : null
             * endRepaymentDate : null
             * repaymentDay : null
             * overdueDays : null
             * repaymentNum : null
             * borrowDate : null
             * statusName : null
             * borrowAmount : null
             * expiryDateFormat : 2017-11-25
             * totalCount : 2
             * expireYear : 2017
             * expireMonthDay : 11月25日
             */

            private String repaymentId;
            private String borrowId;
            private String repaymentNo;
            private String totalAmount;
            private String principal;
            private String fine;
            private String interest;
            private String thisRepayment;
            private String isOverdue;
            private String status;
            private String expireDate;
            private String repaymentDate;
            private String createDate;
            private String createBy;
            private String updateDate;
            private String updateBy;
            private String repaymentWay;
            private String curNum;
            private String boBorrowNo;
            private String memberNo;
            private String mbUserName;
            private String borrowLimit;
            private String borrowFee;
            private String borrowTerm;
            private String alreadyTotalAmount;
            private String startExpireDate;
            private String endExpireDate;
            private String startRepaymentDate;
            private String endRepaymentDate;
            private String repaymentDay;
            private String overdueDays;
            private String repaymentNum;
            private String borrowDate;
            private String statusName;
            private String borrowAmount;
            private String expiryDateFormat;
            private String totalCount;
            private String expireYear;
            private String expireMonthDay;

            public String getRepaymentId() {
                return repaymentId;
            }

            public void setRepaymentId(String repaymentId) {
                this.repaymentId = repaymentId;
            }

            public String getBorrowId() {
                return borrowId;
            }

            public void setBorrowId(String borrowId) {
                this.borrowId = borrowId;
            }

            public String getRepaymentNo() {
                return repaymentNo;
            }

            public void setRepaymentNo(String repaymentNo) {
                this.repaymentNo = repaymentNo;
            }

            public String getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(String totalAmount) {
                this.totalAmount = totalAmount;
            }

            public String getPrincipal() {
                return principal;
            }

            public void setPrincipal(String principal) {
                this.principal = principal;
            }

            public String getFine() {
                return fine;
            }

            public void setFine(String fine) {
                this.fine = fine;
            }

            public String getInterest() {
                return interest;
            }

            public void setInterest(String interest) {
                this.interest = interest;
            }

            public String getThisRepayment() {
                return thisRepayment;
            }

            public void setThisRepayment(String thisRepayment) {
                this.thisRepayment = thisRepayment;
            }

            public String getIsOverdue() {
                return isOverdue;
            }

            public void setIsOverdue(String isOverdue) {
                this.isOverdue = isOverdue;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getExpireDate() {
                return expireDate;
            }

            public void setExpireDate(String expireDate) {
                this.expireDate = expireDate;
            }

            public String getRepaymentDate() {
                return repaymentDate;
            }

            public void setRepaymentDate(String repaymentDate) {
                this.repaymentDate = repaymentDate;
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

            public String getRepaymentWay() {
                return repaymentWay;
            }

            public void setRepaymentWay(String repaymentWay) {
                this.repaymentWay = repaymentWay;
            }

            public String getCurNum() {
                return curNum;
            }

            public void setCurNum(String curNum) {
                this.curNum = curNum;
            }

            public String getBoBorrowNo() {
                return boBorrowNo;
            }

            public void setBoBorrowNo(String boBorrowNo) {
                this.boBorrowNo = boBorrowNo;
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

            public String getBorrowLimit() {
                return borrowLimit;
            }

            public void setBorrowLimit(String borrowLimit) {
                this.borrowLimit = borrowLimit;
            }

            public String getBorrowFee() {
                return borrowFee;
            }

            public void setBorrowFee(String borrowFee) {
                this.borrowFee = borrowFee;
            }

            public String getBorrowTerm() {
                return borrowTerm;
            }

            public void setBorrowTerm(String borrowTerm) {
                this.borrowTerm = borrowTerm;
            }

            public String getAlreadyTotalAmount() {
                return alreadyTotalAmount;
            }

            public void setAlreadyTotalAmount(String alreadyTotalAmount) {
                this.alreadyTotalAmount = alreadyTotalAmount;
            }

            public String getStartExpireDate() {
                return startExpireDate;
            }

            public void setStartExpireDate(String startExpireDate) {
                this.startExpireDate = startExpireDate;
            }

            public String getEndExpireDate() {
                return endExpireDate;
            }

            public void setEndExpireDate(String endExpireDate) {
                this.endExpireDate = endExpireDate;
            }

            public String getStartRepaymentDate() {
                return startRepaymentDate;
            }

            public void setStartRepaymentDate(String startRepaymentDate) {
                this.startRepaymentDate = startRepaymentDate;
            }

            public String getEndRepaymentDate() {
                return endRepaymentDate;
            }

            public void setEndRepaymentDate(String endRepaymentDate) {
                this.endRepaymentDate = endRepaymentDate;
            }

            public String getRepaymentDay() {
                return repaymentDay;
            }

            public void setRepaymentDay(String repaymentDay) {
                this.repaymentDay = repaymentDay;
            }

            public String getOverdueDays() {
                return overdueDays;
            }

            public void setOverdueDays(String overdueDays) {
                this.overdueDays = overdueDays;
            }

            public String getRepaymentNum() {
                return repaymentNum;
            }

            public void setRepaymentNum(String repaymentNum) {
                this.repaymentNum = repaymentNum;
            }

            public String getBorrowDate() {
                return borrowDate;
            }

            public void setBorrowDate(String borrowDate) {
                this.borrowDate = borrowDate;
            }

            public String getStatusName() {
                return statusName;
            }

            public void setStatusName(String statusName) {
                this.statusName = statusName;
            }

            public String getBorrowAmount() {
                return borrowAmount;
            }

            public void setBorrowAmount(String borrowAmount) {
                this.borrowAmount = borrowAmount;
            }

            public String getExpiryDateFormat() {
                return expiryDateFormat;
            }

            public void setExpiryDateFormat(String expiryDateFormat) {
                this.expiryDateFormat = expiryDateFormat;
            }

            public String getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(String totalCount) {
                this.totalCount = totalCount;
            }

            public String getExpireYear() {
                return expireYear;
            }

            public void setExpireYear(String expireYear) {
                this.expireYear = expireYear;
            }

            public String getExpireMonthDay() {
                return expireMonthDay;
            }

            public void setExpireMonthDay(String expireMonthDay) {
                this.expireMonthDay = expireMonthDay;
            }
        }
    }
}
