package com.yltx.modulewd.user;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.allen.library.SuperTextView;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulewd.R;

/**
 * Author：Wq
 * Date：2017/10/9 14:14
 * Description：//todo
 */

public class UserLoanAgreementActivity extends BaseActivity {

    private SuperTextView stLoanAgreement;
    private SuperTextView stLoanContract;

    private SuperTextView stCommisionAgreement;
    
    private SuperTextView stPersonalInformation;

    private SuperTextView stSesameCerAgreement;
    private SuperTextView stSesameCreDitAgreement;
  

    @Override
    protected int initLayout() {
        return R.layout.activity_user_agreement;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "用户借款相关协议", null);

        stLoanAgreement= (SuperTextView) findViewById(R.id.st_loan_agreement);
        stLoanContract= (SuperTextView) findViewById(R.id.st_loan_contract);
        stCommisionAgreement= (SuperTextView) findViewById(R.id.st_commission_agreement);
        stPersonalInformation= (SuperTextView) findViewById(R.id.st_personal_information);
        stSesameCerAgreement= (SuperTextView) findViewById(R.id.st_sesame_certification_agreement);
        stSesameCreDitAgreement= (SuperTextView) findViewById(R.id.st_sesame_credit_agreement);
        
        initEvent();
       

    }

    private void initEvent() {
    }

    @Override
    protected void initData() {

    }
}
