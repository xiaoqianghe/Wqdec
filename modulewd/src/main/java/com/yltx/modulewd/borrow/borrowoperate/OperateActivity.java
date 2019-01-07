package com.yltx.modulewd.borrow.borrowoperate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulebase.util.MD5;
import com.yltx.modulebase.widget.AbsDialog;
import com.yltx.modulebase.widget.ViewConvertListener;
import com.yltx.modulebase.widget.ViewHolder;
import com.yltx.modulewd.R;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.AddBorrowRsBean;
import com.yltx.modulewd.entity.AnnualFeeInfo;
import com.yltx.modulewd.entity.Borrow;
import com.yltx.modulewd.net.RxRetrofit;
import com.yltx.modulewd.widght.LoadingStatusView;
import com.yltx.modulewd.widght.PassWordDialog;
import com.yltx.modulewd.widght.RecyclerViewDialog;
import com.yltx.modulewd.widght.kb.OnPasswordInputFinish;
import com.yltx.modulewd.widght.kb.PasswordView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Wq
 * Date：2017/10/10 14:05
 * Description：//todo
 */

public class OperateActivity extends BaseActivity {

    public TextView toolbar_title;
    public TextView toolbar_subtitle;

    private SuperTextView stBorrowValue;

    private SuperTextView stBorrowCycleTiem;

    private SuperTextView stBorrowBacktime;

    private SuperTextView stBorrowCharge;

    private SuperTextView stBorrowPeriod, stBorrowType;


    private TextView tvBrrowBankName;
    private TextView tvBrrowBankTime;
    private SuperButton btToNext;

    private EditText et_money;


    @Override
    protected int initLayout() {
        return R.layout.activity_brrow;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        initToolBar(true, "借钱", null);

        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_subtitle = (TextView) findViewById(R.id.toolbar_subtitle);

        stBorrowValue = (SuperTextView) findViewById(R.id.st_borrow_value);
        stBorrowCycleTiem = (SuperTextView) findViewById(R.id.st_brrow_cycletime);
        stBorrowBacktime = (SuperTextView) findViewById(R.id.st_brrow_backtime);
        stBorrowCharge = (SuperTextView) findViewById(R.id.st_brrow_charge);

        stBorrowPeriod = (SuperTextView) findViewById(R.id.st_brrow_backperiod);
        stBorrowType = (SuperTextView) findViewById(R.id.st_brrow_backtype);

        tvBrrowBankName = (TextView) findViewById(R.id.tv_brrow_bank_name);
        tvBrrowBankTime = (TextView) findViewById(R.id.tv_borrow_banktime);

        et_money = (EditText) findViewById(R.id.myca_et_comfirepwd);

        btToNext = (SuperButton) findViewById(R.id.bt_tonext);


        initEvent();


    }

    @Override
    protected void initData() {
        loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID));
    }

    private void initEvent() {

        btToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(OperateActivity.this, OperateStatusActivity.class));
