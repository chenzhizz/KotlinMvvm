package com.sumansoul.module_news.api

import com.sumansoul.network.BaseRepose
import com.sumansoul.network.NetWorkApi
import com.sumansoul.network.exception.ExceptionHandle
import io.reactivex.functions.Function
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/24
 * Time: 17:37
 */
class SSNetWorkApi private constructor(): NetWorkApi() {

    companion object {
        /*kotlin---单例---双重锁*/
        val sInstant: SSNetWorkApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SSNetWorkApi()
        }
        /*----------单例结束--------------*/

        fun getInstance(): SSNetWorkApi{
            return sInstant
        }

        fun <T> getService(service: Class<T>): T {
            return getInstance().getRetrofit(service).create(service)
        }


    }


    override fun getInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                var builder = chain.request().newBuilder()
                builder.addHeader("device", NetWorkApi.device)
                builder.addHeader("token", NetWorkApi.token)
                return chain.proceed(builder.build())
            }

        }
    }

    override fun <T> getAppErrorHandler(): Function<T, T> {
        return object : Function<T, T> {
            override fun apply(t: T): T {
                if (t is BaseRepose) {
                    if(t.state != 1 ||( t.state ==1 && t.code==11)){
                        var exception = ExceptionHandle.ServerException()
                        exception.code = t.code
                        exception.message = t.msg
                        throw  exception
                    }

                }
                return t
            }

        }
    }

    /*正式*/
    override fun getFormal(): String {
     return "https://app.sumansoul.com"
    }
    /*测试*/
    override fun getTest(): String {
        return "https://testgo.sumansoul.com"
    }
}


