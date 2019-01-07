package com.yltx.modulewd.authentication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.allen.library.SuperButton;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulewd.R;
import com.yltx.modulewd.authentication.ali.AuthResult;
import com.yltx.modulewd.authentication.ali.PayResult;
import com.yltx.modulewd.authentication.wx.WeChatBean;
import com.yltx.modulewd.constant.OrderInfo;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.AnnualFeeInfo;
import com.yltx.modulewd.entity.Order;
import com.yltx.modulewd.entity.OrderResult;
import com.yltx.modulewd.net.RxRetrofit;

import java.util.Map;

public class OpenMemberActivity extends BaseActivity {

    private ImageView iv_ca;
    private View line_ca_r;
    private TextView tv_ca;
    private View line_score_l;
    private ImageView iv_score;
    private View line_score_r;
    private TextView tv_score;
    private View line_card_l;
    private ImageView iv_card;
    private View line_card_r;
    private TextView tv_card;
    private ImageView iv_vip;
    private View line_vip_l;
    private TextView tv_vip;
    private SuperButton btn_do;
    private TextView tv_annualfee;
    private TextView tv_trem;
    private TextView tv_id;
    private RadioGroup radiogroup;
    private RadioButton rb_ali;
    private RadioButton rb_wecat;
    private TextView tv_wechat_tip;
    private CheckBox checkBox;
    private TextView tv_protocol;

    private IWXAPI wxapi;
    private String payType = "aliPay";
    private boolean isWxSupport = false;

