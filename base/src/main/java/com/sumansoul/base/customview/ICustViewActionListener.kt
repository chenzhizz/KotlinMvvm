package com.sumansoul.base.customview

import android.view.View

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/8/6
 * Time: 16:58
 */
interface ICustViewActionListener<S> {
    companion object{
        val ACTION_ROOT_CLICKED=1
    }

    fun onAction(it1: Int, it: View, viewModel: S?)
    fun onAction( it: View, viewModel: S?)
}