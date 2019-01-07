package com.yltx.modulewd.entity;

/**
 * Author：Wq
 * Date：2017/4/21 14:24
 * Description：//todo
 */

public class Consta {




    public class cardType {
        //cardType=0  储蓄卡 1 信用卡
        public static final String DepositBank= "0";//储值卡
        public static final String CreditBank = "1";//信用卡
        public  static final String CARDTYPE="cardType";
    }



    public class totalStatusValue {
        //
//        借款总状态： 1-借款待审核   2-待放款    3-已放款    4-借款失败    5-还款中   6-已逾期
        public static final String Loan_Audited= "1";//借款待审核
        public static final String Loan_Pending= "2";//待放款
        public  static final String Loan_Secured="3";//已放款

        public static final String Loan_Failure= "4";//借款失败
        public  static final String Repayment="5";//还款中
        public  static final String Overdue="6";//已逾期

        public  static final String PaidOff="7";//已还清
    }

    //借款表状态  1-待初审   2-待复审   3-复审通过   4-已拒绝


    public class LoanStatementStatus {
        //
//        //借款表状态  1-待初审   2-待复审   3-复审通过   4-已拒绝
        public static final String PendingTrial= "1";//待初审
        public static final String PendingReview= "2";//待复审
        public  static final String ReviewPassed="3";//复审通过
        public static final String Rejected= "4";//已拒绝

    }







}
