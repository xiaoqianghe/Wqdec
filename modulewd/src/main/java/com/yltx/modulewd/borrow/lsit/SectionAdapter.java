package com.yltx.modulewd.borrow.lsit;

import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.modulewd.R;

import java.util.List;

/**
 * 功能描述:
 * Created by ixzus on 2017/9/28.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {
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

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<MySection> data, String type) {
        super(layoutResId, sectionHeadResId, data);
        this.type = type;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MySection item) {
        helper.setText(R.id.rv_head_title, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MySection item) {
        helper.addOnClickListener(R.id.cb_item);
        if ("借款".equals(type)) {
            helper.getView(R.id.cb_item).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.cb_item).setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_money, "待还"+item.t.getTitleMoney()+"元");
        helper.setText(R.id.tv_submoney, "借款"+item.t.getSubMoney()+"元");
        helper.setText(R.id.tv_time, item.t.time);
        helper.setText(R.id.tv_stages, "剩余"+item.t.stage+"期");
        helper.setChecked(R.id.cb_item, item.t.isCheck);
        //1-借款待审核   2-待放款    3-已放款    4-借款失败    5-还款中   6-已逾期  7-已还清
        String status="";
        switch (item.t.getStatus()){
            case "1":
                status = "借款待审核";
                helper.getView(R.id.cb_item).setEnabled(false);
                helper.setBackgroundColor(R.id.tv_status, Color.parseColor("#ff6d19"));
                break;
            case "2":
                status = "待放款";
                helper.getView(R.id.cb_item).setEnabled(false);
                helper.setBackgroundColor(R.id.tv_status, Color.parseColor("#ff6d19"));
                break;
            case "3":
                status = "已放款";
                helper.getView(R.id.cb_item).setEnabled(true);
                helper.setBackgroundColor(R.id.tv_status, Color.parseColor("#32a7f6"));
                break;
            case "4":
                status = "借款失败";
                helper.getView(R.id.cb_item).setEnabled(false);
                helper.setBackgroundColor(R.id.tv_status, Color.parseColor("#acacac"));
                break;
            case "5":
                status = "还款中";
                helper.getView(R.id.cb_item).setEnabled(true);
                helper.setBackgroundColor(R.id.tv_status, Color.parseColor("#32a7f6"));
                break;
            case "6":
                status = "已逾期";
                helper.getView(R.id.cb_item).setEnabled(true);
                helper.setBackgroundColor(R.id.tv_status, Color.parseColor("#fcffff"));
                break;
            case "7":
                status = "已还清";
                helper.getView(R.id.cb_item).setEnabled(false);
                helper.setBackgroundColor(R.id.tv_status, Color.parseColor("#32a7f6"));
                break;
        }
        helper.setText(R.id.tv_status, status);
    }
}
