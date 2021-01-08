package com.sumansoul.module_news.view

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.sumansoul.base.customview.BaseCustomView
import com.sumansoul.base.customview.ICustViewActionListener
import com.sumansoul.module_news.GlideApp

import com.sumansoul.module_news.R
import com.sumansoul.module_news.databinding.TitleViewBinding


/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/8/5
 * Time: 16:50
 */
class TitleView : BaseCustomView<TitleViewBinding, TitleViewSModel> {
    var listener: ICustViewActionListener<TitleViewSModel>?=null

    constructor(context: Context) : super(context)

    constructor(context: Context,listener: ICustViewActionListener<TitleViewSModel>?): super(context) {
        this.listener = listener
    }

    override fun setViewLayoutId(): Int {
      return R.layout.title_view
    }

    override fun setDataToView(viewModel: TitleViewSModel) {
        dataBinding.model=viewModel
        if(listener!=null){
            dataBinding.listener=listener
        }
    }

    override fun onRootClick(it: View?) {
        if (it != null) {
            ToastUtils.showLong("点击"+dataBinding.tvTitle.text)
        }

    }


    object ImageBinds {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String?) {
            LogUtils.dTag("ImageBinds",url)
            GlideApp.with(view.context)
                .load(url)
                .into(view)
        }






    }

}