    @Override
    protected int initLayout() {
        return R.layout.activity_open_member;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        isWxSupport = isWXAppInstalledAndSupported();
        initToolBar(true, "开通会员", null);
        iv_ca = (ImageView) findViewById(R.id.iv_ca);
        line_ca_r = findViewById(R.id.line_ca_r);
        tv_ca = (TextView) findViewById(R.id.tv_ca);
        line_score_l = findViewById(R.id.line_score_l);
        iv_score = (ImageView) findViewById(R.id.iv_score);
        line_score_r = findViewById(R.id.line_score_r);
        tv_score = (TextView) findViewById(R.id.tv_score);
        line_card_l = findViewById(R.id.line_card_l);
        iv_card = (ImageView) findViewById(R.id.iv_card);
        line_card_r = findViewById(R.id.line_card_r);
        tv_card = (TextView) findViewById(R.id.tv_card);
        iv_vip = (ImageView) findViewById(R.id.iv_vip);
        line_vip_l = findViewById(R.id.line_vip_l);
        tv_vip = (TextView) findViewById(R.id.tv_vip);

        tv_annualfee = (TextView) findViewById(R.id.tv_annualfee);
        tv_trem = (TextView) findViewById(R.id.tv_trem);
        tv_id = (TextView) findViewById(R.id.tv_id);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        rb_ali = (RadioButton) findViewById(R.id.rb_ali);
        rb_wecat = (RadioButton) findViewById(R.id.rb_wechat);
        tv_wechat_tip = (TextView) findViewById(R.id.tv_wechat_tip);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        tv_protocol = (TextView) findViewById(R.id.tv_protocol);
        btn_do = (SuperButton) findViewById(R.id.btn_do);
        btn_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fitData();
            }
        });
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.rb_ali) {
                    payType = "aliPay";
                }
                if (i == R.id.rb_wechat) {
                    payType = "wxPay";
                }
            }
        });
        if (!isWxSupport) {
            tv_wechat_tip.setVisibility(View.VISIBLE);
            rb_wecat.setEnabled(false);
        } else {
            tv_wechat_tip.setVisibility(View.GONE);
            rb_wecat.setEnabled(true);
        }
        sesameProgress();
    }

    private void fitData() {
        if (TextUtils.isEmpty(payType)) {
            Toast.makeText(mContext, "请选择支付方式", Toast.LENGTH_SHORT).show();
            return;
        }
        createOrder(payType);
    }

    private void sesameProgress() {
        iv_ca.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_y));
        line_ca_r.setBackgroundColor(ContextCompat.getColor(mContext, R.color.tvo));
        tv_ca.setTextColor(ContextCompat.getColor(mContext, R.color.tv6));
        line_score_l.setBackgroundColor(ContextCompat.getColor(mContext, R.color.tvo));
        iv_score.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_y));
        line_score_r.setBackgroundColor(ContextCompat.getColor(mContext, R.color.tvo));
        tv_score.setTextColor(ContextCompat.getColor(mContext, R.color.tv6));
        line_card_l.setBackgroundColor(ContextCompat.getColor(mContext, R.color.tvo));
        iv_card.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_y));
        line_card_r.setBackgroundColor(ContextCompat.getColor(mContext, R.color.tvo));
        tv_card.setTextColor(ContextCompat.getColor(mContext, R.color.tv6));
        iv_vip.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_n));
        line_vip_l.setBackgroundColor(ContextCompat.getColor(mContext, R.color.tvo));
        tv_vip.setTextColor(ContextCompat.getColor(mContext, R.color.tv6));
    }


    @Override
    protected void initData() {
        loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID));
    }

    private void loadData(String memberid) {
        RxRetrofit.getInstance().getApiService().getAnnualFeeInfo(memberid)
                .compose(this.<AnnualFeeInfo>bindToLifecycle())
                .compose(RxSchedulers.<AnnualFeeInfo>io_main())
                .subscribe(new NetObserver<AnnualFeeInfo>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, AnnualFeeInfo annualFeeInfo) {
                        if ("success".equals(annualFeeInfo.getCode())) {
                            refreshUI(annualFeeInfo.getData());
                        } else {
                            Toast.makeText(mContext, "" + annualFeeInfo.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    private void refreshUI(AnnualFeeInfo.DataBean data) {
        tv_id.setText("充值账号：" + ACache.get(mContext).getAsString(UserInfo.USER_ID));
        tv_annualfee.setText(data.getMemberFee() + "元/年");
        tv_trem.setText(data.getStartDate() + "~" + data.getEndDate());

    }

    private void createOrder(final String type) {
        final Order order = new Order();
        order.setMemberId(ACache.get(mContext).getAsString(UserInfo.USER_ID));
        order.setOrderType("deposit");
        order.setPayType(type);
        RxRetrofit.getInstance().getApiService().addOrder(order)
                .compose(this.<OrderResult>bindToLifecycle())
                .compose(RxSchedulers.<OrderResult>io_main())
                .subscribe(new NetObserver<OrderResult>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, OrderResult orderResult) {
                        if ("success".equals(orderResult.getCode())) {
                            payInfo(orderResult.getData());
                            if ("aliPay".equals(type)) {
                                aliPay(orderResult.getData().getAliPaySign());
                            }
                            if ("wxPay".equals(type)) {
                                wechatPay(orderResult.getData().getWeixinRs());
                            }
                        } else {
                            Toast.makeText(mContext, "" + orderResult.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    private void payInfo(OrderResult.DataBean result) {
        try {
            ACache.get(mContext).put(OrderInfo.ORDER_MONEY, result.getTotalAmount());
            ACache.get(mContext).put(OrderInfo.ORDER_TRANSACTION_TIME, result.getPayTime());
            ACache.get(mContext).put(OrderInfo.ORDER_PAY_TYPE, result.getPayType());
            ACache.get(mContext).put(OrderInfo.ORDER_NO, result.getPayNo());
        } catch (Exception e) {
        }
    }

    /**
     * 微信
     */
    private void wechatPay(String info) {
        ACache.get(mContext).put("WXTYPE", "OPENVIP");
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


    /**
     * 支付宝
     */

    private void aliPay(String info) {
        final String orderInfo = info;
//        final String orderInfo = "alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017071407749559&biz_content=%7B%22body%22%3A%22%E5%B0%8F%E8%9C%9C%E7%99%BD%E5%8D%A1%E4%BC%9A%E5%91%98%E5%B9%B4%E8%B4%B9%E5%A5%97%E9%A4%90%E8%B4%B9%22%2C%22out_trade_no%22%3A%22deposit201710131624251523%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%B0%8F%E8%9C%9C%E7%99%BD%E5%8D%A1%E4%BC%9A%E5%91%98%E5%B9%B4%E8%B4%B9%E5%A5%97%E9%A4%90%E8%B4%B9%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F14.21.42.99%3A15555%2Floan%2Fwx%2Findex%21notifyByAli.action&sign=cUQZYEkSWsV%2B7HImNLaX2OvHzwyjFE2UlULRE8HwsOvW7CyKFk%2BWH0Kf%2B66LNleWsdAqHt0voRUoU3p%2FiJPuy4GOh4qUwTuQiOWJjE%2BWZS6JV15GPQsscomcfrkwUgSeAMqzvW%2BukVKK6WnNWq1GKdzUDs1w2afWgWeDHwt6vUGh9FptSuGY9aU%2BfRO04f52dEE6LGEeNetz5Kk9KiIIPmDoeHd3lQzH8wMXqZtzu%2B9g2vNNjglhlBGNxAcJMGgyHYOLM5V2Ug5uGzp8JOS1nhjn3%2BQTCPZLs71P2GA33OpuLm%2FX8wtRGnbf%2FfJxaUOCzkjfoCDt0y7PdeMSFFCWOA%3D%3D&sign_type=RSA2&timestamp=2017-10-13+16%3A24%3A25&version=1.0";
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(OpenMemberActivity.this);
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
                        Toast.makeText(OpenMemberActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(mContext, OpenMemberYActivity.class));
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(OpenMemberActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(OpenMemberActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(OpenMemberActivity.this,
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
