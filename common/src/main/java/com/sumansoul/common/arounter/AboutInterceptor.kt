package com.sumansoul.common.arounter

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/19
 * Time: 11:31
 */
// 比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查
// 拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行
@Interceptor(priority = 1, name = "登录拦截")
class AboutInterceptor : IInterceptor{
    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        val groups = postcard!!.group
        if (RouterConfig.group.NEED_LOGIN == groups) {
            if (true) {
                //登录判断 Log.e("你需要登录", "你需要登录");
                ARouter.getInstance().build(RouterConfig.App.ACTIVITY_LOGIN).navigation()
                callback?.onInterrupt(null)
            }
        } else {
            // 处理完成，交还控制权
            callback?.onContinue(postcard)
        }
        // 以上两种至少需要调用其中一种，否则不会继续路由
    }

    override fun init(context: Context?) {

    }
}