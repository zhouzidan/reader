package com.zhou.reader.ui;

import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhou.reader.R;
import com.zhou.reader.base.BaseFragment;

import butterknife.BindView;

public class ShopFragment extends BaseFragment {

    @BindView(R.id.webview)
    WebView webView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    protected void initData(View view) {
        webView.loadUrl("https://m.liewen.cc/");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                super.shouldOverrideUrlLoading(view, request);
                return false;
            }
        });
    }
}