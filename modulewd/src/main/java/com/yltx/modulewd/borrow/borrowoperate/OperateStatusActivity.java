package com.yltx.modulewd.borrow.borrowoperate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulewd.R;
import com.yltx.modulewd.entity.Consta;
import com.yltx.modulewd.entity.LoanDetailRsBean;
import com.yltx.modulewd.net.RxRetrofit;

/**
 * Author：Wq
 * Date：2017/10/10 15:04
 * Description：//todo
 */

public class OperateStatusActivity extends BaseActivity {

    public TextView toolbar_title;
    public TextView toolbar_subtitle;

    private TextView tvTitleMsg;
    private TextView vtTitleValue, mTvTips, mTvnum, mTvtime;
    private SuperTextView stTips;
    private SuperTextView stLoaNum;
    private SuperTextView stPayment;

    private SuperButton sbToNext;

    private String mBorrowId;
    private String from;

    @Override
    protected int initLayout() {
        return R.layout.activity_operation_status;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        initToolBar(true, "还款成功", null);

        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_subtitle = (TextView) findViewById(R.id.toolbar_subtitle);


        tvTitleMsg = (TextView) findViewById(R.id.tv_title_msg);

        vtTitleValue = (TextView) findViewById(R.id.tv_title_value);
//
//        stTips= (SuperTextView) findViewById(R.id.st_tips);

//        stLoaNum= (SuperTextView) findViewById(R.id.st_loan_number);
//        stPayment= (SuperTextView) findViewById(R.id.st_payment);

        sbToNext = (SuperButton) findViewById(R.id.sb_tonext);

        mTvTips = (TextView) findViewById(R.id.tv_tips);

        mTvnum = (TextView) findViewById(R.id.tv_num);

        mTvtime = (TextView) findViewById(R.id.tv_time);


        initEvent();


    }

    private void initEvent() {
        sbToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
    }

    @Override
    protected void initData() {
//        ACache.get(mContext).put("OperateStatusId","");
        mBorrowId = ACache.get(mContext).getAsString("OperateStatusId");
        from = ACache.get(mContext).getAsString("OperateStatusFrom");
//        mBorrowId=getIntent().getStringExtra("borrowId");
        if ("loan".equals(from)) {
            loadData();
        } else {
            vtTitleValue.setText("" + ACache.get(mContext).getAsString("AddOrderMoney"));
            mTvnum.setText("" + ACache.get(mContext).getAsString("AddOrderPayNo"));
            mTvtime.setText("" + ACache.get(mContext).getAsString("AddOrderTime"));
        }

    }

