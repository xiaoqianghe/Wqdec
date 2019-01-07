package com.yltx.modulewd.aboutcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulewd.R;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.BankListRs;
import com.yltx.modulewd.entity.Consta;
import com.yltx.modulewd.entity.SaveBankResult;
import com.yltx.modulewd.net.RxRetrofit;
import com.yltx.modulewd.user.HelperCenterActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Wq
 * Date：2017/10/9 15:00
 * Description：//todo  我的银行卡页面
 */

public class BankCardListActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private LinearLayout llAddContainer;




    private BankCardListAdapter adapter=new BankCardListAdapter();
    private List<BankListRs.InfoListBean>   mDatas=new ArrayList<BankListRs.InfoListBean>();


    @Override
    protected int initLayout() {
        return R.layout.activity_bankcard_list;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        initToolBar(true, "我的银行卡", null);
        mRecyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        llAddContainer=(LinearLayout)findViewById(R.id.ll_add_container);


    }



    private void initEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Log.d(TAG,"===================setOnItemClickListener");
                Intent intent =new Intent(BankCardListActivity.this,CardDeatilActivity.class);
                intent.putExtra(Consta.cardType.CARDTYPE,mDatas.get(position).getCardType());
                startActivity(intent);
            }
        });


        llAddContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BankCardListActivity.this,ChangeBankCardActivity.class));
            }
        });


    }

    @Override
    protected void initData() {
        loadListData();
        initEvent();
    }

    private void loadListData() {
        RxRetrofit.getInstance().getApiService().getBankList(ACache.get(mContext).getAsString(UserInfo.USER_ID))
                .compose(this.<BankListRs>bindToLifecycle())
                .compose(RxSchedulers.<BankListRs>io_main())
                .subscribe(new NetObserver<BankListRs>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, BankListRs result) {
                        if ("success".equals(result.getCode())) {
                            mDatas=result.getInfoList();
                            if(null!=mDatas&&mDatas.size()>0){
                                setData(mDatas);
                            }
                        } else {
                            Toast.makeText(mContext, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    private void setData(List<BankListRs.InfoListBean> infoList) {
        if(null!=infoList&&infoList.size()==2){
            llAddContainer.setVisibility(View.GONE);
        }
        adapter.setNewData(infoList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }


}
