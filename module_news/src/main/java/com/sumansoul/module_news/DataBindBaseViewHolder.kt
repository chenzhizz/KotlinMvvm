package com.sumansoul.module_news

import android.view.View
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import androidx.databinding.ViewDataBinding


/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/12/8
 * Time: 13:57
 */
public class DataBindBaseViewHolder(view: View) : BaseViewHolder(view) {
    private var ViewBinding:ViewDataBinding?=null
    init {
        ViewBinding = DataBindingUtil.bind(itemView)
    }

    fun getViewBinding(): ViewDataBinding? {
        return ViewBinding
    }

}