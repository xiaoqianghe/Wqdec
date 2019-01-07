package com.yltx.modulewd;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperButton;
import com.yltx.modulebase.base.BaseFragment;
import com.yltx.modulebase.net.NetObserver;
import com.yltx.modulebase.net.RxSchedulers;
import com.yltx.modulebase.util.ACache;
import com.yltx.modulebase.widget.AbsDialog;
import com.yltx.modulebase.widget.ViewHolder;
import com.yltx.modulewd.authentication.BindCreditCardNActivity;
import com.yltx.modulewd.authentication.OpenMemberActivity;
import com.yltx.modulewd.authentication.SesameCertificationNActivity;
import com.yltx.modulewd.authentication.SesameCreditNActivity;
import com.yltx.modulewd.authentication.TransationPasswordActivity;
import com.yltx.modulewd.borrow.borrowoperate.OperateActivity;
import com.yltx.modulewd.constant.UserCaStatus;
import com.yltx.modulewd.constant.UserInfo;
import com.yltx.modulewd.entity.MemberCreditInfo;
import com.yltx.modulewd.net.RxRetrofit;
import com.yltx.modulewd.widght.IKnowDialog;


public class HomeFragment extends BaseFragment {
    private TextView tv_add;
    private SuperButton btn_add;
    private TextView tv_open;
    private SuperButton btn_open;
    private ConstraintLayout cl_top;
    private TextView tv_limit;
    private TextView tv_subLimit;
    private Guideline guide;
    private SuperButton btn_do;
    private CheckBox checkbox;
    private TextView tv_protocol;
    private TextView tv_doc;
    private TextView tv_line;
    private TextView issus;
    private Guideline guideline;
    private TextView tv_effcient;
    private TextView tv_safety;
    private TextView tv_free;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private int step = 0;


    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void fetchData() {
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        tv_add = rootView.findViewById(R.id.tv_add);
        btn_add = rootView.findViewById(R.id.btn_add);
        tv_open = rootView.findViewById(R.id.tv_open);
        btn_open = rootView.findViewById(R.id.btn_open);
        cl_top = rootView.findViewById(R.id.cl_top);
        tv_limit = rootView.findViewById(R.id.tv_limit);
        tv_subLimit = rootView.findViewById(R.id.tv_subLimit);
        guide = rootView.findViewById(R.id.guide);
        btn_do = rootView.findViewById(R.id.btn_do);
        checkbox = rootView.findViewById(R.id.checkbox);
        tv_protocol = rootView.findViewById(R.id.tv_protocol);
        tv_doc = rootView.findViewById(R.id.tv_doc);
        tv_line = rootView.findViewById(R.id.tv_line);
        issus = rootView.findViewById(R.id.issus);
        guideline = rootView.findViewById(R.id.guideline);
        tv_effcient = rootView.findViewById(R.id.tv_effcient);
        tv_safety = rootView.findViewById(R.id.tv_safety);
        tv_free = rootView.findViewById(R.id.tv_free);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    btn_do.setEnabled(true);
                } else {
                    btn_do.setEnabled(false);
                }
            }
        });
        btn_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(mContext, ListActivity.class));
                switch (step) {
                    case 0:
                        startActivity(new Intent(mContext, SesameCertificationNActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(mContext, SesameCreditNActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(mContext, BindCreditCardNActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(mContext, TransationPasswordActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(mContext, OpenMemberActivity.class));
                        break;
                    case 5:
                        if ("1".equals(isHaveReviewingBorrow)) {
                            IKnowDialog.newInstance("借款待审核")
                                    .setConfirmOkListener(new IKnowDialog.ConfirmOkListener() {
                                        @Override
                                        public void convertView(ViewHolder holder, AbsDialog dialog) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .setDimAmount(0.3f)
                                    .setMargin(30)
                                    .setAnimStyle(R.style.DialogAnimation)
                                    .show(getActivity().getSupportFragmentManager());
                        } else {
                            startActivity(new Intent(mContext, OperateActivity.class));
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, BindCreditCardNActivity.class));
            }
        });

        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, OpenMemberActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
//        loadData(ACache.get(mContext).getAsString(UserInfo.USER_ID_CARD));
    }

    @Override
    public void onResume() {
        super.onResume();
            loadData(mParam1, null);

//        loadData("440222199109012112", "4");
    }

    private void status(String status) {
        switch (status) {
            case UserCaStatus.USER_CA:
                cl_top.setVisibility(View.GONE);
                checkbox.setVisibility(View.GONE);
                tv_protocol.setVisibility(View.GONE);
                break;
            case UserCaStatus.USER_ALL:
                cl_top.setVisibility(View.GONE);
                checkbox.setVisibility(View.VISIBLE);
                tv_protocol.setVisibility(View.VISIBLE);
                break;
            case UserCaStatus.USER_BANK:
                checkbox.setVisibility(View.GONE);
                tv_protocol.setVisibility(View.GONE);
                cl_top.setVisibility(View.VISIBLE);
                tv_add.setVisibility(View.VISIBLE);
                btn_add.setVisibility(View.VISIBLE);
                tv_open.setVisibility(View.GONE);
                btn_open.setVisibility(View.GONE);
                break;
            case UserCaStatus.USER_VIP:
                checkbox.setVisibility(View.GONE);
                tv_protocol.setVisibility(View.GONE);
                cl_top.setVisibility(View.VISIBLE);
                tv_add.setVisibility(View.GONE);
                btn_add.setVisibility(View.GONE);
                tv_open.setVisibility(View.VISIBLE);
                btn_open.setVisibility(View.VISIBLE);
                break;
        }

    }

    private void loadData(String merchantNo, String memberid) {
        RxRetrofit.getInstance().getApiService().getMemberCreditInfo(merchantNo, memberid)
                .compose(this.<MemberCreditInfo>bindToLifecycle())
                .compose(RxSchedulers.<MemberCreditInfo>io_main())
                .subscribe(new NetObserver<MemberCreditInfo>(mContext, TAG, 0, true) {
                    @Override
                    public void onSuccess(int whichRequest, MemberCreditInfo memberCreditInfo) {
                        if ("success".equals(memberCreditInfo.getCode())) {
                            refreshUI(memberCreditInfo.getData());
                        } else {
                            Toast.makeText(mContext, "" + memberCreditInfo.getMessage(), Toast.LENGTH_SHORT).show();
                            if("找不到会员信息".equals(memberCreditInfo.getMessage())){
                                getActivity().finish();
                            }
                        }
                    }

                    @Override
                    public void onError(int whichRequest, Throwable e) {

                    }
                });
    }

    private String isHaveReviewingBorrow;

    private void refreshUI(MemberCreditInfo.DataBean entity) {
        isHaveReviewingBorrow = entity.getIsHaveReviewingBorrow();
        tv_limit.setText("" + entity.getLimitUnused());
        tv_subLimit.setText("总额度：￥" + entity.getCreditLimit() + "；30天借钱无息；");
        saveUserInfo(entity);
        step(entity);
        switch (step) {
            case 0:
            case 1:
                status(UserCaStatus.USER_ALL);
                break;
            case 2:
                status(UserCaStatus.USER_BANK);
                break;
            case 3:
                break;
            case 4:
                status(UserCaStatus.USER_VIP);
                break;
            case 5:
                status(UserCaStatus.USER_CA);
                break;
            default:
                break;
        }
    }

    private void saveUserInfo(MemberCreditInfo.DataBean entity) {
//        ACache.get(mContext).put(UserInfo.USER_ID_CARD, entity.getIdentityNumber());
//        ACache.get(mContext).put(UserInfo.USER_NAME, entity.getRealName());
        ACache.get(mContext).put(UserInfo.USER_ID, entity.getMemberId());
        ACache.get(mContext).put(UserInfo.USER_ID_CARD, entity.getIdentityNumber());
        ACache.get(mContext).put(UserInfo.USER_NAME, entity.getRealName());
        ACache.get(mContext).put(UserInfo.USER_PHONE, entity.getPhone());
        ACache.get(mContext).put(UserInfo.USER_BANK_CARD, entity.getCreditBankCardNo());


        ACache.get(mContext).put(UserInfo.ANNUAL_FEE_PAY_TIME, entity.getAnnualFeePayTime());//会员年费缴费时间
        ACache.get(mContext).put(UserInfo.ANNUAL_FEE_VALIDATE, entity.getAnnualFeeValidate());//会员年费到期时间
        ACache.get(mContext).put(UserInfo.LEVEL_NAME, entity.getLevelName());//等级
        ACache.get(mContext).put(UserInfo.CREDIT_SCORE, entity.getCreditScore());//信用分

        ACache.get(mContext).put(UserInfo.IS_PAY_FEE, entity.getIsPayFee());//


        //  //zhimaStatus   芝麻认证zhimaCredit  芝麻信用 isHaveBank信用卡 isPayFee   开通会员
        ACache.get(mContext).put(UserInfo.USER_ZHIMA_STATUS, entity.getZhimaStatus());
        ACache.get(mContext).put(UserInfo.USER_ZHIMA_CREDIT, entity.getZhimaCredit());
        ACache.get(mContext).put(UserInfo.USER_ISHAVE_BANK, entity.getIsHaveBank());


    }


    private void step(MemberCreditInfo.DataBean entity) {
        if (overStep(entity.getZhimaStatus())) {
            step = 1;
        }
        if (step == 1 && overStep(entity.getZhimaCredit())) {
            step = 2;
        }
        if (step == 2 && overStep(entity.getIsHaveBank())) {
            step = 3;
        }
        if (step == 3 && overStep(entity.getIsSetPwd())) {
            step = 4;
        }
        if (step == 4 && overStep(entity.getIsPayFee())) {
            step = 5;
        }
        Log.e(TAG, "step -> " + step);
    }

    private boolean overStep(String status) {
        if ("1".equals(status))
            return true;
        else
            return false;
    }


}
