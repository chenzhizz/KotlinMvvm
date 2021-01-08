package com.sumansoul.network.commoninterceptor

import com.sumansoul.network.INetWorkRequiredInfo
import com.sumansoul.network.NetWorkApi
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/24
 * Time: 12:57
 */
class CommonRequestInterceptor :Interceptor {
    lateinit var mINetWorkRequiredInfo: INetWorkRequiredInfo
    constructor(mINetWorkRequiredInfo: INetWorkRequiredInfo){
        this.mINetWorkRequiredInfo=mINetWorkRequiredInfo
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var builder=chain.request().newBuilder()
//        builder.addHeader("device",NetWorkApi.device)
//        builder.addHeader("token",NetWorkApi.token)
        return chain.proceed(builder.build())
    }
}