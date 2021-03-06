package com.zhou.reader;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhou.reader.shelf.ShelfFragment;

public class ShelfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
        initFragment();
    }

    private void initFragment() {
        ShelfFragment shelfFragment = (ShelfFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (shelfFragment == null) {
            shelfFragment = new ShelfFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, shelfFragment);
            transaction.commit();
        }
    }

}
