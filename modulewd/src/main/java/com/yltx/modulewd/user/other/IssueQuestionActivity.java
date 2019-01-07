package com.yltx.modulewd.user.other;

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
import com.yltx.modulewd.R;
import com.yltx.modulewd.user.UserLoanAgreementActivity;

/**
 * Author：Wq
 * Date：2017/10/9 11:26
 * Description：//todo
 */

public class IssueQuestionActivity extends BaseActivity{

    public TextView toolbar_title;
    public TextView toolbar_subtitle;

    private WebView mWebView;
    private ImageView mIvWebViewRefresh;

    private boolean isError = false;


    private RelativeLayout webViewErr;





    @Override
    protected int initLayout() {
        return R.layout.activity_my_issue;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        initToolBar(true, "常见问题", null);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_subtitle = (TextView) findViewById(R.id.toolbar_subtitle);
        mWebView= (WebView) findViewById(R.id.webView_issue);
        mIvWebViewRefresh= (ImageView) findViewById(R.id.webViewRefresh);
        webViewErr= (RelativeLayout) findViewById(R.id.webViewErr);
    }

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
