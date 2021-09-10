package com.sumansoul.kotlinDemo

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.kingja.loadsir.core.LoadSir
import com.sumansoul.network.NetWorkApi


/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/8/7
 * Time: 14:52
 */
class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        NetWorkApi.init(AppNetWork())
        ARouter.init(this)
        ARouter.openDebug()
        ARouter.openLog()
    }
}


