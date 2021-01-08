package com.sumansoul.base.model

import java.io.Serializable

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/7/27
 * Time: 14:40
 */
 class BaseCachedData<T> :Serializable {
    var updateTimeInMitles:Long = 0
      var  data:T?=null
}