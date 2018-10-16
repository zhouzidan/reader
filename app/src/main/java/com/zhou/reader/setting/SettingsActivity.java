package com.zhou.reader.setting;

import android.os.Build;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.elvishew.xlog.XLog;
import com.zhou.reader.R;
import com.zhou.reader.base.BaseActivity;
import com.zhou.reader.read.ReadSettingManager;

import butterknife.BindView;

public class SettingsActivity extends BaseActivity {

    @BindView(R.id.radio_group_night_mode)
    RadioGroup radioGroup;

    @BindView(R.id.size_seek_bar)
    AppCompatSeekBar textSizeSeekBar;

    @BindView(R.id.textSizeTitle)
    TextView textSizeTitleTextView;

    public void onNightModeChange(){
        XLog.d(" checked:");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initData() {
        int textSize = ReadSettingManager.getInstance().getTextSize();
        textSizeTitleTextView.setText("文字大小：" + textSize);
        boolean nightMode = ReadSettingManager.getInstance().isNightMode();
        radioGroup.check(nightMode ? R.id.radio_day_mode : R.id.radio_night_mode);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            XLog.d(checkedId);
            if (checkedId == R.id.radio_day_mode){
                XLog.d("白天");
                ReadSettingManager.getInstance().setNightMode(false);
            }else if (checkedId == R.id.radio_night_mode){
                XLog.d("夜间模式");
                ReadSettingManager.getInstance().setNightMode(true);
            }
        });
        textSizeSeekBar.setMax(40);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textSizeSeekBar.setMin(10);
        }
        int saveTextSize = ReadSettingManager.getInstance().getTextSize();
        textSizeSeekBar.setProgress(saveTextSize);
        textSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                XLog.d("progress:"+progress);
                int textSize = progress ;
                ReadSettingManager.getInstance().setTextSize(textSize);
                textSizeTitleTextView.setText("文字大小：" + textSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
