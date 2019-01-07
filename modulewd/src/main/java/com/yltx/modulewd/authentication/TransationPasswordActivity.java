package com.yltx.modulewd.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.yltx.modulebase.util.MD5;
import com.yltx.modulewd.R;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.PostPwd;
import com.yltx.modulewd.entity.Result;
import com.yltx.modulewd.net.RxRetrofit;

public class TransationPasswordActivity extends BaseActivity {
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

    private EditText et_pwd;
    private EditText et_repwd;
    private ImageView iv_pwd;
    private ImageView iv_repwd;
    private SuperButton btn_do;

    private boolean isshow;

    @Override
    protected int initLayout() {
        return R.layout.activity_transation_password;
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

        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_repwd = (EditText) findViewById(R.id.et_repwd);
        iv_pwd = (ImageView) findViewById(R.id.iv_pwd);
        iv_repwd = (ImageView) findViewById(R.id.iv_repwd);
        btn_do = (SuperButton) findViewById(R.id.btn_do);
        btn_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fitData();
            }
        });
        iv_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isshow) {
                    iv_pwd.setImageResource(R.mipmap.ic_pwd_n);
                    et_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_repwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    iv_pwd.setImageResource(R.mipmap.ic_pwd);
                    et_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_repwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                isshow = !isshow;
                et_pwd.postInvalidate();
                et_repwd.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = et_pwd.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
                CharSequence charSequence1 = et_repwd.getText();
                if (charSequence1 instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence1;
                    Selection.setSelection(spanText, charSequence1.length());
                }
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
        iv_card.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_n));
        line_card_r.setBackgroundColor(ContextCompat.getColor(mContext, R.color.line_progress));
        tv_card.setTextColor(ContextCompat.getColor(mContext, R.color.tv6));
        iv_vip.setBackground(AppCompatResources.getDrawable(mContext, R.mipmap.ic_ca_d));
        line_vip_l.setBackgroundColor(ContextCompat.getColor(mContext, R.color.line_progress));
        tv_vip.setTextColor(ContextCompat.getColor(mContext, R.color.tv9));
    }

    @Override
    protected void initData() {

    }

    private void fitData() {
        if (TextUtils.isEmpty(et_pwd.getText().toString())) {
            Toast.makeText(mContext, "请输入交易密码", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(et_repwd.getText().toString())) {
            Toast.makeText(mContext, "请确认交易密码", Toast.LENGTH_SHORT).show();
        }

        if (et_pwd.getText().toString().equals(et_repwd.getText().toString())) {
            loadData("null", MD5.getMessageDigest(et_repwd.getText().toString().getBytes()), MD5.getMessageDigest(et_repwd.getText().toString().getBytes()));
        } else {
            Toast.makeText(mContext, "密码不一致", Toast.LENGTH_SHORT).show();
        }

    }


    private void loadData( String oldPwd, String pwd, String repwd) {
        PostPwd mPostPwd= new PostPwd();
        mPostPwd.setMemberId(ACache.get(mContext).getAsString(UserInfo.USER_ID));
        mPostPwd.setOldPassWord(oldPwd);
        mPostPwd.setNewPassWord(MD5.getMessageDigest(pwd.getBytes()));
        mPostPwd.setNewPassWord2(MD5.getMessageDigest(repwd.getBytes()));

        RxRetrofit.getInstance().getApiService().setPassWord(mPostPwd)
                .compose(this.<Result>bindToLifecycle())
                .compose(RxSchedulers.<Result>io_main())
                .subscribe(new NetObserver<Result>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, Result result) {
                        if ("success".equals(result.getCode())) {
                            startActivity(new Intent(mContext, OpenMemberActivity.class));
                            finish();
                        } else {
                            Toast.makeText(mContext, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }
}
