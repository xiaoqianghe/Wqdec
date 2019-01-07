package com.yltx.modulewd;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.yltx.modulebase.base.BaseFragment;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulewd.aboutcard.BankCardListActivity;
import com.yltx.modulewd.borrow.borrowoperate.OperateActivity;
import com.yltx.modulewd.borrow.lsit.ListActivity;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.user.HelperCenterActivity;
import com.yltx.modulewd.user.PersonalInfoAuthActivity;
import com.yltx.modulewd.user.SettingActivity;


public class MyFragment extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public TextView toolbar_title;
    public TextView toolbar_subtitle;

    public ImageView ivAvatar;
    public TextView tvAccountValue;
    public ImageView ivAvatarStatus;

    public TextView tvLevelValue;
    public TextView tvBelieveValue;
    public TextView tvUserTime;

    public SuperTextView stBorrowrecord;
    public SuperTextView stCardmanager;
    public SuperTextView stPersonalinfo;

    public SuperTextView stHelpCenter;

    public SuperTextView stSetting;






    public MyFragment() {
    }

    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void fetchData() {
        Log.e(TAG, "AA_fetchData");
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {

//        initToolBar(true, "账户", null);
        toolbar_title = (TextView)rootView.findViewById(R.id.toolbar_title);
        toolbar_subtitle = (TextView) rootView.findViewById(R.id.toolbar_subtitle);

        ivAvatar = (ImageView) rootView.findViewById(R.id.my_iv_avatar);
        tvAccountValue = (TextView) rootView.findViewById(R.id.my_tv_account_value);
        ivAvatarStatus = (ImageView) rootView.findViewById(R.id.iv_isvip);


        tvLevelValue= (TextView) rootView.findViewById(R.id.my_tv_level_value);
        tvBelieveValue= (TextView) rootView.findViewById(R.id.my_tv_believe_value);

        tvUserTime= (TextView) rootView.findViewById(R.id.my_tv_usetime_value);


        stBorrowrecord= (SuperTextView) rootView.findViewById(R.id.st_borrowrecord);
        stCardmanager= (SuperTextView) rootView.findViewById(R.id.st_cardmanager);
        stPersonalinfo= (SuperTextView) rootView.findViewById(R.id.st_personalinfo);
        stHelpCenter= (SuperTextView) rootView.findViewById(R.id.st_help);

        stSetting= (SuperTextView) rootView.findViewById(R.id.st_setting);




        initEvent();


    }

    @Override
    protected void initData() {
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            setUserInfo();
        }
    }


    private void setUserInfo(){
        tvAccountValue.setText(ACache.get(mContext).getAsString(UserInfo.USER_PHONE));
        tvLevelValue.setText(ACache.get(mContext).getAsString(UserInfo.LEVEL_NAME));
        tvBelieveValue.setText(ACache.get(mContext).getAsString(UserInfo.CREDIT_SCORE));
        tvUserTime.setText(ACache.get(mContext).getAsString(UserInfo.ANNUAL_FEE_PAY_TIME)+"~"+ACache.get(mContext).getAsString(UserInfo.ANNUAL_FEE_VALIDATE));

        if("1".equals(ACache.get(mContext).getAsString(UserInfo.IS_PAY_FEE))){
            ivAvatarStatus.setImageResource(R.mipmap.acount_vip);
        }
    }

    private void initEvent() {
        stBorrowrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //@// TODO: 2017/10/9  跳转借款记录页面
                startActivity(new Intent(getActivity(), ListActivity.class));
            }
        });

        stCardmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //@// TODO: 2017/10/9  跳转银行卡管理页面


                startActivity(new Intent(getActivity(),
                        BankCardListActivity.class));

            }
        });

        stPersonalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //@// TODO: 2017/10/9  跳转个人信息认证页面



                startActivity(new Intent(getActivity(),PersonalInfoAuthActivity.class));

            }
        });


        stHelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //@// TODO: 2017/10/9  跳转 帮助中心页面

                startActivity(new Intent(getActivity(),HelperCenterActivity.class));

            }
        });

        stSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //@// TODO: 2017/10/9  跳转 设置页面

                startActivity(new Intent(getActivity(),SettingActivity.class));

            }
        });



    }

}
