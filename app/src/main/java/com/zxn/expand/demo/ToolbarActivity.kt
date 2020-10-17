package com.zxn.expand.demo

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zxn.expand.demo.ToolbarActivity.Companion.TOOLBAR_ACTIVITY
import kotlinx.android.synthetic.main.activity_toolbar.*

@Route(path = TOOLBAR_ACTIVITY)
class ToolbarActivity : AppCompatActivity() {

    companion object {
        const val TOOLBAR_ACTIVITY = "/AppExpandDemo/ToolbarActivity"

        @JvmStatic
        fun jumpTo() {
            ARouter.getInstance().build(TOOLBAR_ACTIVITY).navigation()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)
        //toolbar.title = "标题"
        val options: ToolBarOptions = NimToolBarOptions()
        setToolBar(options)
    }

    private fun setToolBar(options: ToolBarOptions) {
        if (options.titleId !== 0) {
            toolbar.setTitle(options.titleId)
        }
        if (!TextUtils.isEmpty(options.titleString)) {
            toolbar.title = options.titleString
        }
        /*if (options.logoId !== 0) {
            toolbar.setLogo(options.logoId)
        }*/
        //setSupportActionBar(toolbar)
        if (options.isNeedNavigate) {
            toolbar.setNavigationIcon(options.navigateId)
            toolbar.contentInsetStartWithNavigation = 0
            toolbar.setNavigationOnClickListener {
                //onNavigateUpClicked() }
                Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show()
            }
        }
    }
}