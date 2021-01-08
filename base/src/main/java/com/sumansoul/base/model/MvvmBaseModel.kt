package com.sumansoul.base.model

import android.text.TextUtils
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.SPUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.Reference
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/27
 * Time: 14:43
 */
abstract class MvvmBaseModel<F, T> :
    MvvmNetWorkObserver<F> {
    private var comoisitieDispossable: CompositeDisposable? = null
    lateinit var mData: BaseCachedData<F>
     private var pageNumber: Int = 1
    private var mCachePreferencesKey: String/*缓存key*/
    private var mApkPrefefinedData: String/*预制数据*/
    private var mIsPaging = false/*是否分页*/
    lateinit var clazz: Class<F>
    private var INIT_PAGE_NUMBER: Int = 1
    private lateinit var mReferenceQueue: ReferenceQueue<IBaseModeListener<F, T>>
    private lateinit var mWeakListenerArrayList: ConcurrentLinkedQueue<WeakReference<IBaseModeListener<F, T>>>

    constructor(
        clazz: Class<F>,
        mCachePreferences: String,
        mApkPrefefinedData: String,
        mIsPaging: Boolean,
        vararg initPageNumber: Int

    ) {
        this.mCachePreferencesKey = mCachePreferences
        this.mApkPrefefinedData = mApkPrefefinedData
        this.mIsPaging = mIsPaging
        this.clazz = clazz

        INIT_PAGE_NUMBER = if (initPageNumber.isEmpty()) {
            0
        } else {
            initPageNumber[0]
        }
        pageNumber = if (initPageNumber.isEmpty()) {
            0
        } else {
            initPageNumber[0]
        }

        if (mCachePreferencesKey != null) {
            mData = BaseCachedData()
        }
        mReferenceQueue = ReferenceQueue()
        mWeakListenerArrayList = ConcurrentLinkedQueue()

    }

    fun isPaging(): Boolean {
        return mIsPaging
    }

    /*注册监听*/
    fun register(listener:IBaseModeListener<F, T>) {
        synchronized(this) {
            var releasedListener: Reference<out IBaseModeListener<F, T>>
            do {
                try {
                    releasedListener = mReferenceQueue.poll()
                    if (releasedListener == null) {
                        break
                    }
                    mWeakListenerArrayList.remove(releasedListener)
                } catch (e: Exception) {
                    break
                }

            } while (true)

        }
        mWeakListenerArrayList.forEach { _it ->
            if (_it.get() == null) {
                return
            }
        }
        mWeakListenerArrayList.add(WeakReference(listener, mReferenceQueue))

    }

    /*取消注册*/
    fun unRegister(listener: IBaseModeListener<F, T>) {
        synchronized(this) {
            mWeakListenerArrayList.forEach { _it ->
                if (_it.get() == listener) {
                    mWeakListenerArrayList.remove(_it)
                    return@forEach
                }
            }
        }
    }


    private fun saveDatatoPreferences(date: F) {
        if (date != null) {
            mData.data = date
            mData.updateTimeInMitles = System.currentTimeMillis()
            /*存储到sharePreferenece*/
            /*......*/
            SPUtils.getInstance().put(mCachePreferencesKey, GsonUtils.toJson(mData))
        }

    }

    fun refresh() {
        if (isPaging()) {
            pageNumber = INIT_PAGE_NUMBER
        }
        load()
    }

    public abstract fun load()

    /*是否更新数据到*/
    fun isNeedToUpdate(): Boolean {
        return true
    }

    fun cancel() {
        if (comoisitieDispossable != null && !comoisitieDispossable!!.isDisposed()) {
            comoisitieDispossable!!.dispose()
        }
    }

    fun addDisposable(d: Disposable) {
        if (comoisitieDispossable == null) {
            comoisitieDispossable = CompositeDisposable()
        }
        comoisitieDispossable!!.add(d)
    }

    fun getCacheDataAndLoad() {
        var saveDataString = SPUtils.getInstance().getString(mCachePreferencesKey)
        if (saveDataString.isEmpty()) {
            var saveData: F = GsonUtils.fromJson<F>(saveDataString, clazz)
            if (saveData != null) {
                onSuccess(saveData, true)
                if (isNeedToUpdate()) {
                    load()
                }
                return
            }

        }

        if (!TextUtils.isEmpty(mApkPrefefinedData)) {
            var saveData: F = GsonUtils.fromJson<F>(saveDataString, clazz)
            if (saveData != null) {
                onSuccess(saveData, true)
            }
        }

        load()
    }

    fun notifyResultToListener(networkResponse: F, data: T, isFormCache: Boolean) {
        synchronized(this) {
            mWeakListenerArrayList.forEach { _it ->

                if (_it.get() is IBaseModeListener<F, T>) {
                    var listenerItem = _it.get()
                    if (listenerItem != null) {
                        if (isPaging()) {
                            listenerItem.onLoadFinish(
                                this, data,
                                if (isFormCache) {
                                    PagingResult(
                                         false,
                                        true,
                                      true
                                    )
                                } else {
                                    PagingResult(
                                        if (data == null) {
                                            true
                                        } else {
                                            (data as List<*>).isEmpty()
                                        }, pageNumber == INIT_PAGE_NUMBER, if (data == null) {
                                            false
                                        } else {
                                            (data as List<*>).size > 0
                                        }
                                    )
                                }
                            )
                        } else {
                            listenerItem.onLoadFinish(this, data)
                        }
                    }

                }


            }


            if (isPaging()) {
                if (pageNumber == INIT_PAGE_NUMBER && !isFormCache) {
                    saveDatatoPreferences(networkResponse)
                }
                if (!isFormCache) {
                    if (data != null && data is List<*> && (data as List<*>).size > 0) {
                        pageNumber++
                    }
                }


            } else {
                if (!isFormCache) {
                    saveDatatoPreferences(networkResponse)
                }

            }


        }
    }

    fun loadFail(errorMessage: String) {
        synchronized(this) {
            mWeakListenerArrayList.forEach { _it ->

                if (_it.get() is IBaseModeListener) {
                    var listenerItem = _it.get()
                    if (listenerItem != null) {
                        if (isPaging()) {
                            listenerItem.onLoadFail(
                                this, errorMessage,
                                PagingResult(
                                    true,
                                    pageNumber == INIT_PAGE_NUMBER,
                                    true
                                )
                            )
                        } else {
                            listenerItem.onLoadFail(this, errorMessage)
                        }
                    }

                }
            }
        }
    }

    fun isResresh(): Boolean {
        return pageNumber == INIT_PAGE_NUMBER
    }


    //var ReferenceQueue<>
}