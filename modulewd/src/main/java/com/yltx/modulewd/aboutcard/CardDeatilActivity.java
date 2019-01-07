package com.yltx.modulewd.aboutcard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperTextView;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulewd.R;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.Consta;
import com.yltx.modulewd.entity.BankDetailRsBean;
import com.yltx.modulewd.entity.DepositBankRsBean;
import com.yltx.modulewd.net.RxRetrofit;

import static com.yltx.modulewd.entity.Consta.cardType.DepositBank;

/**
 * Author：Wq
 * Date：2017/10/9 14:39
 * Description：//todo
 */

public class CardDeatilActivity extends BaseActivity {

    public TextView toolbar_title;
    public TextView toolbar_subtitle;

    private SuperTextView stCardDetailName;
    private SuperTextView stePersonalDetailNum;
    private SuperTextView stDetailCardNum;
    private SuperTextView stBankCardName;
    private SuperTextView stCardUserTime;

    private SuperTextView stDetailPhoneNum;
    private TextView tvDetailPersonalMsg,tvDetailPersonalName,tvDetailCardMsg,tvDetailCardNum;



    private String mCardType;

    private BankDetailRsBean.InfoBean mInfoBean;




    @Override
    protected int initLayout() {
        return R.layout.activity_card_detail;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        initToolBar(true, "银行卡详情", "更换银行卡");

        stCardDetailName= (SuperTextView) findViewById(R.id.st_detail_name);

        stePersonalDetailNum= (SuperTextView) findViewById(R.id.st_detail_personal_num);

        stDetailCardNum= (SuperTextView) findViewById(R.id.st_detail_card_num);

        stBankCardName= (SuperTextView) findViewById(R.id.st_bankcard_name);

        stCardUserTime= (SuperTextView) findViewById(R.id.st_detail_card_usetime);

        stDetailPhoneNum= (SuperTextView) findViewById(R.id.st_detail_phone_num);


        tvDetailPersonalMsg= (TextView) findViewById(R.id.tv_detail_personal_msg);
        tvDetailPersonalName= (TextView) findViewById(R.id.tv_detail_personal_name);

        tvDetailCardMsg= (TextView) findViewById(R.id.tv_detail_card_msg);
        tvDetailCardNum= (TextView) findViewById(R.id.tv_detail_card_num);

        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_subtitle = (TextView) findViewById(R.id.toolbar_subtitle);




        SpannableString spannableString1 = new SpannableString("* 真实姓名:");
        spannableString1.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stCardDetailName.setLeftString(spannableString1);


//        SpannableString spannableString2 = new SpannableString("* 身份证号码");
//        spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        stePersonalDetailNum.setLeftString(spannableString2);
//
//        SpannableString spannableString3 = new SpannableString("* 银行卡号");
//        spannableString3.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        stDetailCardNum.setLeftString(spannableString3);

        SpannableString spannableString2 = new SpannableString("* 身份证号:");
        spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDetailPersonalMsg.setText(spannableString2);

        SpannableString spannableString3 = new SpannableString("* 银行卡号:");
        spannableString3.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvDetailCardMsg.setText(spannableString3);


        SpannableString spannableString4 = new SpannableString("* 开户银行:");
        spannableString4.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stBankCardName.setLeftString(spannableString4);

        SpannableString spannableString5 = new SpannableString("* 卡有效期:");
        spannableString5.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stCardUserTime.setLeftString(spannableString5);



        SpannableString spannableString6 = new SpannableString("*  预留手机号:");
        spannableString6.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stDetailPhoneNum.setLeftString(spannableString6);

        initEvent();







    }

    private void initEvent() {

        toolbar_subtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(CardDeatilActivity.this,ChangeBankCardActivity.class));


            }
        });
    }

    @Override
    protected void initData() {


        mCardType=getIntent().getStringExtra(Consta.cardType.CARDTYPE);

        if(mCardType.equals(DepositBank)){
            toLoadDepBankData();
        }else if(mCardType.equals(Consta.cardType.CreditBank)){
            toLoadCreditBankData();
        }


    }

    private void toLoadCreditBankData() {

        RxRetrofit.getInstance().getApiService().getCreditBank(ACache.get(mContext).getAsString(UserInfo.USER_ID))
                .compose(this.<BankDetailRsBean>bindToLifecycle())
                .compose(RxSchedulers.<BankDetailRsBean>io_main())
                .subscribe(new NetObserver<BankDetailRsBean>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, BankDetailRsBean result) {
                        if ("success".equals(result.getCode())) {
                                setData(result);
                        } else {
                            Toast.makeText(mContext, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });

    }

    private void setData(BankDetailRsBean result) {
        mInfoBean=result.getInfo();

        if(null!=mInfoBean){stCardDetailName.setRightString(mInfoBean.getRealName());
//        stePersonalDetailNum.setRightString(mInfoBean.getIdcardNo());
//        stDetailCardNum.setRightString(mInfoBean.getBankCardNo());

        tvDetailPersonalName.setText(mInfoBean.getIdcardNo());
        tvDetailCardNum.setText(mInfoBean.getBankCardNo());

        stBankCardName.setRightString(mInfoBean.getBankName()+filterCardBankName(mInfoBean.getCardType()));
        stCardUserTime.setRightString(mInfoBean.getCardValidate());
        stDetailPhoneNum.setRightString(mInfoBean.getReservedMobile());
        }
    }

    private void toLoadDepBankData() {

        RxRetrofit.getInstance().getApiService().getDepositBank(ACache.get(mContext).getAsString(UserInfo.USER_ID))
                .compose(this.<BankDetailRsBean>bindToLifecycle())
                .compose(RxSchedulers.<BankDetailRsBean>io_main())
                .subscribe(new NetObserver<BankDetailRsBean>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, BankDetailRsBean result) {
                        if ("success".equals(result.getCode())) {
                            setData(result);
                        } else {
                            Toast.makeText(mContext, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });

    }

    public String filterCardBankName(String mCardType){
        if(DepositBank.equals(mCardType)){
            return "  (储蓄卡)";
        }else{
            return "  (信用卡)";
        }

    }


}
