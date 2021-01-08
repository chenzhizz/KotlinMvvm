package com.sumansoul.common.news

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/9/17
 * Time: 16:11
 */
interface INewsServers : IProvider {

//    val NEWS_ROUTER: String
//        get() = "/news"
//    val NEWS_SERVICE: String
//        get() = "/news_service"
fun getHeadFragment(): Fragment


}