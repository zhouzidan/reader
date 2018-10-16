package com.zhou.reader.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.zhou.reader.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initData();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    protected abstract @LayoutRes int getLayoutId();

    protected abstract void initData();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        progressDialog = new ProgressDialog(this);//1.创建一个ProgressDialog的实例
        progressDialog.setTitle("正在加载中...");//2.设置标题
        progressDialog.setCancelable(false);//4.设置可否用back键关闭对话框
        progressDialog.show();//5.将ProgessDialog显示出来
    }

    @Override
    public void hideLoading() {
        try {
            if (progressDialog != null && progressDialog.isShowing()){
                progressDialog.hide();
                progressDialog.cancel();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
