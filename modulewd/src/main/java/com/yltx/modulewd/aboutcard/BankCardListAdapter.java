package com.yltx.modulewd.aboutcard;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.modulewd.R;
import com.yltx.modulewd.entity.BankListRs;
import com.yltx.modulewd.entity.Consta;

/**
 * Author：Wq
 * Date：2017/10/9 15:08
 * Description：//todo
 */

public class BankCardListAdapter extends BaseQuickAdapter<BankListRs.InfoListBean,BaseViewHolder>{


    public BankCardListAdapter() {
        super(R.layout.item_bankcard);
    }

    @Override
    protected void convert(BaseViewHolder helper, BankListRs.InfoListBean item) {

        helper.setText(R.id.tv_phone,"手机尾号 "+item.getReservedMobile());

        helper.setText(R.id.tv_bankcard_name,item.getBankName()+"  ("+getCardTypeName(item.getCardType()) +")");

        helper.setText(R.id.tv_bankcard_num,item.getBankCardNo());

    }


    public String getCardTypeName(String cardType){
        if(cardType.equals(Consta.cardType.DepositBank)){
            return "储值卡";
        }else{
            return "信用卡";
        }
    }




}
