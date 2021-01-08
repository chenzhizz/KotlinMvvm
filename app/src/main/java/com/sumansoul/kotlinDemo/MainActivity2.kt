package com.sumansoul.kotlinDemo

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sumansoul.base.ui.MvvmActivity
import com.sumansoul.common.RouteServiceManager
import com.sumansoul.common.news.INewsServers
import com.sumansoul.kotlinDemo.databinding.ActivityMain2Binding
import com.sumansoul.module_news.view.TitleViewSModel
import com.sumansoul.module_news.viewModel.HomePagerViewModel


class MainActivity2 :
    MvvmActivity<ActivityMain2Binding, HomePagerViewModel, TitleViewSModel>() {
    private var service: INewsServers? = null
    lateinit var headFragment: Fragment;

    override fun initView() {
        service = RouteServiceManager.provide(INewsServers::class.java, "/news/test")
        if (service != null) {
            headFragment = service!!.getHeadFragment()
        }
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.container, headFragment, headFragment.javaClass.getSimpleName())
//        transaction.commit()
//        supportFragmentManager.inTransaction{
//            replace(R.id.container, headFragment)
//        }
        replaceFragment( headFragment,R.id.container)
    }

    override fun onRetryBtnClick() {

    }



    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }
    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction{replace(frameId, fragment)}
    }

    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }

    override fun getBindVariable(): Int {
        return 0
    }

    override fun viewModel(): HomePagerViewModel? {
        return null
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main2
    }

    override fun onListItemInserted(sender: ObservableArrayList<TitleViewSModel>) {

    }


}