package com.yltx.modulewd.widght;

import com.yltx.modulebase.widget.AbsDialog;
import com.yltx.modulebase.widget.ViewConvertListener;
import com.yltx.modulebase.widget.ViewHolder;
import com.yltx.modulewd.R;
import com.yltx.modulewd.widght.kb.PasswordView;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/9.
 */

public class PassWordDialog extends AbsDialog {
    private ViewConvertListener convertListener;
    public static PassWordDialog newInstance() {
        PassWordDialog dialog = new PassWordDialog();
        return dialog;
    }

    @Override
    public int intLayoutId() {
        return R.layout.dialog_kb;
    }

    @Override
    public void convertView(ViewHolder holder, AbsDialog dialog) {
        if (convertListener != null) {
            convertListener.convertView(holder, dialog);
        }
    }

    public PassWordDialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = convertListener;
        return this;
    }
}
