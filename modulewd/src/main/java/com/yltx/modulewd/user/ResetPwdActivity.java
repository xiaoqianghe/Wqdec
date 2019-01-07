package com.yltx.modulewd.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yltx.modulebase.base.ActivityManager;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulebase.util.MD5;
import com.yltx.modulebase.util.TimeCount;
import com.yltx.modulebase.widget.ClearEditTextView;
import com.yltx.modulewd.MainActivity;
import com.yltx.modulewd.R;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.MsgByPhone;
import com.yltx.modulewd.entity.PostPwd;
import com.yltx.modulewd.entity.Result;
import com.yltx.modulewd.net.RxRetrofit;
import com.yltx.modulewd.widght.StateButton;

import org.w3c.dom.Text;

/**
 * Author：Wq
 * Date：2017/10/11 16:14
 * Description：//todo
 */

public class ResetPwdActivity extends BaseActivity {

    private ClearEditTextView msgCode;
    private ClearEditTextView traderPwd;
    private ClearEditTextView comfirePwd;
    private StateButton getMsgCode;
    private StateButton sbCommit;

    private RelativeLayout mRlMsgContainer;

    private CheckBox cbResettolook;



    private String strMsgCode,strTraderPwd,strComfirePwd;
    private TextView tvAboutPhone;

    @Override
    protected int initLayout() {
        return R.layout.activity_resetpwd;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "重置交易密码", null);
        msgCode = (ClearEditTextView) findViewById(R.id.myca_et_msgcode);
        traderPwd = (ClearEditTextView) findViewById(R.id.myca_et_traderpwd);
        comfirePwd= (ClearEditTextView) findViewById(R.id.myca_et_comfirepwd);

        getMsgCode = (StateButton) findViewById(R.id.getpwd_btn_getsms);
        sbCommit = (StateButton) findViewById(R.id.sb_tocommit);

        tvAboutPhone= (TextView) findViewById(R.id.tv_aboutpone);

        mRlMsgContainer=(RelativeLayout) findViewById(R.id.rl_mescontainer);

        cbResettolook=(CheckBox) findViewById(R.id.cb_resettolook);


        //ACache.get(mContext).getAsString(UserInfo.USER_PHONE)


        initEvent();


    }

    private void initEvent() {

        getMsgCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSMS();
            }
        });

        sbCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toResetCommit();
            }
        });


        cbResettolook.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    //如果选中，显示密码
                    traderPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    traderPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });
    }

    @Override
    protected void initData() {

        //系统已向手机号码: 177****2017 发送验证码

        tvAboutPhone.setText(filterPhoneNum(ACache.get(mContext).getAsString(UserInfo.USER_PHONE)));


    }


    private void toResetCommit() {

        strMsgCode=msgCode.getText().toString();
        strTraderPwd=traderPwd.getText().toString();
        strComfirePwd= comfirePwd.getText().toString();


        if(TextUtils.isEmpty(strMsgCode)){
            Toast.makeText(mContext, "验证码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(strTraderPwd)){
            Toast.makeText(mContext, "新密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(strTraderPwd.length()!=6){
            Toast.makeText(mContext, "新密码不足6位!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(strComfirePwd)){
            Toast.makeText(mContext, "确认密码不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!strTraderPwd.equals(strComfirePwd)){
            Toast.makeText(mContext, "新密码和确认密码不一致!", Toast.LENGTH_SHORT).show();
            return;

        }


        PostPwd mPostPwd= new PostPwd();
        mPostPwd.setMemberId(ACache.get(mContext).getAsString(UserInfo.USER_ID));

        //ACache.get(mContext).put(UserInfo.USER_PHONE, entity.getPhone());
        mPostPwd.setCode(strMsgCode);
        mPostPwd.setNewPassWord(MD5.getMessageDigest(strTraderPwd.getBytes()));
        mPostPwd.setNewPassWord2(MD5.getMessageDigest(strComfirePwd.getBytes()));

        toNext(mPostPwd);
    }


    //@// TODO: 2017/10/18  重新设置交易密码 
    private void toNext(PostPwd mPostPwd) {
        RxRetrofit.getInstance().getApiService(). backPassWord(mPostPwd)
                .compose(this.<Result>bindToLifecycle())
                .compose(RxSchedulers.<Result>io_main())
                .subscribe(new NetObserver<Result>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, Result result) {
                        if ("success".equals(result.getCode())) {
                            Toast.makeText(mContext, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
                            //
                            startActivity(new Intent(ResetPwdActivity.this, MainActivity.class));
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
//                            mRlMsgContainer.setVisibility(View.VISIBLE);
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
        new TimeCount(getMsgCode, 60 * 1000, 1000).start();
    }


    public String filterPhoneNum(String str){
        String newStr="";
        if(!TextUtils.isEmpty(str)){
            newStr = str.substring(0,3)+"****"+str.substring(7,str.length());
        }
        return newStr;
    }
}
