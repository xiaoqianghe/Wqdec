package com.eeepay.libbase.widget.dialog;

import android.content.Context;
import android.util.DisplayMetrics;
/**
 * Created by ixzus on 2017/12/27.
 * Email: iandroid@foxmail.com
 * Desc:
 */

public class DialogWHUtil {
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }
}