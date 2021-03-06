package com.yltx.modulewd.widght;

import com.yltx.modulebase.widget.AbsDialog;
import com.yltx.modulebase.widget.ViewConvertListener;
import com.yltx.modulebase.widget.ViewHolder;
import com.yltx.modulewd.R;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/9.
 */

public class RecyclerViewDialog extends AbsDialog {
    private ViewConvertListener convertListener;

    public static RecyclerViewDialog newInstance() {
        RecyclerViewDialog dialog = new RecyclerViewDialog();
        return dialog;
    }

    @Override
    public int intLayoutId() {
        return R.layout.dialog_recyclerview;
    }

    @Override
    public void convertView(ViewHolder holder, AbsDialog dialog) {
        if (convertListener != null) {
            convertListener.convertView(holder, dialog);
        }
    }

    public RecyclerViewDialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = convertListener;
        return this;
    }
}
