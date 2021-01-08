package com.sumansoul.base.model

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/27
 * Time: 14:47
 */
interface MvvmNetWorkObserver<F> {
    fun onSuccess(t:F,isFormCache:Boolean)
    fun onFailure(e:Throwable)
}