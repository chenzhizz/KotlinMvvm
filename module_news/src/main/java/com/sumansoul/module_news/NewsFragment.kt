package com.sumansoul.module_news

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.sumansoul.base.customview.ICustViewActionListener
import com.sumansoul.base.ui.MvvmFragment
import com.sumansoul.module_news.viewModel.HomePagerViewModel
import com.sumansoul.module_news.adpter.HomeAdapter
import com.sumansoul.module_news.databinding.FragmentNewsBinding
import com.sumansoul.module_news.view.TitleViewSModel


/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/8/7
 * Time: 16:05
 */
class NewsFragment : MvvmFragment<FragmentNewsBinding, HomePagerViewModel, TitleViewSModel>(),ICustViewActionListener<TitleViewSModel>{

    private lateinit var adapter: QueryAdapter
    override fun getLayoutId(): Int {
        return R.layout.fragment_news
    }

    override fun onListItemInserted(sender: ObservableArrayList<TitleViewSModel>) {
        viewDataBinding?.smartRefresh?.let {
            it.finishLoadMore()
            it.finishRefresh()
        }
        adapter.setAdapterData(sender)
    }

    override fun initParmeters() {
       // viewModel = ViewModelProvider(this)[HomePagerViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = QueryAdapter()
        viewDataBinding?.smartRefresh?.let {
            it.setOnRefreshListener {
                viewModel?.tryToRefresh()
            }
            it.setOnLoadMoreListener {
                viewModel?.tryToLoadMoreNextPage()
            }
        }
//        viewDataBinding?.smartRefresh?.setOnRefreshListener {
//            viewModel!!.tryToRefresh()
//        }
//        viewDataBinding?.smartRefresh?.setOnLoadMoreListener {
//            viewModel!!.tryToLoadMoreNextPage()
//        }
        viewDataBinding?.recyclerView?.adapter=adapter
        /*监听其他model的LiveData*/
        viewModel?.mPersionDataLiveData?.observe(viewLifecycleOwner, Observer {

            ToastUtils.showShort(GsonUtils.toJson(it))
        })
    }

    override fun onRetryBtnClick() {
    }

    /*主动去调Lifecycle   的响应事件*/
    fun onFragmentSelected(){
        viewModel?.onLifecycleOwnerResume()
    }

    override fun getBindVariable(): Int {
        return 0
    }

    override fun viewModel(): HomePagerViewModel? {

        if(viewModel==null){
            viewModel = ViewModelProvider(this)[HomePagerViewModel::class.java]
        }
        return viewModel
    }

    override fun onAction(it1: Int, it: View, viewModel: TitleViewSModel?) {
        ToastUtils.showShort("点击view:TitleViewSModel1")
    }

    override fun onAction(it: View, ssModel: TitleViewSModel?) {
        if (ssModel != null) {
            viewModel?.getPersionData(ssModel.id)
        }
    }

    override fun onLoadError() {
        viewDataBinding?.smartRefresh?.let {
            it.finishLoadMore()
            it.finishRefresh()
        }
    }


}