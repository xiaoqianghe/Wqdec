package com.yltx.modulewd.borrow.amortization;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.modulewd.R;
import com.yltx.modulewd.borrow.lsit.MySection;

import java.util.List;

/**
 * 功能描述:
 * Created by ixzus on 2017/9/28.
 */

public class AmortizationLoanAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {
    private String type;


    public AmortizationLoanAdapter(int layoutResId, int sectionHeadResId, List<MySection> data, String type) {
        super(layoutResId, sectionHeadResId, data);
        this.type = type;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MySection item) {
        helper.setText(R.id.rv_head_title, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MySection item) {

        helper.setText(R.id.tv_data, "" + item.t.getTitleMoney());
        helper.setText(R.id.tv_tip, "待还" + item.t.getSubMoney() + "元");

        //1-借款待审核   2-待放款    3-已放款    4-借款失败    5-还款中   6-已逾期  7-已还清

    }
}
