package com.sumansoul.base.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.*
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.sumansoul.base.model.IBaseModeListener
import com.sumansoul.base.model.MvvmBaseModel
import com.sumansoul.base.model.PagingResult

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/28
 * Time: 17:50
 */
public abstract class MvvmBaseViewModel<M : MvvmBaseModel<*, *>,F, D> : ViewModel(), LifecycleObserver, IBaseModeListener<F,List<D>> {
    lateinit var model:M

    var dataList = MutableLiveData<ObservableList<D>>()
    var viewStatusLiveData = MutableLiveData<ViewStatus>()
    var errorMessage = MutableLiveData<String>()



    init {
        dataList.value= ObservableArrayList()
        viewStatusLiveData.value= ViewStatus.LOADING
        errorMessage.value=""
    }

    fun tryToRefresh() {
        model.refresh()
    }

    fun tryToLoadMoreNextPage() {
        model.refresh()
    }

    override fun onCleared() {
        super.onCleared()
        model.cancel()
    }
    override fun onLoadFinish(model: MvvmBaseModel<F, List<D>>, data: List<D>, vararg pagingResult: PagingResult) {

        if (model === this.model) {
            if (model.isPaging()) {
                if (pagingResult[0].isFirstPage) {
                    dataList.value?.clear()
                }
                if (pagingResult[0].isEmpty) {
                    if (pagingResult[0].isFirstPage) {
                        viewStatusLiveData.setValue(ViewStatus.EMPTY)
                    } else {
                        viewStatusLiveData.setValue(ViewStatus.NO_MORE_DATA)
                    }
                } else {
                    dataList.value?.addAll(data)
                    dataList.postValue(dataList.value)
                    viewStatusLiveData.setValue(ViewStatus.SHOW_CONTENT)
                }
            } else {
                dataList.value?.clear()
                dataList.value?.addAll(data)
                dataList.postValue(dataList.value)
                viewStatusLiveData.setValue(ViewStatus.SHOW_CONTENT)
            }
            viewStatusLiveData.postValue(viewStatusLiveData.value)
        }
    }

    override fun onLoadFail(model: MvvmBaseModel<F, List<D>>, errMsg: String, vararg pagingResult: PagingResult) {
        errorMessage.value = errMsg
        if (model.isPaging() && !pagingResult[0].isFirstPage) {
            viewStatusLiveData.setValue(ViewStatus.LOAD_MORE_FAILED)
        } else {
            viewStatusLiveData.setValue(ViewStatus.REFRESH_ERROR)
        }
        viewStatusLiveData.postValue(viewStatusLiveData.value)
    }

    /*配合Fragment 懒加载 BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT*/
    /*AndroidX 存在Bug 此事件也许不会回调*/
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public fun onLifecycleOwnerResume(){
        if(dataList.value==null || dataList.value!!.size==0){
            /*但是  首次进入之后会自动执行一次   此方法*/
            model.getCacheDataAndLoad()
        }else{
            dataList.postValue(dataList.value)
            viewStatusLiveData.postValue(viewStatusLiveData.value)
        }
    }

}