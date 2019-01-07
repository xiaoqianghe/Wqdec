package com.yltx.modulewd.authentication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.content.res.AppCompatResources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperButton;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulewd.R;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.ZhimaStatus;
import com.yltx.modulewd.entity.ZhimaUrl;
import com.yltx.modulewd.net.RxRetrofit;

import java.net.URLEncoder;
import java.util.List;

public class SesameCertificationNActivity extends BaseActivity {

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

    @Override
    protected int initLayout() {
        return R.layout.activity_sesame_certification_n;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "芝麻认证", null);
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

        btn_do = (SuperButton) findViewById(R.id.btn_do);
        btn_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

        sesameProgress();
    }

    private void sesameProgress() {
        iv_ca.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_n));
        line_ca_r.setBackgroundColor(ContextCompat.getColor(mContext, R.color.line_progress));
        tv_ca.setTextColor(ContextCompat.getColor(mContext, R.color.tv6));
        line_score_l.setBackgroundColor(ContextCompat.getColor(mContext, R.color.line_progress));
        iv_score.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_d));
        line_score_r.setBackgroundColor(ContextCompat.getColor(mContext, R.color.line_progress));
        tv_score.setTextColor(ContextCompat.getColor(mContext, R.color.tv9));
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

    }

    @Override
    protected void onResume() {
        super.onResume();
        getStatus();
    }

    /**
     * 启动支付宝进行认证
     *
     * @param url 开放平台返回的URL
     */
    private void doVerify(String url) {
        if (hasApplication()) {
            Intent action = new Intent(Intent.ACTION_VIEW);
            StringBuilder builder = new StringBuilder();
            // 这里使用固定appid 20000067
            builder.append("alipays://platformapi/startapp?appId=20000067&url=");
            builder.append(URLEncoder.encode(url));
            action.setData(Uri.parse(builder.toString()));
//            startActivity(action);
            startActivity(action);
//            finish();
        } else {
            // 处理没有安装支付宝的情况
            new AlertDialog.Builder(this)
                    .setMessage("是否下载并安装支付宝完成认证?")
                    .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent action = new Intent(Intent.ACTION_VIEW);
                            action.setData(Uri.parse("https://m.alipay.com"));
                            startActivity(action);
                        }
                    }).setNegativeButton("算了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    }

    /**
     * 判断是否安装了支付宝
     *
     * @return true 为已经安装
     */
    private boolean hasApplication() {
        PackageManager manager = getPackageManager();
        Intent action = new Intent(Intent.ACTION_VIEW);
        action.setData(Uri.parse("alipays://"));
        List list = manager.queryIntentActivities(action, PackageManager.GET_RESOLVED_FILTER);
        return list != null && list.size() > 0;
    }

    private void loadData() {
        RxRetrofit.getInstance().getApiService().getZhimaUrl(ACache.get(mContext).getAsString(UserInfo.USER_ID), "app")
                .compose(this.<ZhimaUrl>bindToLifecycle())
                .compose(RxSchedulers.<ZhimaUrl>io_main())
                .subscribe(new NetObserver<ZhimaUrl>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, ZhimaUrl zhimaUrl) {
                        refreshUI(zhimaUrl);
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    private void getStatus() {
        RxRetrofit.getInstance().getApiService().getZhimaStatus(ACache.get(mContext).getAsString(UserInfo.USER_ID))
                .compose(this.<ZhimaStatus>bindToLifecycle())
                .compose(RxSchedulers.<ZhimaStatus>io_main())
                .subscribe(new NetObserver<ZhimaStatus>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, ZhimaStatus zhimaStatus) {
                        if ("success".equals(zhimaStatus.getCode())) {
                            startActivity(new Intent(mContext, SesameCertificationYActivity.class));
                            finish();
                        } else {
                            Toast.makeText(SesameCertificationNActivity.this, "" + zhimaStatus.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    private void refreshUI(ZhimaUrl entity) {
        if ("您已进行过芝麻认证".equals(entity.getMessage())) {
            Toast.makeText(mContext, "您已进行过芝麻认证", Toast.LENGTH_SHORT).show();
            return;
        }
        doVerify(entity.getData().getZhimaUrl());
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if ("758".equals(requestCode)) {
//            getStatus();
//        }
//    }
}
