package com.yltx.modulewd.borrow.lsit;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperTextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yltx.modulebase.base.BaseFragment;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulewd.R;
import com.yltx.modulewd.borrow.amortization.AmortizationLoanActivity;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.BorrowRepayment;
import com.yltx.modulewd.net.RxRetrofit;

public class LoanFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private SmartRefreshLayout refreshlayout;
    private TextView tvMoneyTitle;
    private TextView tvMoney;
    private SuperTextView stvPrepayment;
    private SuperTextView stvAmortization;


    public LoanFragment() {
    }

    public static LoanFragment newInstance(String param1, String param2) {
        LoanFragment fragment = new LoanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void fetchData() {
        loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID));
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_loan;
    }

    @Override
    protected void initView() {
        refreshlayout = (SmartRefreshLayout) rootView.findViewById(R.id.refreshlayout);
        tvMoneyTitle = (TextView) rootView.findViewById(R.id.tv_money_title);
        tvMoney = (TextView) rootView.findViewById(R.id.tv_money);
        stvPrepayment = (SuperTextView) rootView.findViewById(R.id.stv_prepayment);
        stvAmortization = (SuperTextView) rootView.findViewById(R.id.stv_amortization);

        stvPrepayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, PrePaymentActivity.class));
            }
        });
        stvAmortization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, AmortizationLoanActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID));
            }
        });
    }

    private void loadData(String memberid) {
        RxRetrofit.getInstance().getApiService().getBorrowRepayment(memberid)
                .compose(this.<BorrowRepayment>bindToLifecycle())
                .compose(RxSchedulers.<BorrowRepayment>io_main())
                .subscribe(new NetObserver<BorrowRepayment>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, BorrowRepayment borrowRepayment) {
                        refreshlayout.finishRefresh();
                        if ("success".equals(borrowRepayment.getCode())) {
                            refreshUI(borrowRepayment.getData());
                        } else {
                            Toast.makeText(mContext, "" + borrowRepayment.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {
                        refreshlayout.finishRefresh();
                    }
                });
    }

    private void refreshUI(BorrowRepayment.DataBean data) {
        tvMoney.setText("" + data.getLimitUsed());
        stvPrepayment.setRightString("共 " + data.getToReBoNum() + " 笔");
        stvAmortization.setRightString(data.getExpireMonthDay() + " 待还 " + data.getTotalAmount() + " 元");

        updateNum("" + data.getToReBoNum(), "" + data.getRepayNum());
        ACache.get(mContext).put("ToReBoNum", data.getToReBoNum());
        ACache.get(mContext).put("RepayNum", data.getRepayNum());
    }

    /**
     *
     */

    public interface LoanFragmentListener {
        void onLoanFragmentNum(String num1, String num2);
    }

    private LoanFragmentListener mListener;

    public void updateNum(String num1, String num2) {
        if (mListener != null) {
            mListener.onLoanFragmentNum(num1, num2);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoanFragmentListener) {
            mListener = (LoanFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LoanFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
