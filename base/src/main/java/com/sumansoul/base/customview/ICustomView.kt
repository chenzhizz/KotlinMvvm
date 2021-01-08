package com.sumansoul.base.customview

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/8/5
 * Time: 16:51
 */
interface ICustomView<T : BaseCustomModel> {
    fun setData(t:T)
    fun setstyle(resId:Int)
    fun setActionListener(listener:ICustViewActionListener<T>)
}