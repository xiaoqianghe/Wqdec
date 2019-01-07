package com.yltx.modulewd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulewd.borrow.lsit.ListActivity;

import java.util.List;

public class MainActivity extends BaseActivity {
    private HomeFragment homeFragment;
    private MyFragment myFragment;
    private TextView main_home;
    private TextView main_my;
    private TextView sub_title;
    private int index;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "小白卡", "借还记录");
        String merchantNo =  getIntent().getStringExtra("merchantNo");
        main_home = (TextView) findViewById(R.id.main_home);
        main_my = (TextView) findViewById(R.id.main_my);
        sub_title = (TextView) findViewById(R.id.toolbar_subtitle);
        main_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 0;
                selectIndex(index);
            }
        });
        main_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 1;
                selectIndex(index);
            }
        });
        sub_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, ListActivity.class));
            }
        });
        if (savedInstanceState != null) {
            List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
            for (Fragment fragment : fragmentList) {
                if (fragment instanceof HomeFragment) {
                    homeFragment = (HomeFragment) fragment;
                }
                if (fragment instanceof MyFragment) {
                    myFragment = (MyFragment) fragment;
                }
            }
        } else {
            homeFragment = HomeFragment.newInstance(merchantNo, "");
            myFragment = MyFragment.newInstance("", "");
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.main_frame, homeFragment);
            fragmentTransaction.add(R.id.main_frame, myFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void initData() {
        selectIndex(index);
    }

    private void selectIndex(int index) {
        switch (index) {
            case 0:
                main_home.setSelected(true);
                main_my.setSelected(false);
                main_home.setTextColor(ContextCompat.getColor(mContext, R.color.base_color));
                main_my.setTextColor(ContextCompat.getColor(mContext, R.color.tv6));
                initToolBar(true, "小白卡", "借还记录");
                getSupportFragmentManager().beginTransaction()
                        .show(homeFragment)
                        .hide(myFragment)
                        .commit();
                break;
            case 1:
                main_home.setSelected(false);
                main_my.setSelected(true);
                main_home.setTextColor(ContextCompat.getColor(mContext, R.color.tv6));
                main_my.setTextColor(ContextCompat.getColor(mContext, R.color.base_color));
                initToolBar(false, "账户", null);
                getSupportFragmentManager().beginTransaction()
                        .show(myFragment)
                        .hide(homeFragment)
                        .commit();

                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (index != 0) {
                index = 0;
                selectIndex(index);
            } else {
                finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
