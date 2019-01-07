package com.yltx.modulewd.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperButton;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulebase.util.TimeCount;
import com.yltx.modulewd.R;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.MsgByPhone;
import com.yltx.modulewd.entity.ZhimaCreditStatus;
import com.yltx.modulewd.net.RxRetrofit;

public class SesameCreditIActivity extends BaseActivity {
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

    private TextView tv_name;
    private TextView tv_id;
    private TextView tv_phone;
    private EditText et_sms;
    private SuperButton btn_sms;
    private SuperButton btn_do;

    @Override
    protected int initLayout() {
        return R.layout.activity_sesame_credit_i;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "芝麻信用评分授权", null);
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

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        et_sms = (EditText) findViewById(R.id.et_sms);
        btn_sms = (SuperButton) findViewById(R.id.btn_sms);
        btn_do = (SuperButton) findViewById(R.id.btn_do);
        btn_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_sms.getText().toString())) {
                    Toast.makeText(mContext, "请输短信入验证码", Toast.LENGTH_SHORT).show();
                } else {
                    loadData();
                }
            }
        });
        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSMS();
            }
        });
        sesameProgress();
    }

    private void loadData() {
        RxRetrofit.getInstance().getApiService().getZhimaCreditStatus(ACache.get(mContext).getAsString(UserInfo.USER_ID), et_sms.getText().toString())
                .compose(this.<ZhimaCreditStatus>bindToLifecycle())
                .compose(RxSchedulers.<ZhimaCreditStatus>io_main())
                .subscribe(new NetObserver<ZhimaCreditStatus>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, ZhimaCreditStatus zhimaCreditStatus) {
                        if ("success".equals(zhimaCreditStatus.getCode())) {
                            startActivity(new Intent(mContext, SesameCreditYActivity.class));
                            finish();
                        } else {
                            Toast.makeText(mContext, "" + zhimaCreditStatus.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    private void getSMS() {
        RxRetrofit.getInstance().getApiService().sendMsgByPhone(ACache.get(mContext).getAsString(UserInfo.USER_ID))
                .compose(this.<MsgByPhone>bindToLifecycle())
                .compose(RxSchedulers.<MsgByPhone>io_main())
                .subscribe(new NetObserver<MsgByPhone>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, MsgByPhone msgByPhone) {
                        if ("success".equals(msgByPhone.getCode())) {
                            refreshUI();
                        } else {
                            Toast.makeText(mContext, "" + msgByPhone.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    private void refreshUI() {
        //倒计时
        new TimeCount(btn_sms, 5 * 1000, 1000).start();
    }

    private void sesameProgress() {
        iv_ca.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_y));
        line_ca_r.setBackgroundColor(ContextCompat.getColor(mContext, R.color.tvo));
        tv_ca.setTextColor(ContextCompat.getColor(mContext, R.color.tv6));
        line_score_l.setBackgroundColor(ContextCompat.getColor(mContext, R.color.tvo));
        iv_score.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_n));
        line_score_r.setBackgroundColor(ContextCompat.getColor(mContext, R.color.line_progress));
        tv_score.setTextColor(ContextCompat.getColor(mContext, R.color.tv6));
        line_card_l.setBackgroundColor(ContextCompat.getColor(mContext, R.color.line_progress));
        iv_card.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_d));
        line_card_r.setBackgroundColor(ContextCompat.getColor(mContext, R.color.line_progress));
        tv_card.setTextColor(ContextCompat.getColor(mContext, R.color.tv9));
        iv_vip.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_d));
        line_vip_l.setBackgroundColor(ContextCompat.getColor(mContext, R.color.line_progress));
        tv_vip.setTextColor(ContextCompat.getColor(mContext, R.color.tv9));
    }

    @Override
    protected void initData() {
        tv_name.setText("" + ACache.get(mContext).getAsString(UserInfo.USER_NAME));
        tv_id.setText("" + ACache.get(mContext).getAsString(UserInfo.USER_ID_CARD));
        tv_phone.setText("" + ACache.get(mContext).getAsString(UserInfo.USER_PHONE));
    }
}
