package com.yltx.modulebase.widget;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.yltx.modulebase.R;


/**
 * 功能描述:
 * Created by ixzus on 2017/8/4.
 */

public abstract class AbsDialog extends DialogFragment {
    private static final String MARGIN = "margin";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DIM = "dim_amount";
    private static final String BOTTOM = "show_bottom";
    private static final String CANCEL = "out_cancel";
    private static final String ANIM = "anim_style";
    private static final String LAYOUT = "layout_id";

    private int margin;//左右边距
    private int width;//宽度
    private int height;//高度
    private float dimAmount = 0.5f;//灰度深浅
    private boolean showBottom;//是否底部显示
    private boolean outCancel = true;//是否点击外部取消
    @StyleRes
    private int animStyle;
    @LayoutRes
    protected int layoutId;

    public abstract int intLayoutId();

    public abstract void convertView(ViewHolder holder, AbsDialog dialog);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AbsDialog);
        layoutId = intLayoutId();

        //恢复保存的数据
        if (savedInstanceState != null) {
            margin = savedInstanceState.getInt(MARGIN);
            width = savedInstanceState.getInt(WIDTH);
            height = savedInstanceState.getInt(HEIGHT);
            dimAmount = savedInstanceState.getFloat(DIM);
            showBottom = savedInstanceState.getBoolean(BOTTOM);
            outCancel = savedInstanceState.getBoolean(CANCEL);
            animStyle = savedInstanceState.getInt(ANIM);
            layoutId = savedInstanceState.getInt(LAYOUT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId, container, false);
        convertView(ViewHolder.create(view), this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    /**
     * 屏幕旋转等导致DialogFragment销毁后重建时保存数据
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MARGIN, margin);
        outState.putInt(WIDTH, width);
        outState.putInt(HEIGHT, height);
        outState.putFloat(DIM, dimAmount);
        outState.putBoolean(BOTTOM, showBottom);
        outState.putBoolean(CANCEL, outCancel);
        outState.putInt(ANIM, animStyle);
        outState.putInt(LAYOUT, layoutId);
    }

    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null) {
            //设置dialog进入、退出的动画
            window.setWindowAnimations(animStyle);
            WindowManager.LayoutParams lp = window.getAttributes();
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount;
            //是否在底部显示
            if (showBottom) {
                lp.gravity = Gravity.BOTTOM;
            }
            //设置dialog宽度
            if (width == 0) {
                lp.width = DialogWHUtil.getScreenWidth(getContext()) - 2 * DialogWHUtil.dp2px(getContext(), margin);
            } else {
                lp.width = DialogWHUtil.dp2px(getContext(), width);
            }
            //设置dialog高度
            if (height == 0) {
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                lp.height = DialogWHUtil.dp2px(getContext(), height);
            }

            window.setAttributes(lp);
        }
        setCancelable(outCancel);
    }

    public AbsDialog setMargin(int margin) {
        this.margin = margin;
        return this;
    }

    public AbsDialog setWidth(int width) {
        this.width = width;
        return this;
    }

    public AbsDialog setHeight(int height) {
        this.height = height;
        return this;
    }

    public AbsDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    public AbsDialog setShowBottom(boolean showBottom) {
        this.showBottom = showBottom;
        return this;
    }

    public AbsDialog setOutCancel(boolean outCancel) {
        this.outCancel = outCancel;
        return this;
    }

    public AbsDialog setAnimStyle(@StyleRes int animStyle) {
        this.animStyle = animStyle;
        return this;
    }

    public AbsDialog show(FragmentManager manager) {
        super.show(manager, String.valueOf(System.currentTimeMillis()));
//        show(manager, String.valueOf(System.currentTimeMillis()));
        return this;
    }

//    @Override
//    public void show(FragmentManager manager, String tag) {
//        mDismissed = false;
//        mShownByMe = true;
//        FragmentTransaction ft = manager.beginTransaction();
//        ft.add(this, tag);
//        ft.commitAllowingStateLoss();
//    }

}