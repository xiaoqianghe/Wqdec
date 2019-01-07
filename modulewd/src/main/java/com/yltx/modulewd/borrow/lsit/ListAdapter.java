package com.yltx.modulewd.borrow.lsit;

import android.view.View;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.modulewd.R;

import java.util.List;

/**
 * 功能描述:
 * Created by ixzus on 2017/9/28.
 */

public class ListAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {
    private String type;


    public void addAllCheck() {
        for (MySection mySection : mData) {
            if (!mySection.isHeader) {
                if("1".equals(mySection.t.getStatus()) || "2".equals(mySection.t.getStatus())
                        ||"4".equals(mySection.t.getStatus())||"7".equals(mySection.t.getStatus())){
                    continue;
                }
                mySection.t.setCheck(true);
            }
        }
        notifyDataSetChanged();
    }

    public void removeAllCheck() {
        for (MySection mySection : mData) {
            if (!mySection.isHeader) {
                mySection.t.setCheck(false);
            }
        }
        notifyDataSetChanged();
    }

    public ListAdapter(int layoutResId, int sectionHeadResId, List<MySection> data, String type) {
        super(layoutResId, sectionHeadResId, data);
        this.type = type;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MySection item) {
        helper.setText(R.id.rv_head_title, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MySection item) {
        helper.getView(R.id.cb_item).setVisibility(View.GONE);

        helper.setText(R.id.tv_money, "待还"+item.t.getTitleMoney()+"元");
        helper.setText(R.id.tv_submoney, "借款"+item.t.getSubMoney()+"元");
        helper.setText(R.id.tv_time, item.t.time);
        helper.setText(R.id.tv_stages, "剩余"+item.t.stage+"期");
        helper.setChecked(R.id.cb_item, item.t.isCheck);
//        0-待还款   1-逾期待还款   2-还款成功   3-坏账
        if ("2".equals(item.t.status)) {
            helper.setText(R.id.tv_status, "已还清");
        }else {
            helper.setText(R.id.tv_status, "");
        }
    }
}
