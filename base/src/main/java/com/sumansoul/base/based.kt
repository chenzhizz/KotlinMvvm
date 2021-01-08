package com.sumansoul.base

import java.io.Serializable

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/28
 * Time: 16:54
 */
internal class based<T> : Serializable {
    var updateTimeInMitles: Long = 0
    private val data: T? = null
}