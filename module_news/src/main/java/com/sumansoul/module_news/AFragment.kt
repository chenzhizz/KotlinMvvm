package com.sumansoul.module_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sumansoul.module_news.databinding.ActivityMain3Binding
import com.sumansoul.module_news.databinding.FragmentNewsBinding

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/9/24
 * Time: 9:12
 */
class AFragment :Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var dataBinding=DataBindingUtil.inflate<FragmentNewsBinding>(inflater,R.layout.fragment_news,container,false)
        return dataBinding.root
    }
}