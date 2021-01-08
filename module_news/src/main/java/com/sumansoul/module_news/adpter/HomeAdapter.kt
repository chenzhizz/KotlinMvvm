package com.sumansoul.module_news.adpter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumansoul.base.customview.ICustViewActionListener
import com.sumansoul.base.recyclerview.BaseViewHolder
import com.sumansoul.module_news.view.TitleViewSModel
import com.sumansoul.module_news.view.TitleView

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/8/5
 * Time: 17:28
 */
class HomeAdapter : RecyclerView.Adapter<BaseViewHolder<TitleViewSModel>> {
     lateinit var mItem: List<TitleViewSModel>
    val VIEW_TITLE = 1
    val VIEW_IMG = 2
    var listener: ICustViewActionListener<TitleViewSModel>?=null

    constructor(listener: ICustViewActionListener<TitleViewSModel>?) : super() {
        this.listener = listener
    }

    constructor() : super()


    fun setData(items: List<TitleViewSModel>) {
        mItem = items
        notifyDataSetChanged()
    }
    override fun getItemViewType(position: Int): Int {

        return if (mItem.get(position) is TitleViewSModel) {
            VIEW_TITLE
        } else {
            VIEW_IMG
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TitleViewSModel> {
        if (viewType == VIEW_TITLE) {
            return BaseViewHolder<TitleViewSModel>(TitleView(parent.context,listener))
        } else {
            return BaseViewHolder<TitleViewSModel>(TitleView(parent.context,listener))
        }
    }

    override fun getItemCount(): Int {
        return mItem.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<TitleViewSModel>, position: Int) {
        holder.bind(mItem[position])
    }

}