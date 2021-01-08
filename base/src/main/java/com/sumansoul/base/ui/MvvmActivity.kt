package com.sumansoul.base.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ToastUtils
import com.sumansoul.base.viewmodel.MvvmBaseViewModel
import com.sumansoul.base.viewmodel.ViewStatus

/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
abstract class MvvmActivity<V : ViewDataBinding, VM : MvvmBaseViewModel<*, *, *>, D> :
    AppCompatActivity(), Observer<Any?> {
    var viewModel: VM? = null
    abstract fun viewModel(): VM?
    private lateinit var viewDataBinding: V
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        initView()
        //if (viewModel != null) lifecycle.addObserver(viewModel)
    }

    open fun initView() {
        viewModel = viewModel()
    }

    protected abstract fun onRetryBtnClick()

    abstract fun getBindVariable(): Int

    abstract fun getLayoutId(): Int
    abstract fun onListItemInserted(sender: ObservableArrayList<D>)
    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        if (viewModel == null) {
            viewModel = viewModel()
        } else {
            this.viewModel = viewModel
        }
        viewModel?.let { lifecycle.addObserver(it) }
        //viewModel = if (viewModel == null) viewModel() else viewModel
        viewModel?.dataList?.observe(this,this)
        viewModel?.viewStatusLiveData?.observe(this,this)
        if (getBindVariable() > 0) {
            viewDataBinding.setVariable(getBindVariable(), viewModel)
        }
        viewDataBinding.executePendingBindings()
    }

    override fun onChanged(t: Any?) {
        if (t is ViewStatus) {
            when (t) {
                ViewStatus.LOADING -> ToastUtils.showLong("加载中")
                ViewStatus.EMPTY -> ToastUtils.showLong("数据为空")
                ViewStatus.SHOW_CONTENT -> ToastUtils.showLong("1")
                ViewStatus.NO_MORE_DATA -> ToastUtils.showLong("2")
                ViewStatus.REFRESH_ERROR -> ToastUtils.showShort(viewModel!!.errorMessage.value.toString())
                ViewStatus.LOAD_MORE_FAILED -> ToastUtils.showLong("加载失败")
            }
        }else if(t is ObservableArrayList<*>){
            onListItemInserted(t as ObservableArrayList<D>)
        }
    }

    public override fun onStop() {
        super.onStop()
        Log.d(activityTag, "Activity:$this: onStop")
    }

    public override fun onPause() {
        super.onPause()
        Log.d(activityTag, "Activity:$this: onPause")
    }

    public override fun onResume() {
        super.onResume()
        Log.d(activityTag, "Activity:$this: onResume")
    }

    public override fun onDestroy() {
        super.onDestroy()
        Log.d(activityTag, "Activity:$this: onDestroy")
    }

    protected val activityTag: String
        protected get() = this.javaClass.simpleName
}