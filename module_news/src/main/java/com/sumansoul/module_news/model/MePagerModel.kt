package com.sumansoul.module_news.model

import android.annotation.SuppressLint
import com.sumansoul.base.model.MvvmBaseModel
import com.sumansoul.module_news.api.NewsApiInterface
import com.sumansoul.module_news.api.SSNetWorkApi
import com.sumansoul.module_news.httpBean.HomePagerBean
import com.sumansoul.module_news.httpBean.MePagerBean
import com.sumansoul.module_news.view.TitleViewSModel
import com.sumansoul.network.observer.BaseObserver

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/12/9
 * Time: 9:59
 */
class MePagerModel : MvvmBaseModel<MePagerBean, List<TitleViewSModel>>(
    MePagerBean::class.java,
    HomeModel.PREF_KRY_HOME_CHANNEL,"",false){
    override fun load() {
        var baseObserver=  BaseObserver<MePagerBean>(this)
        SSNetWorkApi.getService(NewsApiInterface::class.java).getMePage().compose(SSNetWorkApi.getInstance().applySchedluers(baseObserver))
    }

    override fun onSuccess(t: MePagerBean, isFormCache: Boolean) {
        var pager =ArrayList<TitleViewSModel> ()
       var bean=TitleViewSModel()
        bean.id=t.data.nickname
        bean.image=t.data.head
        pager.add(bean)
        notifyResultToListener(t,pager,isFormCache)
    }

    override fun onFailure(e: Throwable) {
        loadFail(e.message.toString())
    }


}