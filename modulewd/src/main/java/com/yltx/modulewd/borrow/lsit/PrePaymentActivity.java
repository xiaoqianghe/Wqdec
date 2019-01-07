package com.yltx.modulewd.borrow.lsit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.allen.library.SuperButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulebase.util.MD5;
import com.yltx.modulebase.widget.AbsDialog;
import com.yltx.modulebase.widget.ViewConvertListener;
import com.yltx.modulebase.widget.ViewHolder;
import com.yltx.modulewd.R;
import com.yltx.modulewd.authentication.ali.AuthResult;
import com.yltx.modulewd.authentication.ali.PayResult;
import com.yltx.modulewd.authentication.wx.WeChatBean;
import com.yltx.modulewd.borrow.borrowoperate.OperateStatusActivity;
import com.yltx.modulewd.borrow.detail.BorrowDetailActivity;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.BorrowList;
import com.yltx.modulewd.entity.Order;
import com.yltx.modulewd.entity.OrderResult;
import com.yltx.modulewd.net.RxRetrofit;
import com.yltx.modulewd.widght.FilterDialog;
import com.yltx.modulewd.widght.LoadingStatusView;
import com.yltx.modulewd.widght.MyItemDecoration;
import com.yltx.modulewd.widght.PassWord3Dialog;
import com.yltx.modulewd.widght.PassWordDialog;
import com.yltx.modulewd.widght.PayDialog;
import com.yltx.modulewd.widght.kb.OnPasswordInputFinish;
import com.yltx.modulewd.widght.kb.Password3View;
import com.yltx.modulewd.widght.kb.PasswordView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrePaymentActivity extends BaseActivity {

    private SmartRefreshLayout refreshlayout;
    private TextView toolbar_subtitle;
    private CheckBox checkbox;
    private TextView tv_rvtitle;
    private RecyclerView recyclerview;
    private TextView tv_count;
    private TextView tv_total_count;
    private TextView tv_money;
    private SuperButton btn_do;
    private RelativeLayout rl_bottom;

    private ListFragment.OnFragmentInteractionListener mListener;

    private List<MySection> listData = new ArrayList<>();
    private SectionAdapter adapter;
    private int curDialogItem;
    private int mCount;
    private double mTotalCount;
    private double mMoney;

    @Override
    protected int initLayout() {
        return R.layout.activity_pre_payment;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "提前还款", "筛选");
        refreshlayout = (SmartRefreshLayout) findViewById(R.id.refreshlayout);
        toolbar_subtitle = (TextView) findViewById(R.id.toolbar_subtitle);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        tv_rvtitle = (TextView) findViewById(R.id.tv_rvtitle);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        tv_count = (TextView) findViewById(R.id.tv_count);
        tv_total_count = (TextView) findViewById(R.id.tv_total_count);
        tv_money = (TextView) findViewById(R.id.tv_money);
        btn_do = (SuperButton) findViewById(R.id.btn_do);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);
        toolbar_subtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogFilter();
            }
        });
    }

    @Override
    protected void initData() {
        try {
            totalSize = Integer.valueOf(ACache.get(mContext).getAsString("ToReBoNum"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.addItemDecoration(new MyItemDecoration());
        adapter = new SectionAdapter(R.layout.rv_list_item, R.layout.rv_list_head, listData, "借款");
        recyclerview.setAdapter(adapter);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    adapter.addAllCheck();
                } else {
                    adapter.removeAllCheck();
                }
                updateTotal();
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, final View view, final int position) {
                if (view.getId() == R.id.cb_item) {
                    if (((CheckBox) view).isChecked()) {
                        ((MySection) adapter.getItem(position)).t.setCheck(true);
                    } else {
                        ((MySection) adapter.getItem(position)).t.setCheck(false);
                    }
                    updateTotal();
                }
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = ((MySection) adapter.getData().get(position)).t.getId();
                if (TextUtils.isEmpty(id)) {
                    Toast.makeText(PrePaymentActivity.this, "数据异常", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(mContext, BorrowDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });


        btn_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (0 == listId.size()) {
                    Toast.makeText(mContext, "请选择还款", Toast.LENGTH_SHORT).show();
                    return;
                }
                showPayDialgo();
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
                    Toast.makeText(PrePaymentActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    refreshlayout.finishLoadmore();
                    return;
                }
                ++pageNumber;
                loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), totalStatus, pageNumber);
            }
        });

        loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), totalStatus, pageNumber);

    }

    int pageSize = 5;
    int pageNumber = 1;
    int totalSize;
    private String totalStatus = null;

    private void loadData(String memberid, String totalStatus, int pageNumber) {
        RxRetrofit.getInstance().getApiService().getBorrowList(memberid, totalStatus, pageSize, pageNumber)
                .compose(this.<BorrowList>bindToLifecycle())
                .compose(RxSchedulers.<BorrowList>io_main())
                .subscribe(new NetObserver<BorrowList>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, BorrowList borrowList) {
                        loadFinish();
                        if ("success".equals(borrowList.getCode())) {
                            refreshUI(borrowList.getData());
                        } else {
                            Toast.makeText(mContext, "" + borrowList.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void refreshUI(List<BorrowList.DataBean> data) {
        if (null == data)
            return;
        String totalCount = "0";
        List<MySection> list = new ArrayList<>();
        for (BorrowList.DataBean dataBean : data) {
            list.add(new MySection(true, dataBean.getBorrowYearMonth()));
            for (BorrowList.DataBean.BorrowsBean borrowsBean : dataBean.getBorrows()) {
                list.add(new MySection(new Record(borrowsBean.getBorrowId(), borrowsBean.getToRepayment(), borrowsBean.getBorrowLimit(),
                        borrowsBean.getBorrowDate(), borrowsBean.getSurplusNum(), borrowsBean.getTotalStatus(), borrowsBean.getOverdueFine())));
                totalCount = borrowsBean.getTotalCount();
            }
        }
        tv_rvtitle.setText("当前共有" + totalCount + "笔借款");
        if (1 == pageNumber) {
            listData.clear();
            listData = list;
            adapter.setNewData(listData);
        } else {
            listData.removeAll(list);
            listData.addAll(list);
            adapter.setNewData(listData);
        }
    }

    private void dialogFilter() {
        FilterDialog.newInstance()
                .setLayoutId(R.layout.dialog_filter)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final AbsDialog dialog) {
                        if (0 != curDialogItem) {
                            clearAllViewStatusFilter(holder);
                            viewStatusFilter(holder.getView(curDialogItem));
                        }
                        holder.getView(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        holder.getView(R.id.tv_all).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItemFilter(holder, view, dialog);
                                totalStatus = null;
                            }
                        });
                        holder.getView(R.id.tv_for_lend).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItemFilter(holder, view, dialog);
                                totalStatus = "2";
                            }
                        });
                        holder.getView(R.id.tv_loan).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItemFilter(holder, view, dialog);
                                totalStatus = "3";
                            }
                        });
                        holder.getView(R.id.tv_check_pending).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItemFilter(holder, view, dialog);
                                totalStatus = "1";
                            }
                        });
                        holder.getView(R.id.tv_overdue).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItemFilter(holder, view, dialog);
                                totalStatus = "6";
                            }
                        });
                        holder.getView(R.id.tv_brrow_fail).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItemFilter(holder, view, dialog);
                                totalStatus = "4";
                            }
                        });
                    }
                })
                .setDimAmount(0.3f)
                .setAnimStyle(R.style.DialogButtonStyle)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    private String payType;

    private void showPayDialgo() {
        PayDialog.newInstance()
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final AbsDialog dialog) {
                        ((TextView) holder.getView(R.id.tv_money)).setText("" + mMoney);
                        ((TextView) holder.getView(R.id.tv_info)).setText("提前还款");
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
                                payType = "depositPay";
                            }
                        });
                        holder.getView(R.id.tv_pay_save).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view);
                                payType = "creditPay";
                            }
                        });
                        holder.getView(R.id.tv_pay_wechat).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view);
                                payType = "wxPay";
                            }
                        });
                        holder.getView(R.id.tv_pay_ali).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view);
                                payType = "aliPay";
                            }
                        });
                        holder.getView(R.id.btn_do).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (TextUtils.isEmpty(payType)) {
                                    Toast.makeText(mContext, "请选择支付方式", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                dialog.dismiss();
                                createOrder(payType);
                            }
                        });

                    }
                })
                .setDimAmount(0.3f)
                .setAnimStyle(R.style.DialogButtonStyle)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }


    private boolean netdialog;

    private void createOrder(final String type) {
        Order order = new Order();
        order.setMemberId(ACache.get(mContext).getAsString(UserInfo.USER_ID));
        order.setOrderType("repayment");
        order.setPayType(type);
        order.setBorrowIds(listId);
        switch (type) {
            case "depositPay":
                netdialog = false;
                dialogPwd(order);
                break;
            case "creditPay":
                netdialog = false;
                dialogPwd(order);
                break;
            case "wxPay":
                netdialog = true;
                addOrder(order);
                break;
            case "aliPay":
                netdialog = true;
                addOrder(order);
                break;
        }

    }

    LoadingStatusView statusView;
    AbsDialog statusDialog;
    TextView statusTip;

    private void dialogPwd(final Order order) {
        PassWordDialog.newInstance()
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final AbsDialog dialog) {
                        statusDialog = dialog;
                        holder.getView(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                        ((PasswordView) holder.getView(R.id.pwd_view)).setOnFinishInput(new OnPasswordInputFinish() {
                            @Override
                            public void inputFinish(String password) {
                                if ("depositPay".equals(payType)) {
                                    order.setPaymentPassword(MD5.getMessageDigest(password.getBytes()));
                                    dialog3Pwd(order);
                                    dialog.dismiss();
                                }
                                if ("creditPay".equals(payType)) {
                                    order.setPaymentPassword(MD5.getMessageDigest(password.getBytes()));
                                    holder.getView(R.id.ll_content).setVisibility(View.INVISIBLE);
                                    holder.getView(R.id.ll_load).setVisibility(View.VISIBLE);
                                    statusView = (LoadingStatusView) holder.getView(R.id.statusView);
                                    statusTip = holder.getView(R.id.statusTip);
                                    statusView.loadLoading();
                                    addOrder(order);
                                }

                            }
                        });
                    }
                })
                .setDimAmount(0.3f)
                .setAnimStyle(R.style.DialogButtonStyle)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    LoadingStatusView statusView3;
    AbsDialog statusDialog3;
    TextView statusTip3;

    private void dialog3Pwd(final Order order) {
        PassWord3Dialog.newInstance()
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final AbsDialog dialog) {
                        statusDialog3 = dialog;
                        holder.getView(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                        ((Password3View) holder.getView(R.id.pwd_view)).setOnFinishInput(new OnPasswordInputFinish() {
                            @Override
                            public void inputFinish(String password) {
                                order.setCvn2Code(MD5.getMessageDigest(password.getBytes()));
                                holder.getView(R.id.ll_content).setVisibility(View.INVISIBLE);
                                holder.getView(R.id.ll_load).setVisibility(View.VISIBLE);
                                statusView3 = (LoadingStatusView) holder.getView(R.id.statusView);
                                statusTip3 = holder.getView(R.id.statusTip);
                                statusView3.loadLoading();
                                addOrder(order);
                            }
                        });
                    }
                })
                .setDimAmount(0.3f)
                .setAnimStyle(R.style.DialogButtonStyle)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    private void addOrder(Order order) {
        RxRetrofit.getInstance().getApiService().addOrder(order)
                .compose(this.<OrderResult>bindToLifecycle())
                .compose(RxSchedulers.<OrderResult>io_main())
                .subscribe(new NetObserver<OrderResult>(mContext, TAG, 0, netdialog) {
                    @Override
                    public void onSuccess(int whichRequest, OrderResult orderResult) {
                        if ("success".equals(orderResult.getCode())) {
                            setOrderInfo(orderResult);
                            if ("depositPay".equals(payType)) {
                                statusView3.loadSuccess();
                                statusTip3.setText("支付成功");
                                statusTip3.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        statusDialog3.dismiss();
                                        toSuccess();
                                    }
                                }, 2000);
                            }
                            if ("creditPay".equals(payType)) {
                                statusView.loadSuccess();
                                statusTip.setText("支付成功");
                                statusTip.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        statusDialog.dismiss();
                                        toSuccess();
                                    }
                                }, 2000);
                            }
                            if ("aliPay".equals(payType)) {
                                aliPay(orderResult.getData().getAliPaySign());
                            }
                            if ("wxPay".equals(payType)) {
                                wechatPay(orderResult.getData().getWeixinRs());
                            }
