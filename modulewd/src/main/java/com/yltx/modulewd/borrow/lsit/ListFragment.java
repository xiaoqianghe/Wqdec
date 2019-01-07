package com.yltx.modulewd.borrow.lsit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yltx.modulebase.base.BaseFragment;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulebase.widget.AbsDialog;
import com.yltx.modulebase.widget.ViewConvertListener;
import com.yltx.modulebase.widget.ViewHolder;
import com.yltx.modulewd.R;
import com.yltx.modulewd.borrow.amortization.AmortizationActivity;
import com.yltx.modulewd.borrow.detail.BorrowDetailActivity;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.PaymentRecords;
import com.yltx.modulewd.net.RxRetrofit;
import com.yltx.modulewd.widght.MyItemDecoration;
import com.yltx.modulewd.widght.PayDialog;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private SmartRefreshLayout refreshlayout;
    private RecyclerView recyclerview;

    private OnFragmentInteractionListener mListener;

    private List<MySection> listData = new ArrayList<>();
    private ListAdapter adapter;
    private int pageSize = 5;
    private int pageNumber = 1;
    int totalSize;
    private String totalStatus;

    public ListFragment() {
    }

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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
        loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), null, pageNumber);
    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {
        refreshlayout = (SmartRefreshLayout) rootView.findViewById(R.id.refreshlayout);
        recyclerview = (RecyclerView) rootView.findViewById(R.id.recyclerview);
    }

    @Override
    protected void initData() {
        try {
            totalSize = Integer.valueOf(ACache.get(mContext).getAsString("RepayNum"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.addItemDecoration(new MyItemDecoration());
        adapter = new ListAdapter(R.layout.rv_list_item, R.layout.rv_list_head, listData, mParam1);
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MySection section = null;
                String id = null;
                try {
                    section = (MySection) adapter.getData().get(position);
                    id = section.t.getId();
                } catch (Exception e) {
                    return;
                }
                if (null == section || null == section.t || TextUtils.isEmpty(id)) {
                    Toast.makeText(mContext, "数据异常", Toast.LENGTH_SHORT).show();
                    return;
                }

                int num = 0;
                try {
                    num = Integer.valueOf(section.t.num);
                } catch (NumberFormatException e) {
                }
                if (num > 0) {
                    Intent intent = new Intent(mContext, AmortizationActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("uuid", section.t.getUuid());
                    intent.putExtra("type", "payoff");
                    startActivity(intent);
                    ;
                } else {
                    Intent intent = new Intent(mContext, BorrowDetailActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        });
        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNumber = 1;
                loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), totalStatus, pageNumber);
            }
        });

        refreshlayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (pageNumber * pageSize > totalSize) {
                    Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                    refreshlayout.finishLoadmore();
                    return;
                }
                ++pageNumber;
                loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), totalStatus, pageNumber);
            }
        });

    }


    private void loadData(String memberid, String totalStatus, int pageNumber) {
        RxRetrofit.getInstance().getApiService().getPaymentRecords(memberid, totalStatus, pageNumber, pageSize)
                .compose(this.<PaymentRecords>bindToLifecycle())
                .compose(RxSchedulers.<PaymentRecords>io_main())
                .subscribe(new NetObserver<PaymentRecords>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, PaymentRecords paymentRecords) {
                        refreshlayout.finishRefresh();
                        if ("success".equals(paymentRecords.getCode())) {
                            refreshUI(paymentRecords.getData());
                        } else {
                            Toast.makeText(mContext, "" + paymentRecords.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {
                        refreshlayout.finishRefresh();
                    }
                });
    }

    private void refreshUI(PaymentRecords.DataBean data) {
        List<MySection> list = new ArrayList<>();
        for (PaymentRecords.DataBean.PaymentsBean paymentsBean : data.getPayments()) {
            list.add(new MySection(true, paymentsBean.getYm()));
            for (PaymentRecords.DataBean.PaymentsBean.RecordsBean recordsBean : paymentsBean.getRecords()) {
                list.add(new MySection(new Record(recordsBean.getCurNum(), recordsBean.getRepaymentId(),
                        recordsBean.getBorrowId(), recordsBean.getThisRepayment(), recordsBean.getTotalAmount(),
                        recordsBean.getPaymentTime(), recordsBean.getCurNum(),
                        recordsBean.getStatus())));
            }
        }
        if (1 == pageNumber) {
            listData.clear();
            listData = list;
            adapter.setNewData(listData);
        } else {
            listData.removeAll(list);
            listData.addAll(list);
            adapter.setNewData(listData);
        }
//        ((ListActivity) mContext).updateNum(null, "" + listData.size());
    }


    private void showPayDialgo() {
        PayDialog.newInstance()
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final AbsDialog dialog) {

                        holder.getView(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        holder.getView(R.id.tv_pay_credit).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view);
                            }
                        });
                        holder.getView(R.id.tv_pay_save).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view);
                            }
                        });
                        holder.getView(R.id.tv_pay_wechat).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view);
                            }
                        });
                        holder.getView(R.id.tv_pay_ali).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view);
                            }
                        });

                    }
                })
                .setDimAmount(0.3f)
                .setAnimStyle(R.style.DialogButtonStyle)
                .setShowBottom(true)
                .show(getActivity().getSupportFragmentManager());
    }

    private void dialogItem(ViewHolder holder, View view) {
        clearAllViewStatus(holder);
        viewStatus(view);
    }

    private void clearAllViewStatus(ViewHolder holder) {
        clearViewStatus(holder.getView(R.id.tv_pay_credit));
        clearViewStatus(holder.getView(R.id.tv_pay_save));
        clearViewStatus(holder.getView(R.id.tv_pay_wechat));
        clearViewStatus(holder.getView(R.id.tv_pay_ali));
    }

    private void clearViewStatus(View view) {
        ((TextView) view).setTextColor(ContextCompat.getColor(mContext, R.color.tv3));
        view.setSelected(false);
    }

    private void viewStatus(View view) {
        ((TextView) view).setTextColor(ContextCompat.getColor(mContext, R.color.tvo));
        view.setSelected(true);
    }


    public void onButtonPressed(String status) {
        if (mListener != null) {
            mListener.onFragmentInteraction(status);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String status);
    }

    public void activityToListFragment(String status) {
        totalStatus = status;
        pageNumber = 1;
        loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), totalStatus, pageNumber);
    }
}
