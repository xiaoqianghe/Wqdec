package com.yltx.modulewd.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulewd.R;
import com.yltx.modulewd.borrow.detail.DetailStatus;

import org.w3c.dom.Text;

/**
 * Author：Wq
 * Date：2017/10/9 10:48
 * Description：//todo
 */

public class UserInfoActivity extends BaseActivity {

    public TextView toolbar_title;
    public TextView toolbar_subtitle;

    public ImageView ivAvatar;
    public TextView tvAccountValue;
    public ImageView ivAvatarStatus;

    public TextView tvLevelValue;
    public TextView tvBelieveValue;
    public TextView tvUserTime;

    public SuperTextView  stBorrowrecord;
    public SuperTextView stCardmanager;
    public SuperTextView stPersonalinfo;

    public SuperTextView stHelpCenter;



    @Override
    protected int initLayout() {
        return R.layout.activity_account;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        initToolBar(true, "账户", null);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_subtitle = (TextView) findViewById(R.id.toolbar_subtitle);

        ivAvatar = (ImageView) findViewById(R.id.my_iv_avatar);
        tvAccountValue = (TextView) findViewById(R.id.my_tv_account_value);
        ivAvatarStatus = (ImageView) findViewById(R.id.iv_user_status);


        tvLevelValue= (TextView) findViewById(R.id.my_tv_level_value);
        tvBelieveValue= (TextView) findViewById(R.id.my_tv_believe_value);

        tvUserTime= (TextView) findViewById(R.id.my_tv_usetime_value);


        stBorrowrecord= (SuperTextView) findViewById(R.id.st_borrowrecord);
        stCardmanager= (SuperTextView) findViewById(R.id.st_cardmanager);
        stPersonalinfo= (SuperTextView) findViewById(R.id.st_personalinfo);
        stHelpCenter= (SuperTextView) findViewById(R.id.st_help);
        
        initEvent();



    }

    private void initEvent() {
        stBorrowrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //@// TODO: 2017/10/9  跳转借款记录页面

            }
        });

        stCardmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //@// TODO: 2017/10/9  跳转银行卡管理页面

            }
        });

        stPersonalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //@// TODO: 2017/10/9  跳转个人信息认证页面

            }
        });

        stHelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //@// TODO: 2017/10/9  跳转 帮助中心页面

            }
        });

        stHelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //@// TODO: 2017/10/9  跳转 帮助中心页面

            }
        });


    }

    @Override
    protected void initData() {

    }
}
