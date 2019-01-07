package com.yltx.modulewd.borrow.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.allen.library.SuperButton;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulebase.util.MD5;
import com.yltx.modulebase.widget.AbsDialog;
import com.yltx.modulebase.widget.ViewConvertListener;
import com.yltx.modulebase.widget.ViewHolder;
import com.yltx.modulewd.R;
import com.yltx.modulewd.authentication.ali.AuthResult;
import com.yltx.modulewd.authentication.ali.PayResult;
import com.yltx.modulewd.authentication.wx.WeChatBean;
import com.yltx.modulewd.borrow.amortization.AmortizationActivity;
import com.yltx.modulewd.borrow.borrowoperate.OperateStatusActivity;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.Consta;
import com.yltx.modulewd.entity.LoanDetailRsBean;
import com.yltx.modulewd.entity.Order;
import com.yltx.modulewd.entity.OrderResult;
import com.yltx.modulewd.net.RxRetrofit;
import com.yltx.modulewd.widght.LoadingStatusView;
import com.yltx.modulewd.widght.PassWord3Dialog;
import com.yltx.modulewd.widght.PassWordDialog;
import com.yltx.modulewd.widght.PayDialog;
import com.yltx.modulewd.widght.kb.OnPasswordInputFinish;
import com.yltx.modulewd.widght.kb.Password3View;
import com.yltx.modulewd.widght.kb.PasswordView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BorrowDetailActivity extends BaseActivity {
    public TextView toolbar_title;
    public TextView toolbar_subtitle;
    public TextView tv_title;
    public TextView tv_title_money;
    public ImageView iv_status;
    public TextView tv_title_status;
    public TextView tv_no_title;
    public TextView tv_no;
    public TextView tv_money_title;
    public TextView tv_money;
    public TextView tv_status_title;
    public TextView tv_status;
    public TextView tv_cycle_time_title;
    public TextView tv_cycle_time;
    public TextView tv_cycle_time_detail;
    public TextView tv_start_time_title;
    public TextView tv_start_time;
    public TextView tv_end_time_title;
    public TextView tv_end_time;
    public TextView tv_tip_title;
    public TextView tv_tip;
    public TextView tv_bank_title;
    public TextView tv_bank;
    public TextView tv_remark_title;
    public TextView tv_remark;
    public SuperButton btn_do;
    public TextView tv_doc;
    public TextView tv_line;
    public TextView issus;
    private TextView tv_start_end_title;
    private TextView tv_start_end;
    private TextView tv_split_num_title;
    private TextView tv_split_num;
    private TextView tv_pay_method_title;
    private TextView tv_pay_method;
    private TextView tv_pay_num_title;
    private TextView tv_pay_num;
    private TextView tv_pay_money_title;
    private TextView tv_pay_money;
    private TextView tv_need_money_title;
    private TextView tv_need_money;
    private TextView tv_overdue_num_title;
    private TextView tv_overdue_num_tip;
    private TextView tv_overdue_num;
    private TextView tv_overdue_money_title;
    private TextView tv_overdue_money;
    private TextView tv_pay_type_title;
    private TextView tv_pay_type;
    private TextView tv_last_time_title;
    private TextView tv_last_time;
    private SuperButton btn_commit;

    private String mBorrowId;


    @Override
    protected int initLayout() {
        return R.layout.activity_borrow_detail;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "借款详情", null);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_subtitle = (TextView) findViewById(R.id.toolbar_subtitle);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title_money = (TextView) findViewById(R.id.tv_title_money);
        iv_status = (ImageView) findViewById(R.id.iv_status);
        tv_title_status = (TextView) findViewById(R.id.tv_title_status);
        tv_no_title = (TextView) findViewById(R.id.tv_no_title);
        tv_no = (TextView) findViewById(R.id.tv_no);
        tv_money_title = (TextView) findViewById(R.id.tv_money_title);
        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_status_title = (TextView) findViewById(R.id.tv_status_title);
        tv_status = (TextView) findViewById(R.id.tv_status);
        tv_cycle_time_title = (TextView) findViewById(R.id.tv_cycle_time_title);
        tv_cycle_time = (TextView) findViewById(R.id.tv_cycle_time);
        tv_cycle_time_detail = (TextView) findViewById(R.id.tv_cycle_time_detail);
        tv_start_time_title = (TextView) findViewById(R.id.tv_start_time_title);
        tv_start_time = (TextView) findViewById(R.id.tv_start_time);
        tv_end_time_title = (TextView) findViewById(R.id.tv_end_time_title);
        tv_end_time = (TextView) findViewById(R.id.tv_end_time);
        tv_tip_title = (TextView) findViewById(R.id.tv_tip_title);
        tv_tip = (TextView) findViewById(R.id.tv_tip);
        tv_bank_title = (TextView) findViewById(R.id.tv_bank_title);
        tv_bank = (TextView) findViewById(R.id.tv_bank);
        tv_remark_title = (TextView) findViewById(R.id.tv_remark_title);
        tv_remark = (TextView) findViewById(R.id.tv_remark);
        btn_do = (SuperButton) findViewById(R.id.btn_do);
        tv_doc = (TextView) findViewById(R.id.tv_doc);
        tv_line = (TextView) findViewById(R.id.tv_line);
        issus = (TextView) findViewById(R.id.issus);

        tv_start_end_title = (TextView) findViewById(R.id.tv_start_end_title);
        tv_start_end = (TextView) findViewById(R.id.tv_start_end);
        tv_split_num_title = (TextView) findViewById(R.id.tv_split_num_title);
        tv_split_num = (TextView) findViewById(R.id.tv_split_num);
        tv_pay_method_title = (TextView) findViewById(R.id.tv_pay_method_title);
        tv_pay_method = (TextView) findViewById(R.id.tv_pay_method);
        tv_pay_num_title = (TextView) findViewById(R.id.tv_pay_num_title);
        tv_pay_num = (TextView) findViewById(R.id.tv_pay_num);
        tv_pay_money_title = (TextView) findViewById(R.id.tv_pay_money_title);
        tv_pay_money = (TextView) findViewById(R.id.tv_pay_money);
        tv_need_money_title = (TextView) findViewById(R.id.tv_need_money_title);
        tv_need_money = (TextView) findViewById(R.id.tv_need_money);
        tv_overdue_num_title = (TextView) findViewById(R.id.tv_overdue_num_title);
        tv_overdue_num_tip = (TextView) findViewById(R.id.tv_overdue_num_tip);
        tv_overdue_num = (TextView) findViewById(R.id.tv_overdue_num);
        tv_overdue_money_title = (TextView) findViewById(R.id.tv_overdue_money_title);
        tv_overdue_money = (TextView) findViewById(R.id.tv_overdue_money);
        tv_pay_type_title = (TextView) findViewById(R.id.tv_pay_type_title);
        tv_pay_type = (TextView) findViewById(R.id.tv_pay_type);
        tv_last_time_title = (TextView) findViewById(R.id.tv_last_time_title);
        tv_last_time = (TextView) findViewById(R.id.tv_last_time);
        btn_commit = (SuperButton) findViewById(R.id.btn_commit);

//        String FOR_LEND = "待放款";
//        String CHECK_PENDING = "借款待审核";
//        String LOAN = "已放款";
//        String PAYOFF = "已还清";
//        String OVERDUE = "已逾期";
//        String BORROW_FAIL = "借款失败";
//        status(DetailStatus.FOR_LEND);
//        status(DetailStatus.CHECK_PENDING);
//        status(DetailStatus.LOAN);
//        status(DetailStatus.PAYOFF);
//        status(DetailStatus.OVERDUE);
        // status(DetailStatus.REPAYMENT);
//        status(DetailStatus.BORROW_FAIL);

        initEvent();
    }

    private void initEvent() {

        btn_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AmortizationActivity.class);
                intent.putExtra("id", mBorrowId);
                intent.putExtra("type", "single");
                startActivity(intent);
                finish();
            }
        });

        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPayDialgo();
            }
        });
    }

    @Override
    protected void initData() {

        mBorrowId = getIntent().getStringExtra("id");
        ACache.get(mContext).put("OperateStatusId",""+mBorrowId);
//        mBorrowId="1850";
        loadData();

    }

    private void loadData() {
        RxRetrofit.getInstance().getApiService().loanDetail(mBorrowId)
                .compose(this.<LoanDetailRsBean>bindToLifecycle())
                .compose(RxSchedulers.<LoanDetailRsBean>io_main())
                .subscribe(new NetObserver<LoanDetailRsBean>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, LoanDetailRsBean result) {
                        if ("success".equals(result.getCode())) {
                            refreshUI(result.getData());
                        } else {
                            Toast.makeText(mContext, "" + result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    private void status(String status) {
        switch (status) {
            case DetailStatus.FOR_LEND:
                iv_status.setVisibility(View.VISIBLE);
                iv_status.setImageResource(R.mipmap.ic_borrow_check);
                tv_title_status.setVisibility(View.VISIBLE);
                tv_title_status.setText(DetailStatus.FOR_LEND);
                tv_status.setText(DetailStatus.FOR_LEND);
                tv_status.setTextColor(ContextCompat.getColor(this, R.color.tvo));

                tv_pay_num_title.setVisibility(View.GONE);
                tv_pay_num.setVisibility(View.GONE);
                tv_pay_money_title.setVisibility(View.GONE);
                tv_pay_money.setVisibility(View.GONE);
                tv_need_money_title.setVisibility(View.GONE);
                tv_need_money.setVisibility(View.GONE);
                tv_overdue_num_title.setVisibility(View.GONE);
                tv_overdue_num_tip.setVisibility(View.GONE);
                tv_overdue_num.setVisibility(View.GONE);
                tv_overdue_money_title.setVisibility(View.GONE);
                tv_overdue_money.setVisibility(View.GONE);
                tv_pay_type_title.setVisibility(View.GONE);
                tv_pay_type.setVisibility(View.GONE);
                tv_last_time_title.setVisibility(View.GONE);
                tv_last_time.setVisibility(View.GONE);
                break;
            case DetailStatus.CHECK_PENDING:
                iv_status.setVisibility(View.VISIBLE);
                iv_status.setImageResource(R.mipmap.ic_borrow_check);
                tv_title_status.setVisibility(View.VISIBLE);
                tv_title_status.setText(DetailStatus.CHECK_PENDING);
                tv_status.setText(DetailStatus.CHECK_PENDING);
                tv_status.setTextColor(ContextCompat.getColor(this, R.color.tvo));

                tv_pay_num_title.setVisibility(View.GONE);
                tv_pay_num.setVisibility(View.GONE);
                tv_pay_money_title.setVisibility(View.GONE);
                tv_pay_money.setVisibility(View.GONE);
                tv_need_money_title.setVisibility(View.GONE);
                tv_need_money.setVisibility(View.GONE);
                tv_overdue_num_title.setVisibility(View.GONE);
                tv_overdue_num_tip.setVisibility(View.GONE);
                tv_overdue_num.setVisibility(View.GONE);
                tv_overdue_money_title.setVisibility(View.GONE);
                tv_overdue_money.setVisibility(View.GONE);
                tv_pay_type_title.setVisibility(View.GONE);
                tv_pay_type.setVisibility(View.GONE);
                tv_last_time_title.setVisibility(View.GONE);
                tv_last_time.setVisibility(View.GONE);
                break;
            case DetailStatus.LOAN:
                tv_title.setVisibility(View.VISIBLE);
                tv_title_money.setVisibility(View.VISIBLE);
//                tv_title_money.setText("5000");
                tv_status.setText(DetailStatus.LOAN);
                tv_status.setTextColor(ContextCompat.getColor(this, R.color.base_color));
                btn_do.setVisibility(View.VISIBLE);
                btn_commit.setVisibility(View.VISIBLE);
//                btn_do.setText("提前还款");

                tv_pay_type_title.setVisibility(View.GONE);
                tv_pay_type.setVisibility(View.GONE);
                tv_last_time_title.setVisibility(View.GONE);
                tv_last_time.setVisibility(View.GONE);
                break;
            case DetailStatus.PAYOFF:
                iv_status.setVisibility(View.VISIBLE);
                iv_status.setImageResource(R.mipmap.ic_pay_off);
                tv_title_status.setVisibility(View.VISIBLE);
                tv_title_status.setText("本期借款已还清");
                tv_status.setText(DetailStatus.PAYOFF);
                tv_status.setTextColor(ContextCompat.getColor(this, R.color.tvo));

                tv_pay_num_title.setVisibility(View.GONE);
                tv_pay_num.setVisibility(View.GONE);
                tv_pay_money_title.setVisibility(View.GONE);
                tv_pay_money.setVisibility(View.GONE);
                tv_need_money_title.setVisibility(View.GONE);
                tv_need_money.setVisibility(View.GONE);
//                tv_overdue_num_title.setVisibility(View.GONE);
                tv_overdue_num_tip.setVisibility(View.GONE);
//                tv_overdue_num.setVisibility(View.GONE);
//                tv_overdue_money_title.setVisibility(View.GONE);
//                tv_overdue_money.setVisibility(View.GONE);
//                tv_pay_type_title.setVisibility(View.GONE);
//                tv_pay_type.setVisibility(View.GONE);
//                tv_last_time_title.setVisibility(View.GONE);
//                tv_last_time.setVisibility(View.GONE);
                break;
            case DetailStatus.OVERDUE:
                tv_title.setVisibility(View.VISIBLE);
                tv_title_money.setVisibility(View.VISIBLE);
//                tv_title_money.setText("5000");
                tv_status.setText(DetailStatus.OVERDUE);
                tv_status.setTextColor(ContextCompat.getColor(this, R.color.tvred));
                btn_do.setVisibility(View.VISIBLE);
                btn_commit.setVisibility(View.VISIBLE);
                btn_do.setText("立即还款");

                tv_pay_type_title.setVisibility(View.GONE);
                tv_pay_type.setVisibility(View.GONE);
                tv_last_time_title.setVisibility(View.GONE);
                tv_last_time.setVisibility(View.GONE);
                break;
            case DetailStatus.REPAYMENT:
                tv_title.setVisibility(View.VISIBLE);
                tv_title_money.setVisibility(View.VISIBLE);
//                tv_title_money.setText("5000");
                tv_status.setText(DetailStatus.REPAYMENT);
                tv_status.setTextColor(ContextCompat.getColor(this, R.color.base_color));
                btn_do.setVisibility(View.VISIBLE);
                btn_commit.setVisibility(View.VISIBLE);
//                btn_do.setText("提前还款");

                tv_pay_type_title.setVisibility(View.GONE);
                tv_pay_type.setVisibility(View.GONE);
                tv_last_time_title.setVisibility(View.GONE);
                tv_last_time.setVisibility(View.GONE);
                break;
            case DetailStatus.BORROW_FAIL:
                iv_status.setVisibility(View.VISIBLE);
                iv_status.setImageResource(R.mipmap.ic_borrow_fail);
                tv_title_status.setVisibility(View.VISIBLE);
                tv_title_status.setText(DetailStatus.BORROW_FAIL);
                tv_status.setText(DetailStatus.BORROW_FAIL);
                tv_status.setTextColor(ContextCompat.getColor(this, R.color.tvred));
                tv_remark_title.setVisibility(View.VISIBLE);
                tv_remark.setVisibility(View.VISIBLE);

                tv_pay_num_title.setVisibility(View.GONE);
                tv_pay_num.setVisibility(View.GONE);
                tv_pay_money_title.setVisibility(View.GONE);
                tv_pay_money.setVisibility(View.GONE);
                tv_need_money_title.setVisibility(View.GONE);
                tv_need_money.setVisibility(View.GONE);
                tv_overdue_num_title.setVisibility(View.GONE);
                tv_overdue_num_tip.setVisibility(View.GONE);
                tv_overdue_num.setVisibility(View.GONE);
                tv_overdue_money_title.setVisibility(View.GONE);
                tv_overdue_money.setVisibility(View.GONE);
                tv_pay_type_title.setVisibility(View.GONE);
                tv_pay_type.setVisibility(View.GONE);
                tv_last_time_title.setVisibility(View.GONE);
                tv_last_time.setVisibility(View.GONE);
                break;

        }
    }

    public void refreshUI(LoanDetailRsBean.DataBean data) {

        Log.d(TAG, "LoanDetailRsBean.DataBean data::" + new Gson().toJson(data));
        //借款总状态： 1-借款待审核   2-待放款    3-已放款    4-借款失败    5-还款中   6-已逾期
        checkTotalStatus(data.getTotalStatus());

        tv_no.setText(data.getBorrowNo());//借款单号
        tv_money.setText(data.getBorrowLimit()+"元");//借款金额
        tv_cycle_time.setText(data.getBorrowTerm()+"天");//借款周期
        tv_start_end.setText(data.getBorrowDateStr() + "至" + data.getRepaymentDateStr());//借款起止时间
        tv_split_num.setText("分" + data.getRepaymentNum() + "期");//还款期数
        tv_pay_method.setText("每期等额");//还款方式
        tv_end_time.setText(data.getRepaymentTime());//还款日
        tv_tip.setText(data.getLoanServiceCharge()+"元");//手续费
        tv_bank.setText(data.getBankName() + "(" + data.getBankCardNoEndNumber() + ")");//收款银行卡
        tv_status.setText(data.getTotalStatusName());//借款状态
        tv_start_time.setText(data.getBorrowDate());//借款时间


        tv_pay_num.setText(data.getRepayNum());//已还期数
        tv_pay_money.setText(data.getPaidTotalAmount()+"元");//已还借款
        tv_need_money.setText(data.getSurplusTotalAmount()+"元");//剩余借款
        tv_overdue_num.setText(data.getOverdueDays()+"天");//已逾天数
        tv_overdue_money.setText(data.getOverdueFine()+"元");//逾期罚金

        tv_title_money.setText("￥ " + data.getNeedToPay());

        mMoney = data.getBorrowLimit();
        listId.add(data.getBorrowId());

    }

    //@todo 通过totalStatus  匹配对应的status显示
    private void checkTotalStatus(String totalStatus) {
        switch (totalStatus) {
            case Consta.totalStatusValue.Loan_Audited:
                //1-借款待审核
                status(DetailStatus.CHECK_PENDING);

                break;
            case Consta.totalStatusValue.Loan_Pending:
                //2-待放款
                status(DetailStatus.FOR_LEND);

                break;
            case Consta.totalStatusValue.Loan_Secured:
                //3-已放款
                status(DetailStatus.LOAN);

                break;
            case Consta.totalStatusValue.Loan_Failure:
                //4-借款失败
                status(DetailStatus.BORROW_FAIL);

                break;
            case Consta.totalStatusValue.Repayment:
                //5-还款中
                status(DetailStatus.REPAYMENT);

                break;
            case Consta.totalStatusValue.Overdue:
                // 6-已逾期
                status(DetailStatus.OVERDUE);

                break;

            case Consta.totalStatusValue.PaidOff:
                // 7-已还清
                status(DetailStatus.PAYOFF);

                break;
        }
    }

    private List<String> listId = new ArrayList<>();
    private String payType;
    private String mMoney;

    private void showPayDialgo() {
        PayDialog.newInstance()
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final AbsDialog dialog) {
                        ((TextView) holder.getView(R.id.tv_money)).setText("" + mMoney);
                        ((TextView) holder.getView(R.id.tv_info)).setText("提前还款");
                        holder.getView(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        holder.getView(R.id.tv_pay_credit).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view);
                                payType = "depositPay";
                            }
                        });
                        holder.getView(R.id.tv_pay_save).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view);
                                payType = "creditPay";
                            }
                        });
                        holder.getView(R.id.tv_pay_wechat).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view);
                                payType = "wxPay";
                            }
                        });
                        holder.getView(R.id.tv_pay_ali).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialogItem(holder, view);
                                payType = "aliPay";
                            }
                        });
                        holder.getView(R.id.btn_do).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (TextUtils.isEmpty(payType)) {
                                    Toast.makeText(mContext, "请选择支付方式", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                dialog.dismiss();
                                createOrder(payType);
                            }
                        });

                    }
                })
                .setDimAmount(0.3f)
                .setAnimStyle(R.style.DialogButtonStyle)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    private void dialogItem(ViewHolder holder, View view) {
        clearAllViewStatus(holder);
        viewStatus(view);
    }

    private void clearAllViewStatus(ViewHolder holder) {
        clearViewStatus(holder.getView(R.id.tv_pay_credit));
        clearViewStatus(holder.getView(R.id.tv_pay_save));
        clearViewStatus(holder.getView(R.id.tv_pay_wechat));
        clearViewStatus(holder.getView(R.id.tv_pay_ali));
    }

    private void clearViewStatus(View view) {
        ((TextView) view).setTextColor(ContextCompat.getColor(mContext, R.color.tv3));
        view.setSelected(false);
    }

    private void viewStatus(View view) {
        ((TextView) view).setTextColor(ContextCompat.getColor(mContext, R.color.tvo));
        view.setSelected(true);
    }

    private boolean netdialog;

    private void createOrder(final String type) {
        Order order = new Order();
        order.setMemberId(ACache.get(mContext).getAsString(UserInfo.USER_ID));
        order.setOrderType("repayment");
        order.setPayType(type);
        order.setBorrowIds(listId);
        switch (type) {
            case "depositPay":
                netdialog = false;
                dialogPwd(order);
                break;
            case "creditPay":
                netdialog = false;
                dialogPwd(order);
                break;
            case "wxPay":
                netdialog = true;
                addOrder(order);
                break;
            case "aliPay":
                netdialog = true;
                addOrder(order);
                break;
        }

    }

    LoadingStatusView statusView;
    AbsDialog statusDialog;
    TextView statusTip;

    private void dialogPwd(final Order order) {
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
                                if ("depositPay".equals(payType)) {
                                    order.setPaymentPassword(MD5.getMessageDigest(password.getBytes()));
                                    dialog3Pwd(order);
                                    dialog.dismiss();
                                }
                                if ("creditPay".equals(payType)) {
                                    order.setPaymentPassword(MD5.getMessageDigest(password.getBytes()));
                                    holder.getView(R.id.ll_content).setVisibility(View.INVISIBLE);
                                    holder.getView(R.id.ll_load).setVisibility(View.VISIBLE);
                                    statusView = (LoadingStatusView) holder.getView(R.id.statusView);
                                    statusTip = holder.getView(R.id.statusTip);
                                    statusView.loadLoading();
                                    addOrder(order);
                                }

                            }
                        });
                    }
                })
                .setDimAmount(0.3f)
                .setAnimStyle(R.style.DialogButtonStyle)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    LoadingStatusView statusView3;
    AbsDialog statusDialog3;
    TextView statusTip3;

    private void dialog3Pwd(final Order order) {
        PassWord3Dialog.newInstance()
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final AbsDialog dialog) {
                        statusDialog3 = dialog;
                        holder.getView(R.id.iv_cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                        ((Password3View) holder.getView(R.id.pwd_view)).setOnFinishInput(new OnPasswordInputFinish() {
                            @Override
                            public void inputFinish(String password) {
                                order.setCvn2Code(MD5.getMessageDigest(password.getBytes()));
                                holder.getView(R.id.ll_content).setVisibility(View.INVISIBLE);
                                holder.getView(R.id.ll_load).setVisibility(View.VISIBLE);
                                statusView3 = (LoadingStatusView) holder.getView(R.id.statusView);
                                statusTip3 = holder.getView(R.id.statusTip);
                                statusView3.loadLoading();
                                addOrder(order);
                            }
                        });
                    }
                })
                .setDimAmount(0.3f)
                .setAnimStyle(R.style.DialogButtonStyle)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    private void addOrder(Order order) {
        RxRetrofit.getInstance().getApiService().addOrder(order)
                .compose(this.<OrderResult>bindToLifecycle())
                .compose(RxSchedulers.<OrderResult>io_main())
                .subscribe(new NetObserver<OrderResult>(mContext, TAG, 0, netdialog) {
                    @Override
                    public void onSuccess(int whichRequest, OrderResult orderResult) {
                        if ("success".equals(orderResult.getCode())) {
                            setOrderInfo(orderResult);
                            if ("depositPay".equals(payType)) {
                                statusView3.loadSuccess();
                                statusTip3.setText("支付成功");
                                statusTip3.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        statusDialog3.dismiss();
                                        //starttonext
                                        toSuccess();
                                    }
                                }, 2000);
                            }
                            if ("creditPay".equals(payType)) {
                                statusView.loadSuccess();
                                statusTip.setText("支付成功");
                                statusTip.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        statusDialog.dismiss();
                                        //starttonext
                                        toSuccess();
                                    }
                                }, 2000);
                            }
                            if ("aliPay".equals(payType)) {
                                aliPay(orderResult.getData().getAliPaySign());
                            }
                            if ("wxPay".equals(payType)) {
                                wechatPay(orderResult.getData().getWeixinRs());
                            }
