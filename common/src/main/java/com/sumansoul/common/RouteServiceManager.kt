package com.sumansoul.common

import android.text.TextUtils
import com.alibaba.android.arouter.facade.template.IProvider
import com.alibaba.android.arouter.launcher.ARouter


/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/9/17
 * Time: 17:29
 */
class RouteServiceManager {

    companion object{
        fun <T : IProvider> provide(clz : Class<T>,path:String) : T?{
            if (TextUtils.isEmpty(path)) {
                return null
            }
            var iProvider:IProvider? = null
            try {
                iProvider=(ARouter.getInstance()
                    .build(path)
                    .navigation())
                        as IProvider
               // iProvider = ARouter.getInstance().navigation(clz) as IProvider
            }catch (e : Exception){
                e.printStackTrace()
            }
            return iProvider as T?
        }
    }
}