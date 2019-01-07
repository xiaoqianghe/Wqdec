package com.yltx.modulewd.borrow.amortization;

import android.util.SparseBooleanArray;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.modulewd.R;
import com.yltx.modulewd.entity.InstallmentDetail;

/**
 * 功能描述:
 * Created by ixzus on 2017/9/28.
 */

public class AmortizationAdapter extends BaseQuickAdapter<InstallmentDetail.DataBean.RepaymentListBean, BaseViewHolder> {

    private String type;

    private SparseBooleanArray booleanArray = new SparseBooleanArray();

    public AmortizationAdapter(String type) {
        super(R.layout.rv_amortization_item);
        this.type = type;
    }

    public void addCheck(int position, boolean flag) {
        booleanArray.put(position, flag);
    }

    public boolean isCheck(int position) {
        return booleanArray.get(position);
    }

    public void addAllCheck() {
        for (int i = 0; i < mData.size(); i++) {
            booleanArray.put(i, true);
        }
        notifyDataSetChanged();
    }

    public void removeAllCheck() {
        for (int i = 0; i < mData.size(); i++) {
            booleanArray.put(i, false);
        }
        notifyDataSetChanged();
    }


    @Override
    protected void convert(BaseViewHolder helper, final InstallmentDetail.DataBean.RepaymentListBean item) {
        if ("single".equals(type)||"more".equals(type)) {
            helper.getView(R.id.cb_item).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.cb_item).setVisibility(View.GONE);
        }
        helper.setChecked(R.id.cb_item, booleanArray.get(mData.indexOf(item)));
        helper.addOnClickListener(R.id.cb_item);
        helper.addOnClickListener(R.id.tv_detail);
        helper.setText(R.id.tv_money, "借款" + item.getTotalAmount() + "元");
        helper.setText(R.id.tv_time, "" + item.getBorrowDate());
        helper.setText(R.id.tv_stages, "第" + item.getCurNum() + "期");
        helper.setText(R.id.tv_status, "" + item.getStatusName());
        helper.setText(R.id.tv_num, "分" + item.getRepaymentNum() + "期");
        helper.setText(R.id.tv_type, "每期等额");
        helper.setText(R.id.tv_date, "" + item.getExpiryDateFormat());
        helper.setText(R.id.tv_overdue_day, item.getOverdueDays() + "天");
        helper.setText(R.id.tv_overdue_money, item.getInterest()+"元");

        //1-借款待审核   2-待放款    3-已放款    4-借款失败    5-还款中   6-已逾期  7-已还清

    }
}
