package com.yltx.modulewd.authentication;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperButton;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulebase.util.TimeCount;
import com.yltx.modulebase.widget.KeyBoardHelper;
import com.yltx.modulewd.R;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.BankCard;
import com.yltx.modulewd.entity.MsgByPhone;
import com.yltx.modulewd.entity.SaveBankResult;
import com.yltx.modulewd.net.RxRetrofit;

public class BindCreditCardNActivity extends BaseActivity {
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
    private EditText et_bank;
    private LinearLayout lllayout;
    private LinearLayout ll_bank;
    private TextView tv_bank;
    private TextView tv_err_bank;
    private EditText et_trem;
    private EditText et_phone;
    private EditText et_sms;
    private SuperButton btn_sms;
    private SuperButton btn_do;

    private View layout_bottom;
    private View layout_content;
    private int bottomHeight;
    private KeyBoardHelper boardHelper;

    private boolean isModify;

    @Override
    protected int initLayout() {
        return R.layout.activity_bind_credit_card_n;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "绑定信用卡", null);
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
        et_bank = (EditText) findViewById(R.id.et_bank);
        lllayout = (LinearLayout) findViewById(R.id.lllayout);
        ll_bank = (LinearLayout) findViewById(R.id.ll_bank);
        tv_bank = (TextView) findViewById(R.id.tv_bank);
        tv_err_bank = (TextView) findViewById(R.id.tv_err_bank);
        et_trem = (EditText) findViewById(R.id.et_trem);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_sms = (EditText) findViewById(R.id.et_sms);
        btn_sms = (SuperButton) findViewById(R.id.btn_sms);
        btn_do = (SuperButton) findViewById(R.id.btn_do);

//        lllayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Rect r = new Rect();
//                lllayout.getWindowVisibleDisplayFrame(r);
//                int screenHeight = lllayout.getRootView().getHeight();
//                int heightDifference = screenHeight - r.bottom;
//                if (heightDifference > 200) {
//                    isModify = true;
//                } else {
//                    if (isModify) {
//                        isModify = false;
//                        fitBank(et_bank.getText().toString());
//                    }
//
//                }
//            }
//        });

