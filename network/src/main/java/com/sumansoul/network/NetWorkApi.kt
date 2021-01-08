package com.sumansoul.network



import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.sumansoul.network.commoninterceptor.CommonRequestInterceptor
import com.sumansoul.network.commoninterceptor.CommonResposeInterceptor
import com.sumansoul.network.environment.IEnvironment
import com.sumansoul.network.exception.HttpErrorHandler

import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/23
 * Time: 17:47
 */
abstract class NetWorkApi : IEnvironment{


    /*设置静态方法 与静态属性 */
    /*使用----NetWorkApi.Companion.TENCENT_BASE_URL      NetWorkApi.Companion.getTencentNewChannes      */
    companion object {
        lateinit var mINetWorkRequiredInfo: INetWorkRequiredInfo
        var retrofitHashMap = HashMap<String, Retrofit>()
//        val TENCENT_BASE_URL: String = "https://testgo.sumansoul.com"
//        val TAG: String = "NetWorkApi"
        var token = "cz_s"
        var device = "123456789"
        var mIsFormat=false
        fun init(mINetWorkRequiredInfo: INetWorkRequiredInfo) {
            NetWorkApi.mINetWorkRequiredInfo = mINetWorkRequiredInfo
        }
    }




    private var mBaseUrl:String
    private lateinit var mOkHttpClient:OkHttpClient
    constructor() {
        if (mIsFormat) {
            mBaseUrl=getFormal()
            return
        }
        mBaseUrl=getTest()
    }


    fun <T> getRetrofit(server: Class<T>): Retrofit {
        if (retrofitHashMap[mBaseUrl + server.name] != null) {
            return retrofitHashMap[mBaseUrl + server.name]!!
        }
        var builder: Retrofit.Builder = Retrofit.Builder()
        builder.baseUrl(mBaseUrl)
        builder.client(getOkHttpClient())
        builder.addConverterFactory(GsonConverterFactory.create())
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        var retrofit: Retrofit = builder.build()
        retrofitHashMap[mBaseUrl + server.name] = retrofit
        return retrofit
    }

//    fun <T> getService(service: Class<T>): T {
//        return getRetrofit(service).create(service)
//    }

    /*设置okhttpClient 设置拦截器*/
    private fun getOkHttpClient(): OkHttpClient {
        if(!::mOkHttpClient.isInitialized){
            var okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            getInterceptor()?.let {
                okHttpClientBuilder.addInterceptor(getInterceptor())
            }
            okHttpClientBuilder.addInterceptor(CommonRequestInterceptor(mINetWorkRequiredInfo))
            okHttpClientBuilder.addInterceptor(CommonResposeInterceptor())
            if (mINetWorkRequiredInfo != null && mINetWorkRequiredInfo.isDebug) {
                var httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
            }

            mOkHttpClient=okHttpClientBuilder.build();

        }
        return mOkHttpClient
    }



    fun <T> applySchedluers(observer: Observer<T>): ObservableTransformer<T, T>? {
        return ObservableTransformer<T, T> { upstream ->
            var observable = upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(getAppErrorHandler<T>())
                .onErrorResumeNext(HttpErrorHandler<T>())
            observable.subscribe(observer)
            observable
        }
    }

    abstract fun getInterceptor() :Interceptor
    abstract  fun <T>getAppErrorHandler():Function<T, T>
}




