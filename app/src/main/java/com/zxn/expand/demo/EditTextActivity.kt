package com.zxn.expand.demo

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zxn.expand.demo.EditTextActivity.Companion.EDITTEXTACTIVITY
import kotlinx.android.synthetic.main.activity_edit_text.*

@Route(path = EDITTEXTACTIVITY)
class EditTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)
        ARouter.getInstance().inject(this)

        search_friend_edit.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Toast.makeText(this@EditTextActivity, "搜索按钮!", Toast.LENGTH_SHORT).show()
            }
            false
        }
    }

    companion object {
        const val EDITTEXTACTIVITY = "/AppExpandDemo/EditTextActivity"

        @JvmStatic
        fun jumpTo() {
            ARouter.getInstance().build(EDITTEXTACTIVITY).navigation()
        }
    }

}