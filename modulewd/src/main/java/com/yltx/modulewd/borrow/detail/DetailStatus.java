package com.yltx.modulewd.borrow.detail;

/**
 * 功能描述:
 * Created by ixzus on 2017/9/28.
 */

public interface DetailStatus {
    String FOR_LEND = "待放款";
    String CHECK_PENDING = "借款待审核";
    String LOAN = "已放款";
    String REPAYMENT = "还款中";
    String PAYOFF = "已还清";
    String OVERDUE = "已逾期";
    String BORROW_FAIL = "借款失败";
}
