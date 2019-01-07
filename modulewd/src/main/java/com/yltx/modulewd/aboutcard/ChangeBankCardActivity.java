package com.yltx.modulewd.aboutcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulebase.util.TimeCount;
import com.yltx.modulebase.widget.ClearEditTextView;
import com.yltx.modulewd.R;
import com.yltx.modulewd.authentication.BindCreditCardYActivity;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.BankCard;
import com.yltx.modulewd.entity.MsgByPhone;
import com.yltx.modulewd.entity.SaveBankResult;
import com.yltx.modulewd.net.RxRetrofit;
import com.yltx.modulewd.widght.StateButton;

/**
 * Author：Wq
 * Date：2017/10/9 17:52
 * Description：//todo
 */

public class ChangeBankCardActivity extends BaseActivity {

    public TextView toolbar_title;
    public TextView toolbar_subtitle;

    private ClearEditTextView cetPerName;
    private ClearEditTextView cetPerNum;
    private ClearEditTextView cetCardNum;
    private ClearEditTextView cetCardUseTime;
    private ClearEditTextView cetCardPhone;
    private ClearEditTextView cetMsgCode;

    private StateButton sbGetMsgCode;
    private SuperButton sbBangCard;

    private TextView tv_name_detail,tv_carid_detail;

    @Override
    protected int initLayout() {
        return R.layout.activity_bangcard;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {



        initToolBar(true, "更换银行卡", null);

        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_subtitle = (TextView) findViewById(R.id.toolbar_subtitle);

//        cetPerName= (ClearEditTextView) findViewById(R.id.myca_et_pername);
//
//        cetPerNum= (ClearEditTextView) findViewById(R.id.myca_et_pernum);

        cetCardNum= (ClearEditTextView) findViewById(R.id.myca_et_cardnum);

        cetCardUseTime= (ClearEditTextView) findViewById(R.id.myca_et_card_usetime);

        cetCardPhone= (ClearEditTextView) findViewById(R.id.myca_et_phone);

        cetMsgCode= (ClearEditTextView) findViewById(R.id.myca_et_msgcode);

        sbGetMsgCode= (StateButton) findViewById(R.id.getpwd_btn_getsms);

        sbBangCard= (SuperButton) findViewById(R.id.sb_tonext);

        tv_name_detail = (TextView) findViewById(R.id.tv_name_detail);
        tv_carid_detail = (TextView) findViewById(R.id.tv_carid_detail);









        initEvent();




    }

    private void initEvent() {
        sbGetMsgCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSendMsg();
            }
        });

        sbBangCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fitData();
            }
        });
    }

    private void fitData() {
        if (TextUtils.isEmpty(cetCardNum.getText().toString())) {
            Toast.makeText(mContext, "请输入银行卡号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(cetCardUseTime.getText().toString())) {
            Toast.makeText(mContext, "请输入卡有效期", Toast.LENGTH_SHORT).show();
            return;
        }

        if(4!=cetCardUseTime.getText().toString().trim().replace("/","").length()){
            Toast.makeText(mContext, "请输入卡有效期月年如：（2022年1月）输入0122", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!MonthIsUse(cetCardUseTime.getText().toString().trim())||!YearIsUse(cetCardUseTime.getText().toString().trim())) {
            Toast.makeText(mContext, "请输入正确的4位卡有效期", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(cetCardPhone.getText().toString())) {
            Toast.makeText(mContext, "请输入银行卡绑定的手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(cetMsgCode.getText().toString())) {
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
        bankCard.setBankCardNo(cetCardNum.getText().toString());
        bankCard.setReservedMobile(cetCardPhone.getText().toString());
        bankCard.setCardValidate(  cetCardUseTime.getText().toString());
        bankCard.setCvn2Code(cetMsgCode.getText().toString());
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
                            ACache.get(mContext).put(UserInfo.USER_BANK, result.getBankName());
                            ACache.get(mContext).put(UserInfo.USER_BANK_CARD, bankCard.getBankCardNo());
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




    private void toSendMsg() {
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
        new TimeCount(sbGetMsgCode, 5 * 1000, 1000).start();
    }


    @Override
    protected void initData() {


        tv_name_detail.setText(ACache.get(mContext).getAsString(UserInfo.USER_NAME));
        tv_carid_detail.setText(ACache.get(mContext).getAsString(UserInfo.USER_ID_CARD));

    }


    public boolean MonthIsUse(String str){
        if(1<=Integer.valueOf(str.substring(0,2))&&Integer.valueOf(str.substring(0,2))<=12){
            Log.d(TAG,"MonthIsUse===return true");
            return true;
        }else{
            Log.d(TAG,"MonthIsUse===return false;");
            return false;
        }
    }

    public boolean YearIsUse(String str){
        if(Integer.valueOf(str.substring(2))<=99){
            Log.d(TAG,"MonthIsUse===return true;");
            return true;
        }else{
            Log.d(TAG,"MonthIsUse===return false;");
            return false;
        }
    }
}
