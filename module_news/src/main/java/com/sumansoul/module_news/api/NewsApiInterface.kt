package com.sumansoul.module_news.api


import com.sumansoul.module_news.httpBean.HomePagerBean
import com.sumansoul.module_news.httpBean.MePagerBean
import com.sumansoul.module_news.httpBean.PersionData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header


/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/23
 * Time: 17:32
 */
interface NewsApiInterface {

    @GET( "/app/stu/home/page/1")
    fun getNewsChagnes(
        @Header("device") device:String,
        @Header("token") token:String
       ) : Call<NeawsChannesBean>


    @GET( "/app/stu/home/page/1")
    fun getNewsChagne() : Call<NeawsChannesBean>

    @GET( "/app/stu/home/page/1")
    fun getNewsChagnes() : Observable<NeawsChannesBean>

    @GET( "/app/stu/home/page/1")
    fun getHomePager() : Observable<HomePagerBean>

    @GET( "/app/user/mydata/1")
    fun getPersionData() : Observable<PersionData>

    @GET( "/app/user/mydata/1")
    fun getMePage() : Observable<MePagerBean>

}