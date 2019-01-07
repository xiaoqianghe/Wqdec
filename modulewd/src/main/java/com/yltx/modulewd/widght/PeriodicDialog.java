package com.yltx.modulewd.widght;

import com.yltx.modulebase.widget.AbsDialog;
import com.yltx.modulebase.widget.ViewConvertListener;
import com.yltx.modulebase.widget.ViewHolder;
import com.yltx.modulewd.R;

/**
 * Author：Wq
 * Date：2017/10/23 11:14
 * Description：//todo
 */

public class PeriodicDialog extends AbsDialog{

    private ViewConvertListener convertListener;
    @Override
    public int intLayoutId() {
        return R.layout.periodic_selection;
    }

    @Override
    public void convertView(ViewHolder holder, AbsDialog dialog) {
        if (convertListener != null) {
            convertListener.convertView(holder, dialog);
        }
    }

    public PeriodicDialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = convertListener;
        return this;
    }
}
