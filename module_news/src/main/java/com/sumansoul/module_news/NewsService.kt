package com.sumansoul.module_news

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.sumansoul.common.news.INewsServers

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/9/17
 * Time: 17:07
 */
@Route(path = "/news/test")
 class NewsService : INewsServers{
    override fun getHeadFragment(): Fragment {
       return ConnectFragment()
    }

    override fun init(context: Context?) {

    }
}