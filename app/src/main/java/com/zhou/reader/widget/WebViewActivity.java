package com.zhou.reader.widget;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.zhou.reader.R;
import com.zhou.reader.base.BaseActivity;
import com.zhou.reader.util.SafeToast;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity {

    public static final String EXTRA_URL = "extra_url";

    @BindView(R.id.webview)
    WebView webView;

    @BindView(R.id.progress_horizontal)
    ProgressBar progressBar;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initData() {
        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);
        webView.getSettings().setJavaScriptEnabled(true);
        String webUrl = getIntent().getStringExtra(EXTRA_URL);
        if (TextUtils.isEmpty(webUrl)) {
            SafeToast.makeText("参数异常").show();
            finish();
        } else {
            webView.loadUrl(webUrl);
        }
    }

    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            return super.shouldOverrideUrlLoading(view, request);
            return false;
        }
    };

    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress == 0) {
                progressBar.setProgress(0);
                progressBar.setVisibility(View.VISIBLE);
            } else if (newProgress > 0 && newProgress < 100) {
                progressBar.setProgress(newProgress);
            } else if (newProgress == 100) {
                progressBar.setProgress(100);
                progressBar.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static void start(Context context, String webUrl) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(EXTRA_URL, webUrl);
        context.startActivity(intent);
    }


}
