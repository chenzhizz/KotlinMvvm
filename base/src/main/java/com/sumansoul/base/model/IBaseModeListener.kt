package com.sumansoul.base.model

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/27
 * Time: 15:31
 */
interface IBaseModeListener<F,T> {
    fun  onLoadFinish(model: MvvmBaseModel<F, T>, data:T, vararg pagingResult: PagingResult);
    fun  onLoadFail(model: MvvmBaseModel<F, T>, errMsg:String, vararg pagingResult: PagingResult);
}