package com.sumansoul.module_news.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.ToastUtils
import com.sumansoul.base.model.IBaseModeListener
import com.sumansoul.base.model.MvvmBaseModel
import com.sumansoul.base.model.PagingResult
import com.sumansoul.base.viewmodel.MvvmBaseViewModel
import com.sumansoul.base.viewmodel.ViewStatus
import com.sumansoul.module_news.model.HomeModel
import com.sumansoul.module_news.httpBean.HomePagerBean
import com.sumansoul.module_news.httpBean.PersionData
import com.sumansoul.module_news.model.PersionDataModel
import com.sumansoul.module_news.view.TitleViewSModel

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/8/5
 * Time: 15:33
 */
public class HomePagerViewModel :MvvmBaseViewModel<HomeModel, HomePagerBean, TitleViewSModel>() {
    var mPersionDataModel=PersionDataModel()
    var mPersionDataLiveData = MutableLiveData<PersionData>()
    init {
        model= HomeModel()
        model.register(this)

        mPersionDataModel.register(object : IBaseModeListener<PersionData, PersionData> {
            override fun onLoadFinish(model: MvvmBaseModel<PersionData, PersionData>, data: PersionData, vararg pagingResult: PagingResult) {
                ToastUtils.showShort("回来了")
                mPersionDataLiveData.postValue(data)
            }
            override fun onLoadFail(model: MvvmBaseModel<PersionData, PersionData>, errMsg: String, vararg pagingResult: PagingResult) {
                errorMessage.value = errMsg
                viewStatusLiveData.value = ViewStatus.REFRESH_ERROR
            }

        })


    }
     fun getPersionData(id:String){
        // ToastUtils.showShort("点击点点点点点点点点点点="+id)
         Log.e("TAG","点击点点点点点点点点点点="+id)
        //mPersionDataModel.getCacheDataAndLoad()
    }
}