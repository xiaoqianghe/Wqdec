package com.yltx.modulewd.borrow.amortization;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulewd.R;
import com.yltx.modulewd.borrow.lsit.MySection;
import com.yltx.modulewd.borrow.lsit.Record;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.Instalment;
import com.yltx.modulewd.net.RxRetrofit;

import java.util.ArrayList;
import java.util.List;

public class AmortizationLoanActivity extends BaseActivity {
    private SmartRefreshLayout refreshlayout;
    private TextView tv_no;
    private RecyclerView recyclerview;
    private AmortizationLoanAdapter adapter;
    private List<MySection> listData = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.activity_amortization_loan;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "分期还款", null);
        refreshlayout = (SmartRefreshLayout) findViewById(R.id.refreshlayout);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    }

    @Override
    protected void initData() {
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new AmortizationLoanAdapter(R.layout.rv_amortizationloan_item, R.layout.rv_list_head, listData, "借款");
        View headView = View.inflate(this, R.layout.rv_amortizationloan_head, null);
        tv_no = (TextView) headView.findViewById(R.id.tv_no);
        tv_no.setText("还款日当天系统将自动从尾号" + ACache.get(mContext).getAsString(UserInfo.USER_BANK_CARD) + "银行卡扣款");
        adapter.addHeaderView(headView);
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, AmortizationActivity.class);
                intent.putExtra("id", ((MySection) adapter.getData().get(position)).t.getTime());
                intent.putExtra("type", "more");
                startActivity(intent);
            }
        });
        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNumber = 1;
                loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), pageNumber);
            }
        });
        refreshlayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++pageNumber;
                loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), pageNumber);
            }
        });
        loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), pageNumber);

    }

    int pageSize = 5;
    int pageNumber = 1;

    private void loadData(String memberid, int pageNumber) {
        RxRetrofit.getInstance().getApiService().getInstalmentList(memberid, pageSize, pageNumber)
                .compose(this.<Instalment>bindToLifecycle())
                .compose(RxSchedulers.<Instalment>io_main())
                .subscribe(new NetObserver<Instalment>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, Instalment instalment) {
                        loadFinish();
                        if ("success".equals(instalment.getCode())) {
                            refreshUI(instalment.getData());
                        } else {
                            Toast.makeText(mContext, "" + instalment.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {
                        loadFinish();
                    }
                });
    }

    private void loadFinish() {
        refreshlayout.finishRefresh();
        refreshlayout.finishLoadmore();
    }

    private void refreshUI(List<Instalment.DataBean> data) {
        if (null == data) {
            if (pageNumber >1)
                --pageNumber;
            return;
        }
        List<MySection> list = new ArrayList<>();
        for (Instalment.DataBean dataBean : data) {
            list.add(new MySection(true, dataBean.getYears()));
            for (Instalment.DataBean.RepaymentsBean repaymentsBean : dataBean.getRepayments()) {
                list.add(new MySection(new Record(repaymentsBean.getBorrowId(), repaymentsBean.getExpireMonthDay(), repaymentsBean.getTotalAmount(), repaymentsBean.getExpiryDateFormat(), null, null, null)));
            }
        }
        listData = list;
        adapter.setNewData(listData);
    }


}
