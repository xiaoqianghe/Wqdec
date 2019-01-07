package com.yltx.modulewd.borrow.amortization;

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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.allen.library.SuperButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
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
import com.yltx.modulewd.entity.InstallmentDetail;
import com.yltx.modulewd.entity.Order;
import com.yltx.modulewd.entity.OrderResult;
import com.yltx.modulewd.net.RxRetrofit;
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

public class AmortizationActivity extends BaseActivity {


    private RecyclerView recyclerview;
    private CheckBox checkbox;
    private TextView tv_count;
    private TextView tv_total_count;
    private TextView tv_money;
    private SuperButton btn_do;
    private RelativeLayout rl_bottom;
    private TextView tvh_top;
    private TextView tvh_money;
    private TextView tvh_tip;
    private TextView tvh_info;
    private LinearLayout ll_paymore;
    private LinearLayout ll_paysingle;
    private LinearLayout ll_payoff;

    private AmortizationAdapter adapter;
    private List<InstallmentDetail.DataBean.RepaymentListBean> listData = new ArrayList<>();
    private int mCount;
    private double mTotalCount;
    private double mMoney;
    String id;
    String uuid;
    String type;

    @Override
    protected int initLayout() {
        return R.layout.activity_amortization;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "分期还款", null);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        tv_count = (TextView) findViewById(R.id.tv_count);
        tv_total_count = (TextView) findViewById(R.id.tv_total_count);
        tv_money = (TextView) findViewById(R.id.tv_money);
        btn_do = (SuperButton) findViewById(R.id.btn_do);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        uuid = getIntent().getStringExtra("uuid");
        type = getIntent().getStringExtra("type");

        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerview.addItemDecoration(new MyItemDecoration());
        adapter = new AmortizationAdapter(type);
        View headView = View.inflate(this, R.layout.rv_amortization_head, null);
        tvh_top = headView.findViewById(R.id.tv_top);
        tvh_money = headView.findViewById(R.id.tv_money);
        tvh_tip = headView.findViewById(R.id.tv_tip);
        tvh_info = headView.findViewById(R.id.tv_info);
        ll_paymore = headView.findViewById(R.id.ll_paymore);
        ll_paysingle = headView.findViewById(R.id.ll_paysingle);
        ll_payoff = headView.findViewById(R.id.ll_payoff);
        if ("more".equals(type)) {
            rl_bottom.setVisibility(View.VISIBLE);
            ll_paymore.setVisibility(View.VISIBLE);
            ll_paysingle.setVisibility(View.GONE);
            ll_payoff.setVisibility(View.GONE);
        } else if ("single".equals(type)) {
            rl_bottom.setVisibility(View.VISIBLE);
            ll_paymore.setVisibility(View.GONE);
            ll_paysingle.setVisibility(View.VISIBLE);
            ll_payoff.setVisibility(View.GONE);
        } else {
            rl_bottom.setVisibility(View.GONE);
            ll_paymore.setVisibility(View.GONE);
            ll_paysingle.setVisibility(View.GONE);
            ll_payoff.setVisibility(View.VISIBLE);
        }
        adapter.addHeaderView(headView);
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
            public void onItemChildClick(final BaseQuickAdapter madapter, final View view, final int position) {
                if (view.getId() == R.id.cb_item) {
//                    if (((CheckBox) view).isChecked()) {
////                        ((MySection) adapter.getItem(position)).t.setCheck(true);
//                    } else {
////                        ((MySection) adapter.getItem(position)).t.setCheck(false);
//                    }
                    adapter.addCheck(position, ((CheckBox) view).isChecked());
                    updateTotal();
                }
                if (view.getId() == R.id.tv_detail) {
                    String id = adapter.getData().get(position).getBorrowId();
                    if (TextUtils.isEmpty(id)) {
                        Toast.makeText(AmortizationActivity.this, "数据异常", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(mContext, BorrowDetailActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    finish();
                }
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
        if ("single".equals(type)) {
            loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), id, null);
        } else if ("more".equals(type)) {
            loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), id);
        } else {
            loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), id, uuid);
        }
    }


    private void loadData(String mbId, String repayDate) {
        RxRetrofit.getInstance().getApiService().installmentDetail(mbId, repayDate)
                .compose(this.<InstallmentDetail>bindToLifecycle())
                .compose(RxSchedulers.<InstallmentDetail>io_main())
                .subscribe(new NetObserver<InstallmentDetail>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, InstallmentDetail installmentDetail) {
                        if ("success".equals(installmentDetail.getCode())) {
                            refreshUI(installmentDetail.getData());
                        } else {
                            Toast.makeText(mContext, "" + installmentDetail.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    private void loadData(String mbId, String borrowId, String repayId) {
        RxRetrofit.getInstance().getApiService().installmentLoanDetail(mbId, borrowId, repayId)
                .compose(this.<InstallmentDetail>bindToLifecycle())
                .compose(RxSchedulers.<InstallmentDetail>io_main())
                .subscribe(new NetObserver<InstallmentDetail>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, InstallmentDetail installmentDetail) {
                        if ("success".equals(installmentDetail.getCode())) {
                            refreshUI(installmentDetail.getData());
                        } else {
                            Toast.makeText(mContext, "" + installmentDetail.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    private void refreshUI(InstallmentDetail.DataBean dataBean) {
        listData.clear();
        listData = dataBean.getRepaymentList();
        adapter.setNewData(listData);
        if ("single".equals(type)) {
            tvh_top.setText(dataBean.getRepaymentDate() + " 待还 | " + dataBean.getRepayNum() + "笔分期借款");
            tvh_money.setText("￥" + dataBean.getSurplusTotalAmount());
            tvh_tip.setText("还款日当天系统将自动从尾号" + ACache.get(mContext).getAsString(UserInfo.USER_BANK_CARD) + "银行卡扣款");
        } else if ("more".equals(type)) {
            tvh_top.setText(dataBean.getRepaymentDate() + " 待还 | " + dataBean.getRepayNum() + "笔分期借款");
            tvh_money.setText("￥" + dataBean.getSurplusTotalAmount());
            tvh_tip.setText("还款日当天系统将自动从尾号" + ACache.get(mContext).getAsString(UserInfo.USER_BANK_CARD) + "银行卡扣款");
        } else {
            tvh_info.setText("本期" + dataBean.getRepayNum() + "笔分期借款已还清");
        }

    }

    private List<String> listId = new ArrayList<>();

    private void updateTotal() {
        listId.clear();
        mCount = 0;
        mTotalCount = 0.0;
        mMoney = 0.0;
        for (int i = 0; i < adapter.getData().size(); i++) {
            if (adapter.isCheck(i)) {
                mCount++;
                mMoney += Double.valueOf(adapter.getData().get(i).getTotalAmount());
                mTotalCount += Double.valueOf(adapter.getData().get(i).getInterest());
                listId.add(adapter.getData().get(i).getBorrowId());
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

    private String payType;

    private void showPayDialgo() {
        PayDialog.newInstance()
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final AbsDialog dialog) {
                        ((TextView) holder.getView(R.id.tv_money)).setText("" + mMoney);
                        ((TextView) holder.getView(R.id.tv_info)).setText("分期还款");
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


    private void toSuccess() {
        ACache.get(mContext).put("OperateStatusFrom", "repayment");
        Intent intent = new Intent(mContext, OperateStatusActivity.class);
        startActivity(intent);
        finish();
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
//        req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
        req.appId = appId;
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
                PayTask alipay = new PayTask(AmortizationActivity.this);
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
                        Toast.makeText(AmortizationActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        toSuccess();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(AmortizationActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(AmortizationActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(AmortizationActivity.this,
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
