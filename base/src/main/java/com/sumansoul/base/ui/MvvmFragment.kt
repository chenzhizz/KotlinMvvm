package com.sumansoul.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ToastUtils
import com.sumansoul.base.viewmodel.MvvmBaseViewModel
import com.sumansoul.base.viewmodel.ViewStatus

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2020/8/7
 * Time: 15:33
 */
abstract class MvvmFragment<V : ViewDataBinding, VM : MvvmBaseViewModel<*, *, *>, D> : Fragment(),
    Observer<Any> {
    var viewModel: VM? = null
    abstract fun viewModel(): VM?

    var viewDataBinding: V? = null
    val mFragmentTag = ""
    abstract fun getLayoutId(): Int
    abstract fun onListItemInserted(sender: ObservableArrayList<D>)
    abstract fun onLoadError()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParmeters()
        setRetainInstance(true)

    }

    abstract fun initParmeters()
    abstract fun onRetryBtnClick()
    abstract fun getBindVariable(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return viewDataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel == null) {
            viewModel = viewModel()
        } else {
            this.viewModel = viewModel
        }
        viewModel?.let { lifecycle.addObserver(it) }
//        viewModel.dataList.observe(this, Observer {
//            when(it as ViewStatus){
//                ViewStatus.LOADING -> ToastUtils.showLong("加载中")
//                ViewStatus.EMPTY -> ToastUtils.showLong("数据为空")
//                ViewStatus.SHOW_CONTENT -> ToastUtils.showLong("1")
//                ViewStatus.NO_MORE_DATA -> ToastUtils.showLong("2")
//                ViewStatus.REFRESH_ERROR -> ToastUtils.showLong("error")
//                ViewStatus.LOAD_MORE_FAILED -> ToastUtils.showLong("加载失败")
//            }
//        })
//        viewModel.viewStatusLiveData.observe(this, Observer {
//           onListItemInserted(it as ObservableArrayList<D>)
//        })

//        viewModel?.dataList?.observe(viewLifecycleOwner, Observer {
//            onListItemInserted(it as ObservableArrayList<D>)
//        })
        viewModel?.dataList?.observe(viewLifecycleOwner,this)
        viewModel?.viewStatusLiveData?.observe(viewLifecycleOwner, this)
        if (getBindVariable() > 0) {
            viewDataBinding?.setVariable(getBindVariable(), viewModel)
            viewDataBinding?.executePendingBindings()
        }

    }

    override fun onChanged(t: Any) {
        if (t is ViewStatus) {
            when (t) {
                ViewStatus.LOADING -> ToastUtils.showShort("加载中")
                ViewStatus.EMPTY -> ToastUtils.showShort("数据为空")
                ViewStatus.SHOW_CONTENT -> ToastUtils.showShort("数据加载成功")
                ViewStatus.NO_MORE_DATA -> ToastUtils.showShort("没有更多数据")
                ViewStatus.REFRESH_ERROR -> {
                    ToastUtils.showShort(viewModel!!.errorMessage.value.toString())
                    onLoadError()
                }
                ViewStatus.LOAD_MORE_FAILED -> {
                    ToastUtils.showShort("加载失败")
                    onLoadError()
                }
            }
        } else if (t is ObservableArrayList<*>) {
            onListItemInserted(t as ObservableArrayList<D>)
        }
    }

}


