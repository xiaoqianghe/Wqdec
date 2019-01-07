package com.yltx.modulewd.borrow.lsit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.widget.AbsDialog;
import com.yltx.modulebase.widget.ViewConvertListener;
import com.yltx.modulebase.widget.ViewHolder;
import com.yltx.modulewd.R;
import com.yltx.modulewd.widght.FilterDialog;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends BaseActivity implements ListFragment.OnFragmentInteractionListener, LoanFragment.LoanFragmentListener {

    private TabLayout tablayout;
    private ViewPager viewpager;
    private TextView toolbar_subtitle;
    // FIXME: 2017/10/18
//    private ListFragment loanFragment;
    private LoanFragment loanFragment;
    private ListFragment repaymentFragment;
    private List<Fragment> viewList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private List<String> toolbarTitleList = new ArrayList<>();

    private int curFragment;
    private int curDialogItem;
    private int curDialogItem2;


    @Override
    protected int initLayout() {
        return R.layout.activity_list;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "借款记录", null);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        toolbar_subtitle = (TextView) findViewById(R.id.toolbar_subtitle);
        initFragment();
//        viewpager.setCurrentItem(0);
//        tablayout.getTabAt(0).select();
    }

    @Override
    protected void initData() {
        toolbar_subtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (0 == curFragment) {
                    dialogFilter();
                } else {
                    dialogFilter2();
                }
            }
        });
    }

    private void dialogFilter() {
        FilterDialog.newInstance()
                .setLayoutId(R.layout.dialog_filter)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final AbsDialog dialog) {
                        if (0 != curDialogItem) {
                            clearAllViewStatus(holder);
                            viewStatus(holder.getView(curDialogItem));
                        }
                        holder.getView(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        holder.getView(R.id.tv_all).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view, dialog);
                            }
                        });
                        holder.getView(R.id.tv_for_lend).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view, dialog);
                            }
                        });
                        holder.getView(R.id.tv_loan).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view, dialog);
                            }
                        });
                        holder.getView(R.id.tv_check_pending).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view, dialog);
                            }
                        });
                        holder.getView(R.id.tv_overdue).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view, dialog);
                            }
                        });
                        holder.getView(R.id.tv_brrow_fail).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view, dialog);
                            }
                        });
                    }
                })
                .setDimAmount(0.3f)
                .setAnimStyle(R.style.DialogButtonStyle)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    private String status;
    private void dialogFilter2() {
        FilterDialog.newInstance()
                .setLayoutId(R.layout.dialog_filter2)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final AbsDialog dialog) {
                        if (0 != curDialogItem2) {
                            clearAllViewStatus(holder);
                            viewStatus(holder.getView(curDialogItem2));
                        }
                        holder.getView(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        holder.getView(R.id.tv_all).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                status = null;
                                dialogItem(holder, view, dialog);
                            }
                        });
                        holder.getView(R.id.tv_for_lend).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                status = "0";
                                dialogItem(holder, view, dialog);
                            }
                        });
                        holder.getView(R.id.tv_loan).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                status = "1";
                                dialogItem(holder, view, dialog);
                            }
                        });
                        holder.getView(R.id.tv_check_pending).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                status = "2";
                                dialogItem(holder, view, dialog);
                            }
                        });
                    }
                })
                .setDimAmount(0.3f)
                .setAnimStyle(R.style.DialogButtonStyle)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    private void dialogItem(ViewHolder holder, View view, final AbsDialog dialog) {
        if (0 == curFragment) {
            curDialogItem = view.getId();
//            tablayout.getTabAt(0).setText("借款记录（0）");
        } else {
            curDialogItem2 = view.getId();
//            tablayout.getTabAt(1).setText("还款记录（0）");
            repaymentFragment.activityToListFragment(status);
        }
        clearAllViewStatus(holder);
        viewStatus(view);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 200);
        // FIXME: 2017/10/18
//        if (0 == curFragment) {
//            loanFragment.activityToFragment(curFragment, curDialogItem);
//        } else {
//            repaymentFragment.activityToFragment(curFragment, curDialogItem);
//        }
    }

    private void clearAllViewStatus(ViewHolder holder) {
        if (0 == curFragment) {
            clearViewStatus(holder.getView(R.id.tv_all));
            clearViewStatus(holder.getView(R.id.tv_for_lend));
            clearViewStatus(holder.getView(R.id.tv_loan));
            clearViewStatus(holder.getView(R.id.tv_check_pending));
            clearViewStatus(holder.getView(R.id.tv_overdue));
            clearViewStatus(holder.getView(R.id.tv_brrow_fail));
        } else {
            clearViewStatus(holder.getView(R.id.tv_all));
            clearViewStatus(holder.getView(R.id.tv_for_lend));
            clearViewStatus(holder.getView(R.id.tv_loan));
            clearViewStatus(holder.getView(R.id.tv_check_pending));
        }
    }

    private void clearViewStatus(View view) {
        ((TextView) view).setTextColor(ContextCompat.getColor(mContext, R.color.tv3));
        view.setSelected(false);
    }

    private void viewStatus(View view) {
        ((TextView) view).setTextColor(ContextCompat.getColor(mContext, R.color.tvo));
        view.setSelected(true);
    }

    private void initFragment() {
        viewList.clear();
        titleList.add("借款记录（0）");
        titleList.add("还款记录（0）");
        toolbarTitleList.add("借款记录");
        toolbarTitleList.add("还款记录");
        // FIXME: 2017/10/18
//        loanFragment = ListFragment.newInstance("借款", "");
        loanFragment = LoanFragment.newInstance("", "");
        repaymentFragment = ListFragment.newInstance("还款", "");
        viewList.add(loanFragment);
        viewList.add(repaymentFragment);
        for (String str : titleList) {
            tablayout.addTab(tablayout.newTab().setText(str));
        }
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return viewList.get(position);
            }

            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curFragment = position;
                if (0 != position) {
                    initToolBar(true, toolbarTitleList.get(position), "筛选");
                } else {
                    initToolBar(true, toolbarTitleList.get(position), null);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setOffscreenPageLimit(1);
        tablayout.setupWithViewPager(viewpager);
    }


    @Override
    public void onFragmentInteraction(String status) {

    }

    @Override
    public void onLoanFragmentNum(String num1, String num2) {
        updateNum(num1, num2);
    }
    public void updateNum(String num1, String num2){
        if (!TextUtils.isEmpty(num1)) {
            tablayout.getTabAt(0).setText("借款记录（" + num1 + "）");
        }
        if (!TextUtils.isEmpty(num2)) {
            tablayout.getTabAt(1).setText("还款记录（" + num2 + "）");
        }
    }
}
