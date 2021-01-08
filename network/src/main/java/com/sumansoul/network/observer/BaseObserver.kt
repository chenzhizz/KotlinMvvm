package com.sumansoul.network.observer

import com.sumansoul.base.model.MvvmNetWorkObserver
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/24
 * Time: 14:09
 */
   class BaseObserver <T>: Observer<T> {
    //var baseModel:MvvmBaseModel<F,T>
    var mvvmNetWorkObserver: MvvmNetWorkObserver<T>

    constructor( mvvmNetWorkObserver: MvvmNetWorkObserver<T>) {
       // this.baseModel = baseModel
        this.mvvmNetWorkObserver = mvvmNetWorkObserver
    }


    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: T) {
       // onSuccess(t)
        mvvmNetWorkObserver.onSuccess(t,false)
    }

    override fun onError(e: Throwable) {
        //onFailure(e)
        mvvmNetWorkObserver.onFailure(e)
    }
//      abstract fun onSuccess(t:T)
//     abstract fun onFailure(e:Throwable)
}