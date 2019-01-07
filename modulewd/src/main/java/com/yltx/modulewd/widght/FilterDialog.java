package com.yltx.modulewd.widght;

import android.support.annotation.LayoutRes;

import com.yltx.modulebase.widget.AbsDialog;
import com.yltx.modulebase.widget.ViewConvertListener;
import com.yltx.modulebase.widget.ViewHolder;
import com.yltx.modulewd.R;

/**
 * 功能描述:
 * Created by ixzus on 2017/10/9.
 */

public class FilterDialog extends AbsDialog {
    private ViewConvertListener convertListener;

    public static FilterDialog newInstance() {
        FilterDialog dialog = new FilterDialog();
        return dialog;
    }

    @Override
    public int intLayoutId() {
        return layoutId;
    }

    public FilterDialog setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    @Override
    public void convertView(ViewHolder holder, AbsDialog dialog) {
        if (convertListener != null) {
            convertListener.convertView(holder, dialog);
        }
    }

    public FilterDialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = convertListener;
        return this;
    }
}
