package com.sumansoul.module_news

import com.chad.library.adapter.base.BaseQuickAdapter
import com.sumansoul.module_news.databinding.ActivityMain3Binding
import com.sumansoul.module_news.databinding.TitleViewBinding
import com.sumansoul.module_news.httpBean.HomePagerBean
import com.sumansoul.module_news.model.HomeModel
import com.sumansoul.module_news.view.TitleViewSModel

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/12/8
 * Time: 14:38
 */

class QueryAdapter:BaseQuickAdapter<TitleViewSModel,DataBindBaseViewHolder> (R.layout.title_view){
    private var list:List<TitleViewSModel>?=null
    override fun convert(holder: DataBindBaseViewHolder, item: TitleViewSModel) {
        val titleViewBinding=holder.getViewBinding() as TitleViewBinding
        titleViewBinding.model=item
        titleViewBinding.executePendingBindings()
    }
    fun setAdapterData(items: List<TitleViewSModel>) {
        setList(items)
        notifyDataSetChanged()
    }
}