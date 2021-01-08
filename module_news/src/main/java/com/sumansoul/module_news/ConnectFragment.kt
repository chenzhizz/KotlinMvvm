package com.sumansoul.module_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.sumansoul.module_news.adpter.HeadFragmentAdapter
import com.sumansoul.module_news.databinding.ConnectFragmentBinding

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/9/23
 * Time: 17:37
 */
public class ConnectFragment :Fragment() {
    var viewDataBinding:  ConnectFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding= DataBindingUtil.inflate(inflater, R.layout.connect_fragment, container, false)
        return viewDataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mList=ObservableArrayList<Fragment>()
        var adapter=HeadFragmentAdapter(childFragmentManager)
        mList.add(NewsFragment())
        mList.add(MePageFragment())
        mList.add(NewsFragment())
        adapter.setList(mList)
        viewDataBinding?.viewPage?.adapter=adapter
    }

}