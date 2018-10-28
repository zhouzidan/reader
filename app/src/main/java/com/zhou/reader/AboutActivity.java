package com.zhou.reader;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String email = "zhou_guobao@qq.com";
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.mipmap.ic_launcher)//图片
                .setDescription("本人是一个小说爱好者，每次使用浏览器阅读小说的时候，总是遇到弹框广告的问题，而且阅读进度也不方便管理，所以就做了这个App。")//介绍
                .addItem(new Element().setTitle("Version " + BuildConfig.VERSION_NAME))
                .addItem(new Element().setTitle("更新时间：" + BuildConfig.TIME))
                .addEmail(email,"电子邮箱：" + email)//邮箱
                .addWebsite("https://github.com/zhouzidan/reader")//网站
                .addPlayStore(BuildConfig.APPLICATION_ID)//应用商店
                .addGitHub("zhouzidan")//github
                .create();

        setContentView(aboutPage);
        initActionBar();
    }

    private void initActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
