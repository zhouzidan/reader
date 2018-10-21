package com.zhou.reader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler.sendEmptyMessageDelayed(0,1000);
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            go2ShelfActivity();
        }
    };

    private void go2ShelfActivity() {
        Intent intent = new Intent(this,ShelfActivity.class);
        startActivity(intent);
        finish();
    }
}