//                            pageNumber = 1;
//                            loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID), totalStatus, pageSize, pageNumber);
                        } else {
                            if ("depositPay".equals(payType)) {
                                statusView3.loadFailure();
                                statusTip3.setText(orderResult.getMessage());
                                statusTip3.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        statusDialog.dismiss();
                                        //starttonext
                                    }
                                }, 2000);
                            }
                            if ("creditPay".equals(payType)) {
                                statusView.loadSuccess();
                                statusTip.setText(orderResult.getMessage());
                                statusTip.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        statusDialog.dismiss();
                                        //starttonext
                                    }
                                }, 2000);
                            }
                            Toast.makeText(mContext, "" + orderResult.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {
                        if ("depositPay".equals(payType)) {
                            statusView3.loadFailure();
                            statusTip3.setText("支付失败");
                            statusTip3.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    statusDialog3.dismiss();
                                }
                            }, 2000);
                        }
                        if ("creditPay".equals(payType)) {
                            statusView.loadFailure();
                            statusTip.setText("支付失败");
                            statusTip.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    statusDialog.dismiss();
                                }
                            }, 2000);
                        }
                    }
                });
    }

    private void setOrderInfo(OrderResult orderResult) {
        ACache.get(mContext).put("AddOrderMoney", ""+orderResult.getData().getTotalAmount());
        ACache.get(mContext).put("AddOrderPayNo", ""+orderResult.getData().getPayNo());
        ACache.get(mContext).put("AddOrderTime", ""+orderResult.getData().getCreateDate());
    }

    private void toSuccess(){
        ACache.get(mContext).put("OperateStatusFrom", "repayment");
        Intent intent = new Intent(mContext, OperateStatusActivity.class);
        startActivity(intent);
        finish();
    }

    private IWXAPI wxapi;
    private boolean isWxSupport = false;

    private void wechatPay(String info) {
        ACache.get(mContext).put("WXTYPE", "REPAYMENT");
//        info = "{\"sign\":\"5488B10FA72E809BE036DA976AF43F15\",\"timeStamp\":\"1507883258\",\"packageStr\":\"Sign=WXPay\",\"partnerId\":\"1486234152\",\"appid\":\"wxbfa4282e5eecb703\",\"nonceStr\":\"29ff1j18j4mijk98xoug4peqxpfwjmnm\",\"prepayId\":\"wx20171013162800a8c171666b0643468807\"}";
        WeChatBean weChatBean = new Gson().fromJson(info, WeChatBean.class);
        String appId = weChatBean.getAppid();
        ACache.get(mContext).put("WXAPPID", appId);
        wxapi = WXAPIFactory.createWXAPI(this, appId);
        wxapi.registerApp(appId);

        PayReq req = new PayReq();
//        req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
        req.appId = appId;
        req.partnerId = weChatBean.getPartnerId();
        req.prepayId = weChatBean.getPrepayId();
        req.nonceStr = weChatBean.getNonceStr();
        req.timeStamp = weChatBean.getTimeStamp();
        req.packageValue = weChatBean.getPackageStr();
        req.sign = weChatBean.getSign();
        req.extData = "app data"; // optional
        wxapi.sendReq(req);
    }

    private boolean isWXAppInstalledAndSupported() {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);
        msgApi.registerApp("wxbfa4282e5eecb703");
        boolean sIsWxAppInstalledAndSupported = msgApi.isWXAppInstalled();
        return sIsWxAppInstalledAndSupported;
    }


    private void aliPay(String info) {
        final String orderInfo = info;
//        final String orderInfo = "alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017071407749559&biz_content=%7B%22body%22%3A%22%E5%B0%8F%E8%9C%9C%E7%99%BD%E5%8D%A1%E4%BC%9A%E5%91%98%E5%B9%B4%E8%B4%B9%E5%A5%97%E9%A4%90%E8%B4%B9%22%2C%22out_trade_no%22%3A%22deposit201710131624251523%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%B0%8F%E8%9C%9C%E7%99%BD%E5%8D%A1%E4%BC%9A%E5%91%98%E5%B9%B4%E8%B4%B9%E5%A5%97%E9%A4%90%E8%B4%B9%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F14.21.42.99%3A15555%2Floan%2Fwx%2Findex%21notifyByAli.action&sign=cUQZYEkSWsV%2B7HImNLaX2OvHzwyjFE2UlULRE8HwsOvW7CyKFk%2BWH0Kf%2B66LNleWsdAqHt0voRUoU3p%2FiJPuy4GOh4qUwTuQiOWJjE%2BWZS6JV15GPQsscomcfrkwUgSeAMqzvW%2BukVKK6WnNWq1GKdzUDs1w2afWgWeDHwt6vUGh9FptSuGY9aU%2BfRO04f52dEE6LGEeNetz5Kk9KiIIPmDoeHd3lQzH8wMXqZtzu%2B9g2vNNjglhlBGNxAcJMGgyHYOLM5V2Ug5uGzp8JOS1nhjn3%2BQTCPZLs71P2GA33OpuLm%2FX8wtRGnbf%2FfJxaUOCzkjfoCDt0y7PdeMSFFCWOA%3D%3D&sign_type=RSA2&timestamp=2017-10-13+16%3A24%3A25&version=1.0";
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(BorrowDetailActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(BorrowDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(mContext, OperateStatusActivity.class));
//                        finish();
                        toSuccess();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(BorrowDetailActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(BorrowDetailActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(BorrowDetailActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
}
