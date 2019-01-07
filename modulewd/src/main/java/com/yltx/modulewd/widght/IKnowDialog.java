package com.yltx.modulewd.widght;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.yltx.modulebase.widget.AbsDialog;
import com.yltx.modulebase.widget.ViewHolder;
import com.yltx.modulewd.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IKnowDialog extends AbsDialog {
    private String type;
    private List<String> listInfo;

    public static IKnowDialog newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        IKnowDialog dialog = new IKnowDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    public static IKnowDialog newInstance(String type, ArrayList<String> listInfo) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putStringArrayList("listInfo", listInfo);
        IKnowDialog dialog = new IKnowDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        type = bundle.getString("type");
        listInfo = bundle.getStringArrayList("listInfo");

    }

    @Override
    public int intLayoutId() {
        return R.layout.dialog_iknow;
    }

    @Override
    public void convertView(final ViewHolder viewHolder, final AbsDialog dialog) {

        viewHolder.setOnClickListener(R.id.btn_do, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOkListener.convertView(viewHolder, dialog);
            }
        });

        if ("借款待审核".equals(type)) {
            String str = "您当前有一笔借款为借款待审核，待平台审核通过后才能再次借钱哦~";
            Spannable span = new SpannableString(str);
            span.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6D19")), 9, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            viewHolder.setText(R.id.title, "温馨提示");
            ((TextView) viewHolder.getView(R.id.message)).setText(span);
            viewHolder.setText(R.id.btn_do, "我知道了");
        }


    }

    private ConfirmOkListener confirmOkListener;

    public interface ConfirmOkListener extends Serializable {
        long serialVersionUID = System.currentTimeMillis();

        void convertView(ViewHolder holder, AbsDialog dialog);
    }

    public IKnowDialog setConfirmOkListener(ConfirmOkListener confirmOkListener) {
        this.confirmOkListener = confirmOkListener;
        return this;
    }

}