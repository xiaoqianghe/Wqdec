package com.yltx.modulewd.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.allen.library.SuperTextView;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulewd.MyBankCardActivity;
import com.yltx.modulewd.R;
import com.yltx.modulewd.aboutcard.BankCardListActivity;
import com.yltx.modulewd.authentication.BindCreditCardNActivity;
import com.yltx.modulewd.authentication.BindCreditCardYActivity;
import com.yltx.modulewd.authentication.OpenMemberActivity;
import com.yltx.modulewd.authentication.OpenMemberYActivity;
import com.yltx.modulewd.authentication.SesameCertificationNActivity;
import com.yltx.modulewd.authentication.SesameCertificationYActivity;
import com.yltx.modulewd.authentication.SesameCreditNActivity;
import com.yltx.modulewd.authentication.SesameCreditYActivity;
import com.yltx.modulewd.constant.UserInfo;

/**
 * Author：Wq
 * Date：2017/10/9 14:04
 * Description：//todo
 */

public class PersonalInfoAuthActivity extends BaseActivity {

    private SuperTextView stSesameCertification;
    private SuperTextView stSesameLicense;
    private SuperTextView stBangCard;
    private SuperTextView stHelp;
    private ImageView mIvCertification,mIvLicense,mIvBangCard,mIvHelp;
    private LinearLayout  ll_certification,ll_license,ll_bangcard,ll_help;
    @Override
    protected int initLayout() {
        return R.layout.activity_perinfo_auth;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        initToolBar(true, "个人信息认证", null);

        stSesameCertification= (SuperTextView) findViewById(R.id.st_sesame_certification);

        stSesameLicense= (SuperTextView) findViewById(R.id.st_sesame_license);

        stBangCard= (SuperTextView) findViewById(R.id.st_bangcard);

        stHelp= (SuperTextView) findViewById(R.id.st_help);

        mIvCertification= (ImageView) findViewById(R.id.iv_certification);
        mIvLicense= (ImageView) findViewById(R.id.iv_license);
        mIvBangCard= (ImageView) findViewById(R.id.iv_bangcard);
        mIvHelp= (ImageView) findViewById(R.id.iv_help);

        //  private LinearLayout  ll_certification,ll_license,ll_bangcard,ll_help;

        ll_certification= (LinearLayout) findViewById(R.id.ll_certification);
        ll_license= (LinearLayout) findViewById(R.id.ll_license);
        ll_bangcard= (LinearLayout) findViewById(R.id.ll_bangcard);
        ll_help= (LinearLayout) findViewById(R.id.ll_help);


        
        initEvent();

    }

    private void initEvent() {

        ll_certification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("1".equals(ACache.get(mContext).getAsString(UserInfo.USER_ZHIMA_STATUS))){
                    startActivity(new Intent(PersonalInfoAuthActivity.this,
                            SesameCertificationYActivity.class));
                }else{
                    startActivity(new Intent(PersonalInfoAuthActivity.this,
                            SesameCertificationNActivity.class));
                }
            }
        });

//        stSesameCertification.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if("1".equals(ACache.get(mContext).getAsString(UserInfo.USER_ZHIMA_STATUS))){
//                    startActivity(new Intent(PersonalInfoAuthActivity.this,
//                            SesameCertificationYActivity.class));
//                }else{
//                    startActivity(new Intent(PersonalInfoAuthActivity.this,
//                            SesameCertificationNActivity.class));
//                }
//            }
//        });

        stSesameLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("1".equals(ACache.get(mContext).getAsString(UserInfo.USER_ZHIMA_CREDIT))){
                    startActivity(new Intent(PersonalInfoAuthActivity.this,
                           SesameCreditYActivity.class));
                }else{
                    startActivity(new Intent(PersonalInfoAuthActivity.this,
                            SesameCreditNActivity.class));
                }
            }
        });

        stBangCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("1".equals(ACache.get(mContext).getAsString(UserInfo.USER_ISHAVE_BANK))){
                    startActivity(new Intent(PersonalInfoAuthActivity.this,
                            BankCardListActivity.class));
                }else{
                    startActivity(new Intent(PersonalInfoAuthActivity.this,
                           BindCreditCardNActivity.class));
                }
            }
        });

        stHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("1".equals(ACache.get(mContext).getAsString(UserInfo.IS_PAY_FEE))){
                    startActivity(new Intent(PersonalInfoAuthActivity.this,
                            OpenMemberYActivity.class));
                }else{
                    startActivity(new Intent(PersonalInfoAuthActivity.this,
                            OpenMemberActivity.class));
                }
            }
        });

    }

    @Override
    protected void initData() {
//
//        //  //zhimaStatus   芝麻认证zhimaCredit  芝麻信用 isHaveBank信用卡 isPayFee   开通会员
//        ACache.get(mContext).put(UserInfo.USER_ZHIMA_STATUS,entity.getZhimaStatus());
//        ACache.get(mContext).put(UserInfo.USER_ZHIMA_CREDIT,entity.getZhimaCredit());
//        ACache.get(mContext).put(UserInfo.USER_ISHAVE_BANK,entity.getIsHaveBank());

        if("1".equals(ACache.get(mContext).getAsString(UserInfo.USER_ZHIMA_STATUS))){
            stSesameCertification.setRightTextColor(this.getResources().getColor(R.color.base_color));
            mIvCertification.setVisibility(View.INVISIBLE);

        }
        if("1".equals(ACache.get(mContext).getAsString(UserInfo.USER_ZHIMA_CREDIT))){
            stSesameLicense.setRightTextColor(this.getResources().getColor(R.color.base_color));
            mIvLicense.setVisibility(View.INVISIBLE);

        }
        if("1".equals(ACache.get(mContext).getAsString(UserInfo.USER_ISHAVE_BANK))){
            stBangCard.setRightTextColor(this.getResources().getColor(R.color.base_color));
            mIvBangCard.setVisibility(View.INVISIBLE);
        }
        if("1".equals(ACache.get(mContext).getAsString(UserInfo.IS_PAY_FEE))){
            stHelp.setRightTextColor(this.getResources().getColor(R.color.base_color));
            mIvHelp.setVisibility(View.INVISIBLE);

        }
    }
}
