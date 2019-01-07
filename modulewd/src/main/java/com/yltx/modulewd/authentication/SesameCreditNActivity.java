package com.yltx.modulewd.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulewd.R;

public class SesameCreditNActivity extends BaseActivity {
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
        return R.layout.activity_sesame_credit_n;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "芝麻信用授权", null);
        iv_ca = (ImageView) findViewById(R.id.iv_ca);
        line_ca_r =  findViewById(R.id.line_ca_r);
        tv_ca = (TextView) findViewById(R.id.tv_ca);
        line_score_l =  findViewById(R.id.line_score_l);
        iv_score = (ImageView) findViewById(R.id.iv_score);
        line_score_r =  findViewById(R.id.line_score_r);
        tv_score = (TextView) findViewById(R.id.tv_score);
        line_card_l =  findViewById(R.id.line_card_l);
        iv_card = (ImageView) findViewById(R.id.iv_card);
        line_card_r =  findViewById(R.id.line_card_r);
        tv_card = (TextView) findViewById(R.id.tv_card);
        iv_vip = (ImageView) findViewById(R.id.iv_vip);
        line_vip_l =  findViewById(R.id.line_vip_l);
        tv_vip = (TextView) findViewById(R.id.tv_vip);

        btn_do = (SuperButton) findViewById(R.id.btn_do);
        btn_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext,SesameCreditIActivity.class));
                finish();
            }
        });
        sesameProgress();
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

    }
}