//                finish();
                Log.e(TAG, "AA_" + stBorrowValue.getRightString());
                if (TextUtils.isEmpty(et_money.getText().toString().trim())) {
                    Toast.makeText(mContext, "输入借款金额不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(limit)) {
                    double tem = 0.0;
                    double tem2 = 0.0;
                    try {
                        tem = Double.valueOf(limit);
                        tem2 = Double.valueOf(et_money.getText().toString().trim());
                    } catch (NumberFormatException e) {
                    }
                    if (tem < tem2) {
                        Toast.makeText(mContext, "超出限额!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }


                if (TextUtils.isEmpty(stBorrowCycleTiem.getRightString().toString().trim())) {
                    Toast.makeText(mContext, "输入借款周期不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }

                dialogPwd();
            }
        });
    }

    LoadingStatusView statusView;
    AbsDialog statusDialog;
    TextView statusTip;

    private void dialogPwd() {
        PassWordDialog.newInstance()
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final AbsDialog dialog) {
                        statusDialog = dialog;
                        holder.getView(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                        ((PasswordView) holder.getView(R.id.pwd_view)).setOnFinishInput(new OnPasswordInputFinish() {
                            @Override
                            public void inputFinish(String password) {
//                                doBoroow(password);
//                                dialog.dismiss();
                                holder.getView(R.id.ll_content).setVisibility(View.INVISIBLE);
                                holder.getView(R.id.ll_load).setVisibility(View.VISIBLE);
                                statusView = (LoadingStatusView) holder.getView(R.id.statusView);
                                statusTip = holder.getView(R.id.statusTip);
                                statusView.loadLoading();
                                doBoroow(password);
                            }
                        });
                    }
                })
                .setDimAmount(0.3f)
                .setAnimStyle(R.style.DialogButtonStyle)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    private void doBoroow(String pwd) {
        if(null == borrowTermsBean){
            Toast.makeText(mContext, "请选择", Toast.LENGTH_SHORT).show();
            return;
        }
        Borrow borrow = new Borrow();
        borrow.setMemberId(ACache.get(mContext).getAsString(UserInfo.USER_ID));
        borrow.setBorrowLimit(et_money.getText().toString());
        borrow.setBorrowTerm("" + borrowTermsBean.getTerm());
        borrow.setFirstRepaymentTime("" + borrowTermsBean.getRepaymentTime());
        borrow.setRepaymentNum("2");
        borrow.setPaymentPassword(MD5.getMessageDigest(pwd.getBytes()));

        RxRetrofit.getInstance().getApiService().addBorrow(borrow)
                .compose(this.<AddBorrowRsBean>bindToLifecycle())
                .compose(RxSchedulers.<AddBorrowRsBean>io_main())
                .subscribe(new NetObserver<AddBorrowRsBean>(mContext, TAG, 0, false) {
                    @Override
                    public void onSuccess(int whichRequest, final AddBorrowRsBean result) {
                        if ("success".equals(result.getCode())) {
                            statusView.loadSuccess();
                            statusTip.setText("支付成功");
                            statusTip.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    statusDialog.dismiss();

                                    toSuccess(result.getData());
                                }
                            }, 2000);


                        } else {
                            statusView.loadFailure();
                            statusTip.setText(result.getMessage());
                            statusTip.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    statusDialog.dismiss();
                                }
                            }, 2000);
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {
                        statusView.loadFailure();
                        statusTip.setText("支付失败");
                        statusTip.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                statusDialog.dismiss();
                            }
                        }, 2000);

                    }
                });
    }

    private void toSuccess(AddBorrowRsBean.DataBean data) {
        ACache.get(mContext).put("OperateStatusId", "" + data.getBorrowId());
        ACache.get(mContext).put("OperateStatusFrom", "loan");
        Intent intent = new Intent(OperateActivity.this, OperateStatusActivity.class);
//        intent.putExtra("borrowId", data.getBorrowId());
        startActivity(intent);
        finish();

    }

    private void loadData(String memberid) {
//        memberid="4";//@// TODO: 2017/10/17  测试
        RxRetrofit.getInstance().getApiService().getAnnualFeeInfo(memberid)
                .compose(this.<AnnualFeeInfo>bindToLifecycle())
                .compose(RxSchedulers.<AnnualFeeInfo>io_main())
                .subscribe(new NetObserver<AnnualFeeInfo>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, AnnualFeeInfo annualFeeInfo) {
                        if ("success".equals(annualFeeInfo.getCode())) {
                            refreshUI(annualFeeInfo.getData());
                        } else {
                            Toast.makeText(mContext, "" + annualFeeInfo.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    BorrowTremAdapter adapter;
    List<AnnualFeeInfo.DataBean.BorrowTermsBean> listData = new ArrayList<>();
    AnnualFeeInfo.DataBean.BorrowTermsBean borrowTermsBean;
    private String limit;

    private void refreshUI(AnnualFeeInfo.DataBean data) {
        if(null ==data || null == data.getBorrowTerms() || null == data.getBorrowTerms().get(0)){
            return;
        }
        borrowTermsBean = data.getBorrowTerms().get(0);
        limit = data.getLimitUnused();
        et_money.setHint("当前最多可以借 " + limit + " 元");
        stBorrowCycleTiem.setRightString(""+borrowTermsBean.getTerm());
        stBorrowBacktime.setRightString(""+borrowTermsBean.getRepaymentTime());
        stBorrowPeriod.setRightString("分" + borrowTermsBean.getTermNum() + "期");

        tvBrrowBankName.setText(data.getBankName() + "  (尾号 " + SubBankCarNo(data.getBankCardNo()) + " )");//还款
        stBorrowCharge.setRightString(data.getLoanServiceCharge() + "%");
        tvBrrowBankTime.setText("(" + SubTimes(borrowTermsBean.getRepaymentTime()) + "系统将自动从该卡进行扣款已完成退款)");//(1月5日系统将自动从该卡进行扣款已完成退款)

        stBorrowPeriod = (SuperTextView) findViewById(R.id.st_brrow_backperiod);
        stBorrowType = (SuperTextView) findViewById(R.id.st_brrow_backtype);


        stBorrowType.setRightString("每期等额");


        listData = data.getBorrowTerms();
        adapter = new BorrowTremAdapter();
        adapter.setNewData(listData);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                view.setSelected(true);
                borrowTermsBean = (AnnualFeeInfo.DataBean.BorrowTermsBean) adapter.getData().get(position);
                stBorrowCycleTiem.setRightString(borrowTermsBean.getTerm());
                stBorrowBacktime.setRightString(SubTimes(borrowTermsBean.getRepaymentTime()));
                tvBrrowBankTime.setText("(" + SubTimes(borrowTermsBean.getRepaymentTime()) + "系统将自动从该卡进行扣款已完成退款)");//(1月5日系统将自动从该卡进行扣款已完成退款)

                stBorrowPeriod.setRightString("分" + borrowTermsBean.getTermNum() + "期");

                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stagesDialog.dismiss();
                    }
                }, 500);

            }
        });
        stBorrowCycleTiem.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
            @Override
            public void onClickListener() {
                showDialog();
            }
        });
    }


    //@// TODO: 2017/10/17 取出银行卡号后4位
    public String SubBankCarNo(String str) {
        String newStr = "";
        if (!TextUtils.isEmpty(str)) {
            newStr = str.substring(str.length() - 4, str.length());
        }
        return newStr;
    }


    //@// TODO: 2017/10/17 分隔出XX月XX日
    public String SubTimes(String str) {
        String newStr = "";
        if (!TextUtils.isEmpty(str)) {
//            newStr=str.substring(5,str.length());

            newStr = str.substring(str.indexOf("-") + 1, str.length());
            newStr = newStr.replace("-", "月") + "日";

        }
        return newStr;
    }

    private RecyclerView recyclerView;
    private AbsDialog stagesDialog;

    private void showDialog() {
        RecyclerViewDialog.newInstance()
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final AbsDialog dialog) {
                        stagesDialog = dialog;
                        recyclerView = holder.getView(R.id.recyclerView);
                        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
                        recyclerView.setAdapter(adapter);
                        holder.getView(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });


                    }
                })
                .setDimAmount(0.3f)
                .setAnimStyle(R.style.DialogButtonStyle)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

}
