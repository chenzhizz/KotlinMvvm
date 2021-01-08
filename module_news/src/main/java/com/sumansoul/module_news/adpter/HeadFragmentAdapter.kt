package com.sumansoul.module_news.adpter

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/9/17
 * Time: 14:52
 *
 * BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT  最新懒加载方案
 *
 *
 */
public class HeadFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var mList = ObservableArrayList<Fragment>()
    fun setList(list:ObservableArrayList<Fragment>){
        mList.addAll(list)
    }
    override fun getItem(position: Int): Fragment {
       return mList[position]
    }

    override fun getCount(): Int {
      return mList.size
    }
}