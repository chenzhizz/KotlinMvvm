package com.sumansoul.module_news

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModelProvider
import com.sumansoul.base.customview.ICustViewActionListener
import com.sumansoul.base.ui.MvvmFragment
import com.sumansoul.module_news.databinding.FragmentMeBinding
import com.sumansoul.module_news.databinding.FragmentNewsBinding
import com.sumansoul.module_news.model.MePagerModel
import com.sumansoul.module_news.view.TitleViewSModel
import com.sumansoul.module_news.viewModel.HomePagerViewModel
import com.sumansoul.module_news.viewModel.MeHomePagerViewModel

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/12/9
 * Time: 9:56
 */
class MePageFragment : MvvmFragment<FragmentMeBinding, MeHomePagerViewModel, TitleViewSModel>(), ICustViewActionListener<TitleViewSModel> {
    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

    override fun viewModel(): MeHomePagerViewModel? {
        if(viewModel==null){
            viewModel = ViewModelProvider(this)[MeHomePagerViewModel::class.java]
        }
        return viewModel
    }

    override fun onListItemInserted(sender: ObservableArrayList<TitleViewSModel>) {
        if(sender.size>0){
            viewDataBinding?.model= sender[0]
        }

    }

    override fun onLoadError() {

    }

    override fun initParmeters() {

    }

    override fun onRetryBtnClick() {

    }

    override fun getBindVariable(): Int {
        return 0
    }

    override fun onAction(it1: Int, it: View, viewModel: TitleViewSModel?) {

    }

    override fun onAction(it: View, viewModel: TitleViewSModel?) {

    }
}