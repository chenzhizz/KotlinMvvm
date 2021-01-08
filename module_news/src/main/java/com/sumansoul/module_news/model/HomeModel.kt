package com.sumansoul.module_news.model

import android.annotation.SuppressLint
import com.sumansoul.base.model.MvvmBaseModel
import com.sumansoul.module_news.api.NewsApiInterface
import com.sumansoul.module_news.api.SSNetWorkApi
import com.sumansoul.module_news.httpBean.HomePagerBean
import com.sumansoul.module_news.view.TitleViewSModel
import com.sumansoul.network.observer.BaseObserver

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/28
 * Time: 14:34
 */
class HomeModel : MvvmBaseModel<HomePagerBean, List<TitleViewSModel>>(
    HomePagerBean::class.java,
    PREF_KRY_HOME_CHANNEL,"",false) {
    companion object{
        var PREF_KRY_HOME_CHANNEL="pref_kry_home_channel"
        var TAG="HomeModel"
    }

    override fun onSuccess(homePagerBean: HomePagerBean, isFormCache: Boolean) {
       var pager =ArrayList<TitleViewSModel> ()
        homePagerBean.data.Activities.forEach { source ->
            var home= TitleViewSModel()
            home.id=source._id
            home.image=source.img_path
            pager.add(home)
        }
        /*可以做数据的转化与数据处理*/
       notifyResultToListener(homePagerBean,pager,isFormCache)
    }

    override fun onFailure(e: Throwable) {
      loadFail(e.message.toString())
    }

    override fun load() {
        var baseObserver=  BaseObserver<HomePagerBean>(this)
        SSNetWorkApi.getService(NewsApiInterface::class.java).getHomePager().compose(SSNetWorkApi.getInstance().applySchedluers(baseObserver))
//        SSNetWorkApi.getService(NewsApiInterface::class.java)
//            .getNewsChagnes()
//            .compose(SSNetWorkApi.getInstance().applySchedluers(object :
//                BaseObserver<NeawsChannesBean>(){
//                override fun onSuccess(t: NeawsChannesBean) {
//                    Log.e(TAG,GsonUtils.toJson(t))
//
//                }
//
//                override fun onFailure(e: Throwable) {
//                    Log.e(TAG,GsonUtils.toJson(e))
//                }
//
//            }))
    }

}