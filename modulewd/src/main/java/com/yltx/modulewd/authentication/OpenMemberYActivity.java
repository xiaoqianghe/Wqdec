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
import com.yltx.modulebase.util.ACache;
import com.yltx.modulewd.MainActivity;
import com.yltx.modulewd.R;
import com.yltx.modulewd.borrow.borrowoperate.OperateActivity;
import com.yltx.modulewd.constant.OrderInfo;

public class OpenMemberYActivity extends BaseActivity {
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

    private TextView tv_money;
    private TextView tv_name;
    private TextView tv_time;
    private TextView tv_pay_type;
    private TextView tv_no;
    private SuperButton btn_do;

    @Override
    protected int initLayout() {
        return R.layout.activity_open_member_y;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
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

        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_pay_type = (TextView) findViewById(R.id.tv_pay_type);
        tv_no = (TextView) findViewById(R.id.tv_no);

        btn_do = (SuperButton) findViewById(R.id.btn_do);
        btn_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, OperateActivity.class));
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
        iv_score.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_y));
        line_score_r.setBackgroundColor(ContextCompat.getColor(mContext, R.color.tvo));
        tv_score.setTextColor(ContextCompat.getColor(mContext, R.color.tv6));
        line_card_l.setBackgroundColor(ContextCompat.getColor(mContext, R.color.tvo));
        iv_card.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_y));
        line_card_r.setBackgroundColor(ContextCompat.getColor(mContext, R.color.tvo));
        tv_card.setTextColor(ContextCompat.getColor(mContext, R.color.tv6));
        iv_vip.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_y));
        line_vip_l.setBackgroundColor(ContextCompat.getColor(mContext, R.color.tvo));
        tv_vip.setTextColor(ContextCompat.getColor(mContext, R.color.tv6));
    }

    @Override
    protected void initData() {
        payInfo();
    }

    private void payInfo() {
        tv_money.setText("￥"+ ACache.get(mContext).getAsString(OrderInfo.ORDER_MONEY));
        tv_time.setText("" + ACache.get(mContext).getAsString(OrderInfo.ORDER_TRANSACTION_TIME));
        tv_pay_type.setText("" + ACache.get(mContext).getAsString(OrderInfo.ORDER_PAY_TYPE));
        tv_no.setText("" + ACache.get(mContext).getAsString(OrderInfo.ORDER_NO));
    }
}
