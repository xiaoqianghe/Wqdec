package com.yltx.modulewd.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.yltx.modulebase.base.BaseActivity;
import com.yltx.modulewd.MainActivity;
import com.yltx.modulewd.R;
import com.yltx.modulewd.user.other.AboutMeActivity;
import com.yltx.modulewd.user.other.IssueQuestionActivity;
import com.yltx.modulewd.user.other.LoanInstructionsActivity;
import com.yltx.modulewd.user.other.RepaymentInstructionsActivity;

/**
 * Author：Wq
 * Date：2017/10/9 11:26
 * Description：//todo
 */

public class HelperCenterActivity extends BaseActivity{

    public TextView toolbar_title;
    public TextView toolbar_subtitle;

    private SuperTextView stLoadAgreement;
    private SuperTextView stLoadContract;
    private SuperTextView stComissionAggrement;
    private SuperTextView stPersonalInformation;

    private SuperTextView stScerAgreement;
    private SuperTextView stScreditContract;
    private SuperTextView stCallme;
    private SuperTextView stAboutOurs;


    private WebView mWebView;
    private ImageView mIvWebViewRefresh;
    private boolean isError = false;
    private RelativeLayout webViewErr;



    @Override
    protected int initLayout() {
        return R.layout.activity_helper_center;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "帮助中心", null);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_subtitle = (TextView) findViewById(R.id.toolbar_subtitle);
        stLoadAgreement= (SuperTextView) findViewById(R.id.st_loan_agreement);
        stLoadContract= (SuperTextView) findViewById(R.id.st_loan_contract);
        stComissionAggrement= (SuperTextView) findViewById(R.id.st_commission_agreement);
        stPersonalInformation= (SuperTextView) findViewById(R.id.st_personal_information);
        stScerAgreement= (SuperTextView) findViewById(R.id.st_sesame_certification_agreement);
        stAboutOurs= (SuperTextView) findViewById(R.id.st_aboutours);
        stCallme= (SuperTextView) findViewById(R.id.st_callme);


        mWebView= (WebView) findViewById(R.id.webView_issue);
        mIvWebViewRefresh= (ImageView) findViewById(R.id.webViewRefresh);
        webViewErr= (RelativeLayout) findViewById(R.id.webViewErr);

        //initEvent();


    }

//    private void initEvent() {
//        stPersonalInformation.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        startActivity(new Intent(HelperCenterActivity.this, UserLoanAgreementActivity.class));
//                    }
//                });
//
//
//        stLoadAgreement.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //常见问题
//                startActivity(new Intent(HelperCenterActivity.this,IssueQuestionActivity.class));
//            }
//        });
//
//        stLoadContract.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //借款说明
//                startActivity(new Intent(HelperCenterActivity.this,LoanInstructionsActivity.class));
//            }
//        });
//
//        stComissionAggrement.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //还款说明
//                startActivity(new Intent(HelperCenterActivity.this,RepaymentInstructionsActivity.class));
//            }
//        });
//
//        stAboutOurs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //还款说明
//                startActivity(new Intent(HelperCenterActivity.this, AboutMeActivity.class));
//            }
//        });
//    }

    @Override
    protected void initData() {

        mIvWebViewRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isError = false;
                mWebView.loadUrl("http://wxssj.ygs001.com/ewm/question.html");
            }
        });
//        webView.loadUrl("file:///android_asset/question.html");
        mWebView.loadUrl("http://wxssj.ygs001.com/ewm/question.html");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWebView.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                showErr(true);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                showErr(true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!isError) {
                    showErr(false);
                }
            }
        });

    }


    private void showErr(boolean flag) {
        if (flag) {
            isError = true;
            webViewErr.setVisibility(View.VISIBLE);
        } else {
            isError = false;
            webViewErr.setVisibility(View.GONE);
        }
    }
}
