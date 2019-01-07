package com.yltx.modulewd.borrow.borrowoperate;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yltx.modulewd.R;
import com.yltx.modulewd.entity.AnnualFeeInfo;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/23.
 */

public class BorrowTremAdapter extends BaseQuickAdapter<AnnualFeeInfo.DataBean.BorrowTermsBean, BaseViewHolder> {
    public BorrowTremAdapter() {
        super(R.layout.rv_stages_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnnualFeeInfo.DataBean.BorrowTermsBean item) {
        helper.addOnClickListener(R.id.tv_stages);
        helper.setText(R.id.tv_stages, item.getTerm());
    }
}
