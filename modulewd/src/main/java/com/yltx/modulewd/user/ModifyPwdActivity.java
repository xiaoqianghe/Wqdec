package com.yltx.modulewd.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulebase.util.MD5;
import com.yltx.modulebase.widget.ClearEditTextView;
import com.yltx.modulewd.MainActivity;
import com.yltx.modulewd.R;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.PostPwd;
import com.yltx.modulewd.entity.Result;
import com.yltx.modulewd.net.RxRetrofit;
import com.yltx.modulewd.widght.StateButton;

import io.reactivex.internal.schedulers.NewThreadScheduler;

/**
 * Author：Wq
 * Date：2017/10/18 9:14
 * Description：//todo
 */

public class ModifyPwdActivity extends BaseActivity {

    private ClearEditTextView cetOld;
    private ClearEditTextView cetNew;
    private ClearEditTextView cetComfire;

    private StateButton   sbCommit;

    private CheckBox cbToLook;

    private String strOld;
    private String strNew;
    private String strComfire;

    private TextView tvForget;
    @Override
    protected int initLayout() {
        return R.layout.activity_modifypwd;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "修改交易密码", null);


        cetOld=(ClearEditTextView)findViewById(R.id.myca_et_oldpwd);
        cetNew=(ClearEditTextView)findViewById(R.id.myca_et_newpwd);
        cetComfire=(ClearEditTextView)findViewById(R.id.myca_et_comfirepwd);
        sbCommit=(StateButton) findViewById(R.id.sb_tocommit);
        tvForget=(TextView)findViewById(R.id.tv_forget);
        cbToLook=(CheckBox)findViewById(R.id.cb_tolook);

        
        initEvent();

        

    }

    private void initEvent() {

        sbCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toCommit();
            }
        });


        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ModifyPwdActivity.this,ResetPwdActivity.class));
            }
        });

        cbToLook.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    //如果选中，显示密码
                    cetNew.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    cetNew.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });



}


    class  MyTextWatcher implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            toSetBtStatus();

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private void toSetBtStatus() {
        if(!TextUtils.isEmpty(cetOld.getText().toString().trim())
                &&!TextUtils.isEmpty(cetNew.getText().toString().trim())
                &&!TextUtils.isEmpty(cetComfire.getText().toString().trim())
                ){

        }

    }

    private void toCommit() {

        strOld=cetOld.getText().toString();
        strNew=cetNew.getText().toString();
        strComfire=cetComfire.getText().toString();


        if(TextUtils.isEmpty(strOld)){
            Toast.makeText(mContext, "原密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(strNew)){
            Toast.makeText(mContext, "新密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(strNew.length()!=6){
            Toast.makeText(mContext, "新密码不足6位!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(strComfire)){
            Toast.makeText(mContext, "确认密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!strNew.equals(strComfire)){
            Toast.makeText(mContext, "新密码和确认密码不一致!", Toast.LENGTH_SHORT).show();
            return;

        }


        PostPwd mPostPwd= new PostPwd();
        mPostPwd.setMemberId(ACache.get(mContext).getAsString(UserInfo.USER_ID));
        mPostPwd.setOldPassWord(MD5.getMessageDigest(strOld.getBytes()));
        mPostPwd.setNewPassWord(MD5.getMessageDigest(strNew.getBytes()));
        mPostPwd.setNewPassWord2(MD5.getMessageDigest(strComfire.getBytes()));

        toNext(mPostPwd);
    }

    private void toNext(PostPwd mPostPwd) {
        RxRetrofit.getInstance().getApiService(). setPassWord(mPostPwd)
                .compose(this.<Result>bindToLifecycle())
                .compose(RxSchedulers.<Result>io_main())
                .subscribe(new NetObserver<Result>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, Result result) {
                        if ("success".equals(result.getCode())) {
                            Toast.makeText(mContext, "" + result.getMessage(), Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(ModifyPwdActivity.this, MainActivity.class));
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


    @Override
    protected void initData() {


    }
}
