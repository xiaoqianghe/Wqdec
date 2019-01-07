package com.yltx.modulewd.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.allen.library.SuperTextView;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.widget.ClearEditTextView;
import com.yltx.modulewd.R;

/**
 * Author：Wq
 * Date：2017/10/12 16:54
 * Description：//todo
 */

public class SettingActivity extends BaseActivity{
    private SuperTextView stModifyPwd;
    @Override
    protected int initLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "设置", null);

        stModifyPwd= (SuperTextView) findViewById(R.id.st_modify_pwd);

        stModifyPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(SettingActivity.this,ResetPwdActivity.class));

                startActivity(new Intent(SettingActivity.this,ModifyPwdActivity.class));
            }
        });

    }

    @Override
    protected void initData() {

    }
}
