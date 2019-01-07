package com.yltx.modulewd.widght;

import com.yltx.modulebase.widget.AbsDialog;
import com.yltx.modulebase.widget.ViewConvertListener;
import com.yltx.modulebase.widget.ViewHolder;
import com.yltx.modulewd.R;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/9.
 */

public class PassWord3Dialog extends AbsDialog {
    private ViewConvertListener convertListener;
    public static PassWord3Dialog newInstance() {
        PassWord3Dialog dialog = new PassWord3Dialog();
        return dialog;
    }

    @Override
    public int intLayoutId() {
        return R.layout.dialog_kb3;
    }

    @Override
    public void convertView(ViewHolder holder, AbsDialog dialog) {
        if (convertListener != null) {
            convertListener.convertView(holder, dialog);
        }
    }

    public PassWord3Dialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = convertListener;
        return this;
    }
}