//                            pageNumber = 1;
//                            loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), totalStatus, pageSize, pageNumber);
                        } else {
                            if ("depositPay".equals(payType)) {
                                statusView3.loadFailure();
                                statusTip3.setText(orderResult.getMessage());
                                statusTip3.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        statusDialog.dismiss();
                                        //starttonext
                                    }
                                }, 2000);
                            }
                            if ("creditPay".equals(payType)) {
                                statusView.loadSuccess();
                                statusTip.setText(orderResult.getMessage());
                                statusTip.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        statusDialog.dismiss();
                                        //starttonext
                                    }
                                }, 2000);
                            }
                            Toast.makeText(mContext, "" + orderResult.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {
                        if ("depositPay".equals(payType)) {
                            statusView3.loadFailure();
                            statusTip3.setText("支付失败");
                            statusTip3.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    statusDialog3.dismiss();
                                }
                            }, 2000);
                        }
                        if ("creditPay".equals(payType)) {
                            statusView.loadFailure();
                            statusTip.setText("支付失败");
                            statusTip.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    statusDialog.dismiss();
                                }
                            }, 2000);
                        }
                    }
                });
    }

    private void setOrderInfo(OrderResult orderResult) {
        ACache.get(mContext).put("AddOrderMoney", ""+orderResult.getData().getTotalAmount());
        ACache.get(mContext).put("AddOrderPayNo", ""+orderResult.getData().getPayNo());
        ACache.get(mContext).put("AddOrderTime", ""+orderResult.getData().getCreateDate());
    }

    private void toSuccess(){
        ACache.get(mContext).put("OperateStatusFrom", "repayment");
        Intent intent = new Intent(mContext, OperateStatusActivity.class);
        startActivity(intent);
        finish();
    }

    private List<String> listId = new ArrayList<>();

    private void updateTotal() {
        listId.clear();
        mCount = 0;
        mTotalCount = 0.0;
        mMoney = 0.0;
        for (MySection mySection : adapter.getData()) {
            if (!mySection.isHeader) {
                if (mySection.t.isCheck) {
                    mCount++;
                    mMoney += Double.valueOf(mySection.t.getTitleMoney());
                    mTotalCount += Double.valueOf(mySection.t.getOverdue());
                    listId.add(mySection.t.id);
                }
            }
        }
        tv_count.setText("" + mCount);
        tv_total_count.setText("" + mTotalCount);
        tv_money.setText("" + mMoney);
        if (listId.size() > 0) {
            btn_do.setEnabled(true);
        } else {
            btn_do.setEnabled(false);
        }
    }

    /*******************************************************************************/

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


    /**
     * filterdialog
     */
    private void dialogItemFilter(ViewHolder holder, View view, final AbsDialog dialog) {
        curDialogItem = view.getId();
        clearAllViewStatusFilter(holder);
        viewStatusFilter(view);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                pageNumber = 1;
                loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), totalStatus, pageNumber);
            }
        }, 200);
    }

    private void clearAllViewStatusFilter(ViewHolder holder) {

        clearViewStatusFilter(holder.getView(R.id.tv_all));
        clearViewStatusFilter(holder.getView(R.id.tv_for_lend));
        clearViewStatusFilter(holder.getView(R.id.tv_loan));
        clearViewStatusFilter(holder.getView(R.id.tv_check_pending));
        clearViewStatusFilter(holder.getView(R.id.tv_overdue));
        clearViewStatusFilter(holder.getView(R.id.tv_brrow_fail));

    }

    private void clearViewStatusFilter(View view) {
        ((TextView) view).setTextColor(ContextCompat.getColor(mContext, R.color.tv3));
        view.setSelected(false);
    }

    private void viewStatusFilter(View view) {
        ((TextView) view).setTextColor(ContextCompat.getColor(mContext, R.color.tvo));
        view.setSelected(true);
    }


    private IWXAPI wxapi;
    private boolean isWxSupport = false;

    private void wechatPay(String info) {
        ACache.get(mContext).put("WXTYPE", "REPAYMENT");
//        info = "{\"sign\":\"5488B10FA72E809BE036DA976AF43F15\",\"timeStamp\":\"1507883258\",\"packageStr\":\"Sign=WXPay\",\"partnerId\":\"1486234152\",\"appid\":\"wxbfa4282e5eecb703\",\"nonceStr\":\"29ff1j18j4mijk98xoug4peqxpfwjmnm\",\"prepayId\":\"wx20171013162800a8c171666b0643468807\"}";
        WeChatBean weChatBean = new Gson().fromJson(info, WeChatBean.class);
        String appId = weChatBean.getAppid();
        ACache.get(mContext).put("WXAPPID", appId);
        wxapi = WXAPIFactory.createWXAPI(this, appId);
        wxapi.registerApp(appId);

        PayReq req = new PayReq();
        req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
//        req.appId = appId;
        req.partnerId = weChatBean.getPartnerId();
        req.prepayId = weChatBean.getPrepayId();
        req.nonceStr = weChatBean.getNonceStr();
        req.timeStamp = weChatBean.getTimeStamp();
        req.packageValue = weChatBean.getPackageStr();
        req.sign = weChatBean.getSign();
        req.extData = "app data"; // optional
        wxapi.sendReq(req);
    }

    private boolean isWXAppInstalledAndSupported() {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
        msgApi.registerApp("wxbfa4282e5eecb703");
        boolean sIsWxAppInstalledAndSupported = msgApi.isWXAppInstalled();
        return sIsWxAppInstalledAndSupported;
    }


    private void aliPay(String info) {
        final String orderInfo = info;
//        final String orderInfo = "alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017071407749559&biz_content=%7B%22body%22%3A%22%E5%B0%8F%E8%9C%9C%E7%99%BD%E5%8D%A1%E4%BC%9A%E5%91%98%E5%B9%B4%E8%B4%B9%E5%A5%97%E9%A4%90%E8%B4%B9%22%2C%22out_trade_no%22%3A%22deposit201710131624251523%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%B0%8F%E8%9C%9C%E7%99%BD%E5%8D%A1%E4%BC%9A%E5%91%98%E5%B9%B4%E8%B4%B9%E5%A5%97%E9%A4%90%E8%B4%B9%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F14.21.42.99%3A15555%2Floan%2Fwx%2Findex%21notifyByAli.action&sign=cUQZYEkSWsV%2B7HImNLaX2OvHzwyjFE2UlULRE8HwsOvW7CyKFk%2BWH0Kf%2B66LNleWsdAqHt0voRUoU3p%2FiJPuy4GOh4qUwTuQiOWJjE%2BWZS6JV15GPQsscomcfrkwUgSeAMqzvW%2BukVKK6WnNWq1GKdzUDs1w2afWgWeDHwt6vUGh9FptSuGY9aU%2BfRO04f52dEE6LGEeNetz5Kk9KiIIPmDoeHd3lQzH8wMXqZtzu%2B9g2vNNjglhlBGNxAcJMGgyHYOLM5V2Ug5uGzp8JOS1nhjn3%2BQTCPZLs71P2GA33OpuLm%2FX8wtRGnbf%2FfJxaUOCzkjfoCDt0y7PdeMSFFCWOA%3D%3D&sign_type=RSA2&timestamp=2017-10-13+16%3A24%3A25&version=1.0";
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(PrePaymentActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PrePaymentActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(mContext, OperateStatusActivity.class));
//                        finish();
                        toSuccess();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PrePaymentActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(PrePaymentActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(PrePaymentActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
}
