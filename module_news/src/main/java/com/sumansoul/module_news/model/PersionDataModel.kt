package com.sumansoul.module_news.model

import com.blankj.utilcode.util.ToastUtils
import com.google.gson.Gson
import com.sumansoul.base.model.MvvmBaseModel
import com.sumansoul.module_news.api.NewsApiInterface
import com.sumansoul.module_news.api.SSNetWorkApi
import com.sumansoul.module_news.httpBean.HomePagerBean
import com.sumansoul.module_news.httpBean.PersionData
import com.sumansoul.network.observer.BaseObserver

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/9/24
 * Time: 13:39
 */
class PersionDataModel : MvvmBaseModel<PersionData,PersionData> (PersionData::class.java,"","",false){
    override fun load() {
        SSNetWorkApi.getService(NewsApiInterface::class.java).getPersionData()
            .compose(SSNetWorkApi.getInstance().applySchedluers(BaseObserver<PersionData>(this)))
    }

    override fun onSuccess(t: PersionData, isFormCache: Boolean) {
        notifyResultToListener(t,t,isFormCache)
    }

    override fun onFailure(e: Throwable) {
        loadFail(e.message.toString())
    }

}