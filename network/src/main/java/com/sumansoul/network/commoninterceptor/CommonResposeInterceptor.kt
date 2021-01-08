package com.sumansoul.network.commoninterceptor

import android.util.Log
import com.sumansoul.network.NetWorkApi
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/24
 * Time: 13:03
 */
class CommonResposeInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var response = chain.proceed(chain.request())
        return response
    }
}