        et_bank.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                }else{
                    fitBank(et_bank.getText().toString());
                }
            }
        });

        layout_content = findViewById(R.id.layout_content);
        layout_bottom = findViewById(R.id.btn_do);

        btn_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fitData();
            }
        });

        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSMS();
            }
        });
        sesameProgress();
        initKeyBoard();
    }

    private void fitBank(String text) {
        if (TextUtils.isEmpty(text)) {
            showErr(false);
            return;
        }
        if (text.length() < 16 || text.length() > 19) {
            showErrNum(true);
            return;
        } else {
            showErrNum(false);
        }
        RxRetrofit.getInstance().getApiService().getBankCard(text)
                .compose(this.<SaveBankResult>bindToLifecycle())
                .compose(RxSchedulers.<SaveBankResult>io_main())
                .subscribe(new NetObserver<SaveBankResult>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, SaveBankResult result) {
                        if ("success".equals(result.getCode())) {
                            tv_err_bank.setVisibility(View.GONE);
                            btn_do.setEnabled(true);
                            btn_sms.setEnabled(true);
                            ll_bank.setVisibility(View.VISIBLE);
                            tv_bank.setText(result.getBankName());
                        } else {
                            showErr(true);
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    private void showErr(boolean falg) {
        if (falg) {
            tv_err_bank.setVisibility(View.VISIBLE);
            btn_sms.setEnabled(false);
            btn_do.setEnabled(false);
        } else {
            tv_err_bank.setVisibility(View.GONE);
            btn_sms.setEnabled(true);
            btn_do.setEnabled(true);
        }
    }

    private void showErrNum(boolean falg) {
        if (falg) {
            tv_err_bank.setVisibility(View.VISIBLE);
            tv_err_bank.setText("请输入正确的银行卡号!");
            btn_sms.setEnabled(false);
            btn_do.setEnabled(false);
        } else {
            tv_err_bank.setVisibility(View.GONE);
            btn_sms.setEnabled(true);
            btn_do.setEnabled(true);
        }
    }

    private void fitData() {
        if (TextUtils.isEmpty(et_bank.getText().toString())) {
            Toast.makeText(mContext, "请输入银行卡号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(et_trem.getText().toString())) {
            Toast.makeText(mContext, "请输入卡有效期", Toast.LENGTH_SHORT).show();
            return;
        }
        if(4!=et_trem.getText().toString().trim().replace("/","").length()){
            Toast.makeText(mContext, "请输入卡有效期月年如：（2022年1月）输入0122", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(et_phone.getText().toString())) {
            Toast.makeText(mContext, "请输入银行卡绑定的手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(et_sms.getText().toString())) {
            Toast.makeText(mContext, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        BankCard bankCard = new BankCard();
        bankCard.setCardType("1");
        bankCard.setRealName(ACache.get(mContext).getAsString(UserInfo.USER_NAME));
        bankCard.setIdcardNo(ACache.get(mContext).getAsString(UserInfo.USER_ID_CARD));
        bankCard.setCreateBy(ACache.get(mContext).getAsString(UserInfo.USER_NAME));
        bankCard.setUpdateBy(ACache.get(mContext).getAsString(UserInfo.USER_NAME));
        bankCard.setMemberId(ACache.get(mContext).getAsString(UserInfo.USER_ID));
        bankCard.setBankCardNo(et_bank.getText().toString());
        bankCard.setReservedMobile(et_phone.getText().toString());
        bankCard.setCardValidate(et_trem.getText().toString());
        bankCard.setCvn2Code(et_sms.getText().toString());
        loadData(bankCard);
    }

    private void loadData(final BankCard bankCard) {
        RxRetrofit.getInstance().getApiService().saveBankCard(bankCard)
                .compose(this.<SaveBankResult>bindToLifecycle())
                .compose(RxSchedulers.<SaveBankResult>io_main())
                .subscribe(new NetObserver<SaveBankResult>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, SaveBankResult result) {
                        if ("success".equals(result.getCode())) {
                            ACache.get(mContext).put(UserInfo.USER_BANK, ""+result.getBankName());
                            ACache.get(mContext).put(UserInfo.USER_BANK_CARD, ""+bankCard.getBankCardNo());
                            startActivity(new Intent(mContext, BindCreditCardYActivity.class));
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

    private void initKeyBoard() {
        boardHelper = new KeyBoardHelper(this);
        boardHelper.onCreate();
        boardHelper.setOnKeyBoardStatusChangeListener(onKeyBoardStatusChangeListener);
        layout_bottom.post(new Runnable() {
            @Override
            public void run() {
                bottomHeight = layout_bottom.getHeight();
            }
        });
    }

    private KeyBoardHelper.OnKeyBoardStatusChangeListener onKeyBoardStatusChangeListener = new KeyBoardHelper.OnKeyBoardStatusChangeListener() {

        @Override
        public void OnKeyBoardPop(int keyBoardheight) {

            final int height = keyBoardheight;
            if (bottomHeight > height) {
                layout_bottom.setVisibility(View.GONE);
            } else {
                int offset = bottomHeight - height;
                final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) layout_content
                        .getLayoutParams();
                lp.topMargin = offset;
                layout_content.setLayoutParams(lp);
            }

        }

        @Override
        public void OnKeyBoardClose(int oldKeyBoardheight) {
            if (View.VISIBLE != layout_bottom.getVisibility()) {
                layout_bottom.setVisibility(View.VISIBLE);
            }
            final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) layout_content
                    .getLayoutParams();
            if (lp.topMargin != 0) {
                lp.topMargin = 0;
                layout_content.setLayoutParams(lp);
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        boardHelper.onDestory();
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
        tv_name.setText("" + ACache.get(mContext).getAsString(UserInfo.USER_NAME));
        tv_id.setText("" + ACache.get(mContext).getAsString(UserInfo.USER_ID_CARD));

    }
}
