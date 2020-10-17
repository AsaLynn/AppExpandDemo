package com.zxn.expand.demo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.zxn.widget.ExpandCheckBox
import com.zxn.widget.SwitchButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @JvmField
    @BindView(R.id.cb_checked_all)
    var cbCheckedAll: ExpandCheckBox? = null

    @JvmField
    @BindView(R.id.sb_plug)
    var sbPlug: SwitchButton? = null

    @JvmField
    @BindView(R.id.sb_plug_click)
    var sbPlugClick: SwitchButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        cbCheckedAll!!.setOnBoxCheckedChangeListener(object : ExpandCheckBox.OnBoxCheckedChangeListener {
            override fun onBoxCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                Toast.makeText(this@MainActivity, "isChecked:$isChecked", Toast.LENGTH_SHORT).show()
            }
        })

        sbPlug!!.setOnCheckedChangeListener(object : SwitchButton.OnCheckedChangeListener {
            override fun onCheckedChanged(view: SwitchButton?, isChecked: Boolean) {

            }

            override fun onTouchCheckedChanged(view: SwitchButton?, isChecked: Boolean) {
                Toast.makeText(this@MainActivity, "sbPlugIsChecked:$isChecked", Toast.LENGTH_SHORT).show()
//                Log.i(TAG, "onTouchCheckedChanged: $isChecked")
            }
        })
        sbPlugClick!!.setOnClickListener { v: View? -> Toast.makeText(this@MainActivity, "setOnClickListener:" + sbPlugClick!!.isChecked, Toast.LENGTH_SHORT).show() }

        btn_input.setOnClickListener(View.OnClickListener {
            //EditTextActivity
            EditTextActivity.jumpTo()
        })
        btn_toolbar.setOnClickListener(View.OnClickListener {
            ToolbarActivity.jumpTo()
        })

    }

    @OnClick(R.id.button, R.id.btn_switch, R.id.btn_switch_none)
    fun onViewClicked(view: View) {
        if (view.id == R.id.button) {
            cbCheckedAll!!.setSimpleChecked(!cbCheckedAll!!.isChecked)
        } else if (view.id == R.id.btn_switch) {
            sbPlug!!.isChecked = !sbPlug!!.isChecked
        } else if (view.id == R.id.btn_switch_none) {
            sbPlugClick!!.isChecked = !sbPlugClick!!.isChecked
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}