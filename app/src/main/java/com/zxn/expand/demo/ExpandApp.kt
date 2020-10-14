package com.zxn.expand.demo

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Copyright(c) ${}YEAR} ZhuLi co.,Ltd All Rights Reserved.
 *
 * @className: ExpandApp$
 * @description: TODO 类描述
 * @version: v0.0.1
 * @author: zxn < a href=" ">zhangxiaoning@17biyi.com</ a>
 * @date: 2020/10/14$ 18:29$
 * @updateUser: 更新者：
 * @updateDate: 2020/10/14$ 18:29$
 * @updateRemark: 更新说明：
 * @version: 1.0
 * */
class ExpandApp :Application(){
    override fun onCreate() {
        super.onCreate()
        ARouter.openDebug()
        ARouter.openLog()
        ARouter.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}