    private void loadData() {

        RxRetrofit.getInstance().getApiService().loanDetail(mBorrowId)
                .compose(this.<LoanDetailRsBean>bindToLifecycle())
                .compose(RxSchedulers.<LoanDetailRsBean>io_main())
                .subscribe(new NetObserver<LoanDetailRsBean>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, LoanDetailRsBean result) {
                        if ("success".equals(result.getCode())) {
                            setData(result.getData());
                        } else {
                            Toast.makeText(mContext, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    private void setData(LoanDetailRsBean.DataBean data) {
        initToolBar(true, setStatusTitle(data), null);
        vtTitleValue.setText("¥ " + data.getBorrowLimit());
        tvTitleMsg.setText(setStatusMsg(data));

        mTvnum.setText(data.getBorrowNo());
        mTvtime.setText(data.getBorrowDate());
        mTvTips.setText(setStatusTips(data));
    }


    private String setStatusString(LoanDetailRsBean.DataBean data) {
        String mSatatusStr = "";
        switch (data.getTotalStatus()) {
            case Consta.totalStatusValue.Loan_Audited:
                mSatatusStr = "借款待审核";
                break;
            case Consta.totalStatusValue.Loan_Pending:
                mSatatusStr = "待放款";
                break;
            case Consta.totalStatusValue.Loan_Secured:
                mSatatusStr = "已放款";
                break;
            case Consta.totalStatusValue.Loan_Failure:
                mSatatusStr = "借款失败";
                break;
            case Consta.totalStatusValue.Repayment:
                mSatatusStr = "还款中";
                break;

            case Consta.totalStatusValue.Overdue:
                mSatatusStr = "已逾期";
                break;
        }
        return mSatatusStr;
    }


    private String setStatusTips(LoanDetailRsBean.DataBean data) {
//        //        借款总状态： 1-借款待审核   2-待放款    3-已放款    4-借款失败    5-还款中   6-已逾期
//        public static final String Loan_Audited= "1";//借款待审核
//        public static final String Loan_Pending= "2";//待放款
//        public  static final String Loan_Secured="3";//已放款
//
//        public static final String Loan_Failure= "4";//借款失败
//        public  static final String Repayment="5";//还款中
//        public  static final String Overdue="6";//已逾期
//
//        public  static final String PaidOff="7";//已还清
        String mStatusTips = "";
        switch (data.getTotalStatus()) {
            case Consta.totalStatusValue.Loan_Audited://借款待审核
                mStatusTips = "您稍后有可能会接到我们客服人员与您确认借款信息，请留意并保持电话畅通";
//                mStatusTips = "预计银行到账时间 "+data.getArriveTime();
                break;
            case Consta.totalStatusValue.Loan_Pending://待放款
//                mStatusTips= "待放款";
                mStatusTips = "您稍后有可能会接到我们客服人员与您确认借款信息，请留意并保持电话畅通";
                break;
            case Consta.totalStatusValue.Loan_Secured://已放款
//                mStatusTips = "已放款";
                mStatusTips = "预计银行到账时间 " + data.getArriveTime();
                break;
            case Consta.totalStatusValue.Loan_Failure://借款失败
                mStatusTips = "您稍后有可能会接到我们客服人员与您确认借款信息，请留意并保持电话畅通";
                break;
            case Consta.totalStatusValue.Repayment://还款中
                mStatusTips = "还款中";
                break;

            case Consta.totalStatusValue.Overdue://已逾期
                mStatusTips = "已逾期";
                break;
        }
        return "温馨提示: " + mStatusTips;
    }


    private String setStatusTitle(LoanDetailRsBean.DataBean data) {
        String mStatusTitle = "";
        switch (data.getTotalStatus()) {
            case Consta.totalStatusValue.Loan_Audited:
                mStatusTitle = "借款待审核 ";
                break;
            case Consta.totalStatusValue.Loan_Pending:
//                mStatusTips= "待放款";
                mStatusTitle = "待放款";
                break;
            case Consta.totalStatusValue.Loan_Secured:
                mStatusTitle = "已放款";
                break;
            case Consta.totalStatusValue.Loan_Failure:
                mStatusTitle = "借款失败";
                break;
            case Consta.totalStatusValue.Repayment:
                mStatusTitle = "还款中";
                break;

            case Consta.totalStatusValue.Overdue:
                mStatusTitle = "已逾期";
                break;
        }
        return mStatusTitle;
    }

//
//    private String setStatusMsg(LoanDetailRsBean.DataBean data){
//        String mStatusMsg="";
//        switch (data.getTotalStatus()) {
//            case Consta.LoanStatementStatus.PendingTrial:
//                mStatusMsg = "待初审";
//                break;
//            case Consta.LoanStatementStatus.PendingReview:
//                mStatusMsg= "待复审";
//                break;
//            case Consta.LoanStatementStatus.ReviewPassed:
//                mStatusMsg = "已放款";
//                break;
//            case Consta.LoanStatementStatus.Rejected:
//                mStatusMsg = "已拒绝";
//                break;
//        }
//        return mStatusMsg;
//    }


    private String setStatusMsg(LoanDetailRsBean.DataBean data) {
        String mStatusMsg = "";
        switch (data.getTotalStatus()) {
            case Consta.totalStatusValue.Loan_Audited:
                mStatusMsg = "借款提交成功,等待系统审核 ";
                break;
            case Consta.totalStatusValue.Loan_Pending:
//                mStatusTips= "待放款";
                mStatusMsg = "借款提交成功，待放款";
                break;
            case Consta.totalStatusValue.Loan_Secured:
                mStatusMsg = "借款提交成功，已放款";
                break;
            case Consta.totalStatusValue.Loan_Failure:
                mStatusMsg = "借款提交成功，借款失败";
                break;
            case Consta.totalStatusValue.Repayment:
                mStatusMsg = "还款中";
                break;

            case Consta.totalStatusValue.Overdue:
                mStatusMsg = "已逾期";
                break;
        }
        return mStatusMsg;
    }
}
