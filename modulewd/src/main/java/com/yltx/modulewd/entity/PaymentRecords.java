package com.yltx.modulewd.entity;

import java.util.List;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/25.
 */

public class PaymentRecords {

    /**
     * code : null
     * message : null
     * data : {"count":"1","payments":[{"ym":"2017-10","records":[{"isOverdue":"","paymentTime":"2017-10-25 11:30:04","mbUserName":"","alreadyTotalAmount":"0.0","createBy":"","endExpireDate":"","repaymentId":"351","principal":"0.0","thisRepayment":"124.23","borrowId":"1851","interest":"0.0","repaymentWay":"","boBorrowNo":"","totalAmount":"123.0","year":"2017","curNum":"1","createDate":{"array":false}}]}]}
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
         * count : 1
         * payments : [{"ym":"2017-10","records":[{"isOverdue":"","paymentTime":"2017-10-25 11:30:04","mbUserName":"","alreadyTotalAmount":"0.0","createBy":"","endExpireDate":"","repaymentId":"351","principal":"0.0","thisRepayment":"124.23","borrowId":"1851","interest":"0.0","repaymentWay":"","boBorrowNo":"","totalAmount":"123.0","year":"2017","curNum":"1","createDate":{"array":false}}]}]
         */

        private String count;
        private List<PaymentsBean> payments;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<PaymentsBean> getPayments() {
            return payments;
        }

        public void setPayments(List<PaymentsBean> payments) {
            this.payments = payments;
        }

        public static class PaymentsBean {
            /**
             * ym : 2017-10
             * records : [{"isOverdue":"","paymentTime":"2017-10-25 11:30:04","mbUserName":"","alreadyTotalAmount":"0.0","createBy":"","endExpireDate":"","repaymentId":"351","principal":"0.0","thisRepayment":"124.23","borrowId":"1851","interest":"0.0","repaymentWay":"","boBorrowNo":"","totalAmount":"123.0","year":"2017","curNum":"1","createDate":{"array":false}}]
             */

            private String ym;
            private List<RecordsBean> records;

            public String getYm() {
                return ym;
            }

            public void setYm(String ym) {
                this.ym = ym;
            }

            public List<RecordsBean> getRecords() {
                return records;
            }

            public void setRecords(List<RecordsBean> records) {
                this.records = records;
            }

            public static class RecordsBean {
                /**
                 * isOverdue : 
                 * paymentTime : 2017-10-25 11:30:04
                 * mbUserName : 
                 * alreadyTotalAmount : 0.0
                 * createBy : 
                 * endExpireDate : 
                 * repaymentId : 351
                 * principal : 0.0
                 * thisRepayment : 124.23
                 * borrowId : 1851
                 * interest : 0.0
                 * repaymentWay : 
                 * boBorrowNo : 
                 * totalAmount : 123.0
                 * year : 2017
                 * curNum : 1
                 * createDate : {"array":false}
                 */

                private String isOverdue;
                private String paymentTime;
                private String mbUserName;
                private String alreadyTotalAmount;
                private String createBy;
                private String endExpireDate;
                private String repaymentId;
                private String principal;
                private String thisRepayment;
                private String borrowId;
                private String interest;
                private String repaymentWay;
                private String boBorrowNo;
                private String totalAmount;
                private String year;
                private String curNum;
                private String status;
                private CreateDateBean createDate;

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getIsOverdue() {
                    return isOverdue;
                }

                public void setIsOverdue(String isOverdue) {
                    this.isOverdue = isOverdue;
                }

                public String getPaymentTime() {
                    return paymentTime;
                }

                public void setPaymentTime(String paymentTime) {
                    this.paymentTime = paymentTime;
                }

                public String getMbUserName() {
                    return mbUserName;
                }

                public void setMbUserName(String mbUserName) {
                    this.mbUserName = mbUserName;
                }

                public String getAlreadyTotalAmount() {
                    return alreadyTotalAmount;
                }

                public void setAlreadyTotalAmount(String alreadyTotalAmount) {
                    this.alreadyTotalAmount = alreadyTotalAmount;
                }

                public String getCreateBy() {
                    return createBy;
                }

                public void setCreateBy(String createBy) {
                    this.createBy = createBy;
                }

                public String getEndExpireDate() {
                    return endExpireDate;
                }

                public void setEndExpireDate(String endExpireDate) {
                    this.endExpireDate = endExpireDate;
                }

                public String getRepaymentId() {
                    return repaymentId;
                }

                public void setRepaymentId(String repaymentId) {
                    this.repaymentId = repaymentId;
                }

                public String getPrincipal() {
                    return principal;
                }

                public void setPrincipal(String principal) {
                    this.principal = principal;
                }

                public String getThisRepayment() {
                    return thisRepayment;
                }

                public void setThisRepayment(String thisRepayment) {
                    this.thisRepayment = thisRepayment;
                }

                public String getBorrowId() {
                    return borrowId;
                }

                public void setBorrowId(String borrowId) {
                    this.borrowId = borrowId;
                }

                public String getInterest() {
                    return interest;
                }

                public void setInterest(String interest) {
                    this.interest = interest;
                }

                public String getRepaymentWay() {
                    return repaymentWay;
                }

                public void setRepaymentWay(String repaymentWay) {
                    this.repaymentWay = repaymentWay;
                }

                public String getBoBorrowNo() {
                    return boBorrowNo;
                }

                public void setBoBorrowNo(String boBorrowNo) {
                    this.boBorrowNo = boBorrowNo;
                }

                public String getTotalAmount() {
                    return totalAmount;
                }

                public void setTotalAmount(String totalAmount) {
                    this.totalAmount = totalAmount;
                }

                public String getYear() {
                    return year;
                }

                public void setYear(String year) {
                    this.year = year;
                }

                public String getCurNum() {
                    return curNum;
                }

                public void setCurNum(String curNum) {
                    this.curNum = curNum;
                }

                public CreateDateBean getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(CreateDateBean createDate) {
                    this.createDate = createDate;
                }

                public static class CreateDateBean {
                    /**
                     * array : false
                     */

                    private boolean array;

                    public boolean isArray() {
                        return array;
                    }

                    public void setArray(boolean array) {
                        this.array = array;
                    }
                }
            }
        }
    }
}
