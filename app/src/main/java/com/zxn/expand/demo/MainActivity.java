package com.zxn.expand.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zxn.widget.ExpandCheckBox;
import com.zxn.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.cb_checked_all)
    ExpandCheckBox cbCheckedAll;
    @BindView(R.id.sb_plug)
    SwitchButton sbPlug;
    @BindView(R.id.sb_plug_click)
    SwitchButton sbPlugClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        cbCheckedAll.setOnBoxCheckedChangeListener((buttonView, isChecked) -> Toast.makeText(MainActivity.this, "isChecked:" + isChecked, Toast.LENGTH_SHORT).show());

        sbPlug.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

            }

            @Override
            public void onTouchCheckedChanged(SwitchButton view, boolean isChecked) {
                Toast.makeText(MainActivity.this, "sbPlugIsChecked:" + isChecked, Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onTouchCheckedChanged: " + isChecked);
            }
        });

        sbPlugClick.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "setOnClickListener:" + sbPlugClick.isChecked(), Toast.LENGTH_SHORT).show();
        });
    }

    @OnClick({R.id.button, R.id.btn_switch, R.id.btn_switch_none})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.button) {
            cbCheckedAll.setSimpleChecked(!cbCheckedAll.isChecked());
        } else if (view.getId() == R.id.btn_switch) {
            sbPlug.setChecked(!sbPlug.isChecked());
        } else if (view.getId() == R.id.btn_switch_none) {
            sbPlugClick.setChecked(!sbPlugClick.isChecked());
        }
    }
}
