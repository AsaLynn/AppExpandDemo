package com.zxn.expand.demo;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zxn.expandapp.widget.ExpandCheckBox;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.cb_checked_all)
    ExpandCheckBox cbCheckedAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        cbCheckedAll.setOnBoxCheckedChangeListener((buttonView, isChecked) -> Toast.makeText(MainActivity.this, "isChecked:" + isChecked, Toast.LENGTH_SHORT).show());
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        cbCheckedAll.setSimpleChecked(!cbCheckedAll.isChecked());
    }
}
