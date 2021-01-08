package com.sumansoul.base.customview

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/8/6
 * Time: 17:03
 */
abstract class BaseCustomView<T : ViewDataBinding, S : BaseCustomModel> : LinearLayout,
    ICustomView<S>, View.OnClickListener {

    var dataBinding: T = DataBindingUtil.inflate<T>(
        this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
        setViewLayoutId(), this, false
    )
        get() {
            return field
        }

    var viewModel: S? = null
        get() {
            return field
        }


     var mListener: ICustViewActionListener<S>?=null

    constructor(context: Context) : super(context) {
        initView()
    }


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initView()
    }


    override fun getRootView(): View {
        return dataBinding.root
    }

    private fun initView() {
        if (setViewLayoutId() != 0) {
//            dataBinding.root.setOnClickListener {
//                mListener?.onAction(ICustViewActionListener.ACTION_ROOT_CLICKED, it, viewModel)
//                onRootClick(it)
//            }
            this.addView(dataBinding.root)
        }

    }

    override fun setData(t: S) {
        viewModel = t
        setDataToView(viewModel!!)
        dataBinding.executePendingBindings()
        onDataUpdated()

    }


    fun onDataUpdated() {

    }

    override fun setstyle(resId: Int) {

    }

    override fun onClick(p0: View?) {
    }

    override fun setActionListener(listener: ICustViewActionListener<S>) {
        mListener=listener
    }
    abstract fun setViewLayoutId(): Int
    abstract fun setDataToView(viewModel: S)
    abstract fun onRootClick(it: View?)
}