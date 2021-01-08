package com.sumansoul.network.exception

import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/24
 * Time: 16:54
 */
class HttpErrorHandler<T> : Function<Throwable,Observable<T>> {
    override fun apply(t: Throwable): Observable<T> {
     return Observable.error(ExceptionHandle.handleException(t))
    }
}